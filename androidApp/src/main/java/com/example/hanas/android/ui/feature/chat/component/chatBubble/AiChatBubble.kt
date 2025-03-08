package com.example.hanas.android.ui.feature.chat.component.chatBubble

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.hanas.android.ui.theme.HanasTheme

@Composable
fun AiChatBubble(
    modifier: Modifier = Modifier,
    message: String,
    translatedMessage: String,
    isMessageVisible: Boolean,
    isTranslated: Boolean,
    iconButtons: List<ChatBubbleIconButton>,
) {
    fun getMessage(): String {
        if (!isMessageVisible) {
            return "---- --- -----"
        }
        if (isTranslated) {
            return translatedMessage
        }
        return message
    }

    BaseChatBubble(
        modifier = modifier,
        message = getMessage(),
        backgroundColor = HanasTheme.colorScheme.tertiaryBackground,
        sharpCorner = CornerPosition.TopStart,
        iconButtons = iconButtons,
    )
}
