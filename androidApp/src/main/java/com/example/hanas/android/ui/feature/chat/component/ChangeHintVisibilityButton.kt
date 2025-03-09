package com.example.hanas.android.ui.feature.chat.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.hanas.android.R
import com.example.hanas.android.ui.theme.HanasTheme

// CancelSpeechButtonと構成一緒

@Composable
fun ChangeHintVisibilityButton(
    modifier: Modifier = Modifier,
    isVisible: Boolean,
    onClick: () -> Unit,
) {
    val haptic = LocalHapticFeedback.current
    val containerColor = HanasTheme.colorScheme.yellow.copy(alpha = 0.8f)
    val imageVector =
        if (isVisible) {
            ImageVector.vectorResource(R.drawable.ic_exclamation_slash)
        } else {
            ImageVector.vectorResource(R.drawable.ic_exclamation_2)
        }

    Box(
        modifier =
            modifier
                .background(
                    color = containerColor,
                    shape = CircleShape,
                )
                .clip(CircleShape)
                .clickable {
                    haptic.performHapticFeedback(HapticFeedbackType.TextHandleMove)
                    onClick()
                },
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            modifier = Modifier.size(24.dp),
            imageVector = imageVector,
            contentDescription = null,
            tint = HanasTheme.colorScheme.gray200,
        )
    }
}
