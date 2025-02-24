package com.example.hanas.android.ui.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.hanas.android.ui.component.CancelRecordButton
import com.example.hanas.android.ui.component.ChangeHintVisibilityButton
import com.example.hanas.android.ui.component.MicButton
import com.example.hanas.android.ui.component.TopBar
import com.example.hanas.android.ui.component.TopBarActionButton
import com.example.hanas.android.ui.component.chatBubble.AiChatBubble
import com.example.hanas.android.ui.component.chatBubble.ChatBubbleAction
import com.example.hanas.android.ui.component.chatBubble.UserChatBubble
import com.example.hanas.android.ui.theme.HanasTheme

sealed interface ChatFeedComponentType {
    data object TaskIntroduction : ChatFeedComponentType

    data class AiChatBubble(val message: String, val actions: List<ChatBubbleAction>) :
        ChatFeedComponentType

    data class UserChatBubble(val message: String, val actions: List<ChatBubbleAction>) :
        ChatFeedComponentType

    data class SuggestionGuide(val sentence: String) : ChatFeedComponentType
}

@Composable
fun ChatScreen() {
    val haptic = LocalHapticFeedback.current
    val chatFeedComponentTypes =
        listOf(
            ChatFeedComponentType.AiChatBubble(
                "Hey geDem, how's it going? Hey geDem. Hey geDem, how's it going?",
                listOf(
                    ChatBubbleAction.PlaySound(false) {},
                    ChatBubbleAction.Translate {},
                    ChatBubbleAction.ChangeSentenceVisibility(true) {},
                ),
            ),
            ChatFeedComponentType.SuggestionGuide("かなりお腹が空いていて、今すぐにも倒れそうです。"),
            ChatFeedComponentType.UserChatBubble(
                "I'm doing great! I'm doing great! I'm doing great!",
                listOf(
                    ChatBubbleAction.Retry {},
                ),
            ),
        )

    Scaffold(
        containerColor = HanasTheme.colorScheme.primaryBackground,
        topBar = {
            TopBar(
                modifier = Modifier.statusBarsPadding(),
                title = "フリートーク",
                leftActionButton =
                    TopBarActionButton(
                        icon = Icons.AutoMirrored.Default.ArrowBack,
                        onClick = {
                            haptic.performHapticFeedback(HapticFeedbackType.TextHandleMove)
                        },
                    ),
            )
        },
    ) { innerPadding ->
        Box(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
        ) {
            // チャットフィード
            Column(
                modifier =
                    Modifier
                        .fillMaxSize()
                        .padding(horizontal = 12.dp, vertical = 24.dp)
                        .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                chatFeedComponentTypes.forEach { componentType ->
                    when (componentType) {
                        is ChatFeedComponentType.AiChatBubble -> {
                            BoxWithConstraints {
                                val maxWidth = getChatBubbleMaxWidth(constraints)

                                AiChatBubble(
                                    modifier =
                                        Modifier
                                            .widthIn(max = maxWidth),
                                    message = componentType.message,
                                    actions = componentType.actions,
                                )
                            }
                        }

                        is ChatFeedComponentType.UserChatBubble -> {
                            BoxWithConstraints(
                                modifier = Modifier.align(Alignment.End),
                            ) {
                                val maxWidth = getChatBubbleMaxWidth(constraints)

                                UserChatBubble(
                                    modifier =
                                        Modifier
                                            .widthIn(max = maxWidth),
                                    message = componentType.message,
                                    actions = componentType.actions,
                                )
                            }
                        }

                        is ChatFeedComponentType.SuggestionGuide -> {
                            ChatFeedComponentType.SuggestionGuide(sentence = componentType.sentence)
                        }

                        is ChatFeedComponentType.TaskIntroduction -> {
                            TaskIntroduction()
                        }
                    }
                }
            }

            Row(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(bottom = 24.dp)
                        .align(Alignment.BottomCenter),
                horizontalArrangement =
                    Arrangement.spacedBy(
                        space = 24.dp,
                        alignment = Alignment.CenterHorizontally,
                    ),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                // 録音停止ボタン
                CancelRecordButton(
                    modifier = Modifier.size(48.dp),
                    onClick = {},
                )

                // 録音ボタン
                MicButton(
                    modifier =
                        Modifier
                            .offset(y = (-15).dp)
                            .shadow(5.dp, shape = CircleShape)
                            .background(HanasTheme.colorScheme.primaryBackground),
                    isRecording = false,
                    onClick = {
                        haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                    },
                )

                ChangeHintVisibilityButton(
                    modifier = Modifier.size(48.dp),
                    isVisible = true,
                    onClick = { },
                )
            }
        }
    }
}

@Composable
private fun getChatBubbleMaxWidth(constraints: Constraints): Dp {
    return with(LocalDensity.current) {
        (constraints.maxWidth.toDp() / 1.3.dp).dp
    }
}

@Composable
private fun TaskIntroduction() {
    // レッスン画面で表示するやーつ
    // TODO
}

@Preview
@Composable
private fun Preview() {
    HanasTheme {
        ChatScreen()
    }
}
