package com.example.hanas.android.ui.component.chatBubble

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.hanas.android.ui.theme.HanasTheme

@Composable
fun UserChatBubble(
    modifier: Modifier = Modifier,
    message: String,
    actions: List<ChatBubbleAction>,
) {
    BaseChatBubble(
        modifier = modifier,
        message = message,
        backgroundColor = HanasTheme.colorScheme.secondaryBackground,
        sharpCorner = CornerPosition.TopEnd,
        actions = actions,
    )
}
