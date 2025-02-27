package com.example.hanas.android.ui.feature.chat

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import com.example.hanas.android.ui.common.EventEffect
import com.example.hanas.android.ui.common.EventFlow
import com.example.hanas.android.ui.feature.chat.component.ChatFeedComponentType
import com.example.hanas.android.ui.feature.chat.component.chatBubble.ChatBubbleAction

sealed interface ChatScreenEvent {
    data object OnAppear : ChatScreenEvent

    data object OnClickPopBackButton : ChatScreenEvent
}

data class ChatUiState(
    val chatFeedComponentTypes: List<ChatFeedComponentType>,
)

@Composable
internal fun chatScreenPresenter(
    navController: NavController,
    eventFlow: EventFlow<ChatScreenEvent>,
): ChatUiState {
    val (uiState, setUiState) =
        remember {
            mutableStateOf(
                ChatUiState(
                    chatFeedComponentTypes = emptyList(),
                ),
            )
        }

    EventEffect(eventFlow) { event ->
        when (event) {
            ChatScreenEvent.OnAppear -> {
                setUiState(
                    uiState.copy(
                        chatFeedComponentTypes =
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
                            ),
                    ),
                )
            }

            ChatScreenEvent.OnClickPopBackButton -> {
                navController.popBackStack()
            }
        }
    }

    return uiState
}
