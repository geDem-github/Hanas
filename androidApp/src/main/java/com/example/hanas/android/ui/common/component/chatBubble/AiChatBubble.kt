package com.example.hanas.android.ui.feature.chat.component.chatBubble

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.hanas.android.ui.theme.HanasTheme

@Composable
fun AiChatBubble(
    modifier: Modifier = Modifier,
    message: String,
    actions: List<ChatBubbleAction>,
) {
    BaseChatBubble(
        modifier = modifier,
        message = message,
        backgroundColor = HanasTheme.colorScheme.tertiaryBackground,
        sharpCorner = CornerPosition.TopStart,
        actions = actions,
    )
}
