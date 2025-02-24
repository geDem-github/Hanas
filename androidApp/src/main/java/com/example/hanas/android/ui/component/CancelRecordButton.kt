package com.example.hanas.android.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.unit.dp
import com.example.hanas.android.ui.theme.HanasTheme

// ChangeHintVisibilityButtonと構成一緒

@Composable
fun CancelRecordButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    val haptic = LocalHapticFeedback.current
    Box(
        modifier =
            modifier
                .background(
                    color = HanasTheme.colorScheme.red.copy(0.6f),
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
            modifier = Modifier.size(32.dp),
            imageVector = Icons.Default.Close,
            contentDescription = null,
            tint = HanasTheme.colorScheme.gray200,
        )
    }
}
