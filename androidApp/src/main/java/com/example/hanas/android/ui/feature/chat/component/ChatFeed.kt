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
import androidx.compose.ui.unit.min
import com.example.hanas.android.ui.feature.chat.component.chatBubble.AiChatBubble
import com.example.hanas.android.ui.feature.chat.component.chatBubble.ChatBubbleAction
import com.example.hanas.android.ui.feature.chat.component.chatBubble.UserChatBubble

sealed interface ChatFeedComponentType {
    data object TaskIntroduction : ChatFeedComponentType

    data class AiChatBubble(val message: String, val actions: List<ChatBubbleAction>) :
        ChatFeedComponentType

    data class UserChatBubble(val message: String, val actions: List<ChatBubbleAction>) :
        ChatFeedComponentType

    data class SuggestionGuide(val sentence: String) : ChatFeedComponentType
}

@Composable
fun ChatFeed(
    modifier: Modifier = Modifier,
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
                            actions = componentType.actions,
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
                            modifier =
                                Modifier
                                    .widthIn(max = maxWidth, min = minWidth),
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
