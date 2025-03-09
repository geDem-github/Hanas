package com.example.hanas.android.ui.feature.chat.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.widthIn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.hanas.android.ui.feature.chat.component.chatBubble.AiChatBubble
import com.example.hanas.android.ui.feature.chat.component.chatBubble.ChatBubbleIconButton
import com.example.hanas.android.ui.feature.chat.component.chatBubble.UserChatBubble
import java.util.UUID

interface Identifiable {
    val id: UUID
}

sealed interface ChatFeedComponentType : Identifiable {
    data class TaskIntroduction(
        override val id: UUID = UUID.randomUUID(),
        val text: String,
    ) : ChatFeedComponentType

    data class AiChatBubble(
        override val id: UUID = UUID.randomUUID(),
        val message: String,
        val translatedMessage: String?,
        val isTranslated: Boolean,
        val isPlayingSound: Boolean,
        val onClickSpeakerButton: () -> Unit,
        val onClickTranslateButton: () -> Unit,
        val onClickVisibilityButton: () -> Unit,
    ) : ChatFeedComponentType

    data class UserChatBubble(
        override val id: UUID = UUID.randomUUID(),
        val message: String,
        val onClickRetryButton: () -> Unit,
    ) : ChatFeedComponentType

    data class SuggestionGuide(
        override val id: UUID = UUID.randomUUID(),
        val sentence: String,
    ) : ChatFeedComponentType
}

@Composable
fun ChatFeed(
    modifier: Modifier = Modifier,
    isAiChatTextVisible: Boolean,
    isHintVisible: Boolean,
    chatFeedComponentTypes: List<ChatFeedComponentType>,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        chatFeedComponentTypes.forEach { componentType ->
            when (componentType) {
                is ChatFeedComponentType.AiChatBubble -> {
                    BoxWithConstraints {
                        val maxWidth = getChatBubbleMaxWidth(constraints)
                        val minWidth = getChatBubbleMinWidth(constraints)

                        AiChatBubble(
                            modifier =
                                Modifier
                                    .widthIn(max = maxWidth, min = minWidth),
                            message = componentType.message,
                            translatedMessage = componentType.translatedMessage ?: "",
                            isMessageVisible = isAiChatTextVisible,
                            isTranslated = componentType.isTranslated,
                            iconButtons =
                                listOf(
                                    ChatBubbleIconButton.PlaySound(
                                        isPlaying = componentType.isPlayingSound,
                                        onClick = componentType.onClickSpeakerButton,
                                    ),
                                    ChatBubbleIconButton.Translate(
                                        onClick = componentType.onClickTranslateButton,
                                    ),
                                    ChatBubbleIconButton.ChangeSentenceVisibility(
                                        isVisible = isAiChatTextVisible,
                                        onClick = componentType.onClickVisibilityButton,
                                    ),
                                ),
                        )
                    }
                }

                is ChatFeedComponentType.UserChatBubble -> {
                    BoxWithConstraints(
                        modifier = Modifier.align(Alignment.End),
                    ) {
                        val maxWidth = getChatBubbleMaxWidth(constraints)
                        val minWidth = getChatBubbleMinWidth(constraints)

                        UserChatBubble(
                            modifier = Modifier.widthIn(max = maxWidth, min = minWidth),
                            message = componentType.message,
                            actions =
                                listOf(
                                    ChatBubbleIconButton.Retry(
                                        onClick = componentType.onClickRetryButton,
                                    ),
                                ),
                        )
                    }
                }

                is ChatFeedComponentType.SuggestionGuide -> {
                    if (!isHintVisible) return@forEach
                    ChatFeedComponentType.SuggestionGuide(sentence = componentType.sentence)
                }

                is ChatFeedComponentType.TaskIntroduction -> {
                    TaskIntroduction()
                }
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
private fun getChatBubbleMinWidth(constraints: Constraints): Dp {
    return with(LocalDensity.current) {
        (constraints.maxWidth.toDp() / 2.5.dp).dp
    }
}

@Composable
private fun TaskIntroduction() {
    // レッスン画面で表示するやーつ
    // TODO
}
