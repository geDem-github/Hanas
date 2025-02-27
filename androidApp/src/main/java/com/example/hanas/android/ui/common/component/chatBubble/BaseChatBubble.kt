package com.example.hanas.android.ui.feature.chat.component.chatBubble

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.hanas.android.ui.theme.HanasTheme

sealed class ChatBubbleAction {
    abstract val onClick: () -> Unit

    data class PlaySound(
        val isPlaying: Boolean,
        override val onClick: () -> Unit,
    ) :
        ChatBubbleAction()

    data class Translate(
        override val onClick: () -> Unit,
    ) : ChatBubbleAction()

    data class ChangeSentenceVisibility(
        val isVisible: Boolean,
        override val onClick: () -> Unit,
    ) :
        ChatBubbleAction()

    data class Retry(
        override val onClick: () -> Unit,
    ) : ChatBubbleAction()

    val icon: ImageVector
        @Composable
        get() =
            when (this) {
                is ChangeSentenceVisibility ->
                    if (isVisible) {
                        ImageVector.vectorResource(com.example.hanas.android.R.drawable.ic_eye_slash)
                    } else {
                        ImageVector.vectorResource(com.example.hanas.android.R.drawable.ic_eye)
                    }

                is PlaySound ->
                    if (isPlaying) {
                        ImageVector.vectorResource(com.example.hanas.android.R.drawable.ic_stop) // 停止アイコン
                    } else {
                        ImageVector.vectorResource(com.example.hanas.android.R.drawable.ic_speaker) // 再生アイコン
                    }

                is Retry -> ImageVector.vectorResource(com.example.hanas.android.R.drawable.ic_arrow_loop)
                is Translate -> ImageVector.vectorResource(com.example.hanas.android.R.drawable.ic_language)
            }
}

internal enum class CornerPosition {
    TopStart,
    TopEnd,
    BottomStart,
    BottomEnd,
}

@Composable
internal fun BaseChatBubble(
    modifier: Modifier = Modifier,
    message: String,
    actions: List<ChatBubbleAction>,
    backgroundColor: Color,
    sharpCorner: CornerPosition,
) {
    val roundedCornerValue = 8.dp
    val shape =
        RoundedCornerShape(
            topStart = if (sharpCorner == CornerPosition.TopStart) 0.dp else roundedCornerValue,
            topEnd = if (sharpCorner == CornerPosition.TopEnd) 0.dp else roundedCornerValue,
            bottomStart = if (sharpCorner == CornerPosition.BottomStart) 0.dp else roundedCornerValue,
            bottomEnd = if (sharpCorner == CornerPosition.BottomEnd) 0.dp else roundedCornerValue,
        )

    Column(
        modifier =
            modifier
                .shadow(
                    elevation = 2.dp,
                    shape = shape,
                    ambientColor = HanasTheme.colorScheme.shadow,
                    spotColor = HanasTheme.colorScheme.shadow,
                )
                .background(
                    color = backgroundColor,
                    shape = shape,
                )
                .padding(horizontal = 16.dp)
                .padding(top = 12.dp, bottom = 4.dp),
    ) {
        Text(
            text = message,
            style = HanasTheme.typography.sRegular,
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            actions.forEach { action ->
                IconButton(imageVector = action.icon, onClick = action.onClick)
            }
        }
    }
}

@Composable
private fun IconButton(
    modifier: Modifier = Modifier,
    imageVector: ImageVector,
    onClick: () -> Unit,
) {
    val haptic = LocalHapticFeedback.current
    Box(
        modifier =
            modifier
                .clip(CircleShape)
                .clickable {
                    haptic.performHapticFeedback(HapticFeedbackType.TextHandleMove)
                    onClick()
                }
                .padding(8.dp),
    ) {
        Icon(
            modifier = Modifier.size(24.dp),
            imageVector = imageVector,
            contentDescription = null,
            tint = HanasTheme.colorScheme.secondaryText,
        )
    }
}
