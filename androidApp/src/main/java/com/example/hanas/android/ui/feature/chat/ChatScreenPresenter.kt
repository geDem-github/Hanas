package com.example.hanas.android.ui.feature.chat

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.speech.SpeechRecognizer
import android.widget.Toast
import androidx.activity.compose.LocalActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import com.example.hanas.android.ui.common.EventEffect
import com.example.hanas.android.ui.common.EventFlow
import com.example.hanas.android.ui.feature.chat.component.ChatFeedComponentType
import com.example.hanas.android.util.mic.HanasIntentFactory
import com.example.hanas.android.util.mic.HanasRecognitionListener
import com.example.hanas.domain.entity.languageActivity.ChatActivity
import com.example.hanas.domain.usecase.GetChatActivityUseCase
import com.example.hanas.domain.usecase.SendChatUseCase
import com.example.hanas.domain.usecase.StartChatActivityUseCase
import kotlinx.coroutines.launch
import org.koin.compose.koinInject
import java.util.UUID

sealed interface ChatScreenEvent {
    data object OnAppear : ChatScreenEvent

    data object OnClickPopBackButton : ChatScreenEvent

    data object OnClickMicButton : ChatScreenEvent

    data object OnClickCancelSpeechButton : ChatScreenEvent

    data object OnClickHintButton : ChatScreenEvent

    data class OnFinishSpeech(val text: String?) : ChatScreenEvent

    data class OnResponseRecordAudioPermission(val isGranted: Boolean) : ChatScreenEvent

    data class OnClickChatSpeakerButton(val id: UUID) : ChatScreenEvent

    data class OnClickChatTranslateButton(val id: UUID) : ChatScreenEvent

    data class OnClickChatVisibilityButton(val id: UUID) : ChatScreenEvent

    data class OnClickRetryButton(val id: UUID) : ChatScreenEvent
}

data class ChatUiState(
    val topic: String?,
    val isSpeeching: Boolean,
    val isAiChatTextVisible: Boolean,
    val isHintVisible: Boolean,
    val chatFeedComponentTypes: List<ChatFeedComponentType>,
)

@Composable
internal fun chatScreenPresenter(
    chatId: String,
    navController: NavController,
    eventFlow: EventFlow<ChatScreenEvent>,
    mainActivityContext: Activity? = LocalActivity.current,
    sendChatUseCase: SendChatUseCase = koinInject(),
    getChatActivityUseCase: GetChatActivityUseCase = koinInject(),
    startChatActivityUseCase: StartChatActivityUseCase = koinInject(),
): ChatUiState {
    // State
    var chatActivity by remember { mutableStateOf<ChatActivity?>(null) }
    var topic by rememberSaveable { mutableStateOf<String?>(null) }
    var isSpeeching by rememberSaveable { mutableStateOf(false) }
    var isAiChatTextVisible by rememberSaveable { mutableStateOf(true) }
    var isHintVisible by rememberSaveable { mutableStateOf(true) }
    var chatFeedComponentTypes by rememberSaveable { mutableStateOf(emptyList<ChatFeedComponentType>()) }

    // 音声認識 権限リクエストランチャー
    val recordAudioRequest =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestPermission(),
            onResult = {
                eventFlow.tryEmit(ChatScreenEvent.OnResponseRecordAudioPermission(isGranted = it))
            },
        )

    // 音声入力関連
    val speechRecognizer = SpeechRecognizer.createSpeechRecognizer(mainActivityContext)
    val speechRecognizerIntent = HanasIntentFactory.englishOnlySpeechRecognizerIntent

    // AIチャット作成
    fun createAiChatBubble(
        id: UUID = UUID.randomUUID(),
        message: String,
    ): ChatFeedComponentType.AiChatBubble {
        return ChatFeedComponentType.AiChatBubble(
            id = id,
            message = message,
            translatedMessage = null,
            isTranslated = false,
            isPlayingSound = false,
            onClickSpeakerButton = {
                eventFlow.tryEmit(
                    ChatScreenEvent.OnClickChatSpeakerButton(id),
                )
            },
            onClickTranslateButton = {
                eventFlow.tryEmit(
                    ChatScreenEvent.OnClickChatTranslateButton(id),
                )
            },
            onClickVisibilityButton = {
                eventFlow.tryEmit(
                    ChatScreenEvent.OnClickChatVisibilityButton(id),
                )
            },
        )
    }

    // ユーザーチャット作成
    fun createUserChatBubble(
        id: UUID = UUID.randomUUID(),
        message: String,
    ): ChatFeedComponentType.UserChatBubble {
        return ChatFeedComponentType.UserChatBubble(
            id = id,
            message = message,
            onClickRetryButton = {
                eventFlow.tryEmit(ChatScreenEvent.OnClickRetryButton(id))
            },
        )
    }

    EventEffect(eventFlow) { event ->
        when (event) {
            /** 画面表示 */
            is ChatScreenEvent.OnAppear -> {
                val foundActivity =
                    getChatActivityUseCase.execute(chatId)
                        ?: run {
                            Toast.makeText(mainActivityContext, "不明なエラーが発生しました。", Toast.LENGTH_SHORT).show()
                            navController.popBackStack()
                            return@EventEffect
                        }

                chatActivity = foundActivity
                topic = foundActivity.title

                startChatActivityUseCase
                    .execute(chatId)
                    .collect { result ->
                        result
                            .fold(
                                onSuccess = {
                                    chatFeedComponentTypes =
                                        chatFeedComponentTypes.plus(
                                            createAiChatBubble(message = it.content),
                                        )
                                },
                                onFailure = {
                                    Toast.makeText(mainActivityContext, "不明なエラーが発生しました。", Toast.LENGTH_SHORT).show()
                                    navController.popBackStack()
                                },
                            )
                    }
            }

            /** 戻る遷移ボタンタップ */
            is ChatScreenEvent.OnClickPopBackButton -> {
                navController.popBackStack()
            }

            /** マイクボタンタップ */
            is ChatScreenEvent.OnClickMicButton -> {
                // 入力中なら、入力を中止する
                if (isSpeeching) {
                    speechRecognizer.stopListening()
                    isSpeeching = false
                    return@EventEffect
                }

                // 音声入力権限確認
                if (
                    PackageManager.PERMISSION_DENIED ==
                    mainActivityContext?.checkSelfPermission(
                        Manifest.permission.RECORD_AUDIO,
                    )
                ) {
                    if (mainActivityContext.shouldShowRequestPermissionRationale(Manifest.permission.RECORD_AUDIO)) {
                        recordAudioRequest.launch(Manifest.permission.RECORD_AUDIO)
                    } else {
                        val message = "設定アプリで音声入力を許可してください。"
                        Toast.makeText(mainActivityContext, message, Toast.LENGTH_SHORT).show()
                    }
                    return@EventEffect
                }

                // 音声入力開始
                isSpeeching = true
                val listener =
                    HanasRecognitionListener(
                        onResult = { eventFlow.tryEmit(ChatScreenEvent.OnFinishSpeech(it)) },
                    )
                speechRecognizer.setRecognitionListener(listener)
                speechRecognizer.startListening(speechRecognizerIntent)
            }

            /** 音声認識キャンセルボタンタップ */
            is ChatScreenEvent.OnClickCancelSpeechButton -> {
                speechRecognizer.cancel()
                isSpeeching = false
            }

            /** ヒントボタンタップ */
            is ChatScreenEvent.OnClickHintButton -> {
                isHintVisible = !isHintVisible
            }

            /** 音声認識終了 */
            is ChatScreenEvent.OnFinishSpeech -> {
                isSpeeching = false
                val text = event.text
                if (!text.isNullOrEmpty()) {
                    launch {
                        // フィード更新
                        val userMessageId = UUID.randomUUID()
                        chatFeedComponentTypes =
                            chatFeedComponentTypes
                                .plus(createUserChatBubble(userMessageId, text))

                        // チャットを送信する
                        sendChatUseCase
                            .execute(chatId, text)
                            .collect { result ->
                                result
                                    .onSuccess { message ->
                                        chatFeedComponentTypes =
                                            chatFeedComponentTypes
                                                .plus(createAiChatBubble(message = message))
                                    }
                                    .onFailure {
                                        Toast
                                            .makeText(
                                                mainActivityContext,
                                                "メッセージ送信に失敗しました。もう一度お試しください。",
                                                Toast.LENGTH_SHORT,
                                            )
                                            .show()
                                    }
                            }
                    }
                }
            }

            /** 音声入力権限レスポンス */
            is ChatScreenEvent.OnResponseRecordAudioPermission -> {
                val message =
                    if (event.isGranted) "音声入力が許可されました" else "音声入力が拒否されました。"
                Toast.makeText(mainActivityContext, message, Toast.LENGTH_SHORT).show()
            }

            /** リトライボタンタップ */
            is ChatScreenEvent.OnClickRetryButton -> TODO()

            /** AIメッセージ読み上げボタンタップ */
            is ChatScreenEvent.OnClickChatSpeakerButton -> {
                // TODO: 音声再生・停止
                chatFeedComponentTypes =
                    chatFeedComponentTypes
                        .map {
                            when {
                                it.id == event.id && it is ChatFeedComponentType.AiChatBubble ->
                                    it.copy(
                                        isPlayingSound = !it.isPlayingSound,
                                    )

                                else -> it
                            }
                        }
            }

            /** AIメッセージの翻訳ボタンタップ */
            is ChatScreenEvent.OnClickChatTranslateButton -> {
                chatFeedComponentTypes =
                    chatFeedComponentTypes
                        .map {
                            when {
                                it.id == event.id && it is ChatFeedComponentType.AiChatBubble ->
                                    it.copy(isTranslated = !it.isTranslated)

                                else -> it
                            }
                        }
            }

            /** AIメッセージの表示/非表示ボタンタップ */
            is ChatScreenEvent.OnClickChatVisibilityButton -> {
                isAiChatTextVisible = !isAiChatTextVisible
            }
        }
    }

    return ChatUiState(
        topic = topic,
        isSpeeching = isSpeeching,
        isAiChatTextVisible = isAiChatTextVisible,
        isHintVisible = isHintVisible,
        chatFeedComponentTypes = chatFeedComponentTypes,
    )
}
