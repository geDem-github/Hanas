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
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import com.example.hanas.android.ui.common.EventEffect
import com.example.hanas.android.ui.common.EventFlow
import com.example.hanas.android.ui.feature.chat.component.ChatFeedComponentType
import com.example.hanas.android.ui.feature.chat.component.chatBubble.ChatBubbleAction
import com.example.hanas.android.util.mic.HanasIntentFactory
import com.example.hanas.android.util.mic.HanasRecognitionListener
import com.example.hanas.domain.usecase.SendChatUseCase
import kotlinx.coroutines.launch
import org.koin.compose.koinInject

sealed interface ChatScreenEvent {
    data object OnAppear : ChatScreenEvent

    data object OnClickPopBackButton : ChatScreenEvent

    data object OnClickMicButton : ChatScreenEvent

    data object OnClickCancelSpeechButton : ChatScreenEvent

    data class OnFinishSpeech(val text: String?) : ChatScreenEvent

    data class OnResponseRecordAudioPermission(val isGranted: Boolean) : ChatScreenEvent
}

data class ChatUiState(
    val isSpeeching: Boolean,
    val chatFeedComponentTypes: List<ChatFeedComponentType>,
)

@Composable
internal fun chatScreenPresenter(
    mainActivityContext: Activity? = LocalActivity.current,
    sendChatUseCase: SendChatUseCase = koinInject(),
    navController: NavController,
    eventFlow: EventFlow<ChatScreenEvent>,
): ChatUiState {
    // State
    var isSpeeching by remember { mutableStateOf(false) }
    var chatFeedComponentTypes by remember { mutableStateOf(emptyList<ChatFeedComponentType>()) }

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

    EventEffect(eventFlow) { event ->
        when (event) {
            // 画面表示
            is ChatScreenEvent.OnAppear -> {}

            // 戻る遷移ボタンタップ
            is ChatScreenEvent.OnClickPopBackButton -> {
                navController.popBackStack()
            }

            // マイクボタンタップ
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

            // 音声認識キャンセルボタンタップ
            is ChatScreenEvent.OnClickCancelSpeechButton -> {
                speechRecognizer.cancel()
                isSpeeching = false
            }

            // 音声認識終了
            is ChatScreenEvent.OnFinishSpeech -> {
                isSpeeching = false
                val text = event.text
                if (!text.isNullOrEmpty()) {
                    launch {
                        // フィード更新
                        chatFeedComponentTypes =
                            chatFeedComponentTypes.plus(
                                ChatFeedComponentType.UserChatBubble(
                                    message = text,
                                    actions = listOf(ChatBubbleAction.Retry {}),
                                ),
                            )

                        // チャットを送信する
                        sendChatUseCase
                            .execute(text)
                            .collect { result ->
                                result
                                    .onSuccess { message ->
                                        chatFeedComponentTypes =
                                            chatFeedComponentTypes
                                                .plus(
                                                    ChatFeedComponentType.AiChatBubble(
                                                        message = message,
                                                        actions = listOf(),
                                                    ),
                                                )
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

            // 音声入力権限レスポンス
            is ChatScreenEvent.OnResponseRecordAudioPermission -> {
                val message = if (event.isGranted) "音声入力が許可されました" else "音声入力が拒否されました。"
                Toast.makeText(mainActivityContext, message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    return ChatUiState(
        isSpeeching = isSpeeching,
        chatFeedComponentTypes = chatFeedComponentTypes,
    )
}
