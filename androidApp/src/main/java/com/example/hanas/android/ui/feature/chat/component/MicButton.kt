package com.example.hanas.android.ui.feature.chat.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.hanas.android.R
import com.example.hanas.android.ui.theme.HanasTheme

@Composable
fun MicButton(
    modifier: Modifier = Modifier,
    isSpeeching: Boolean,
    onClick: () -> Unit,
) {
    val containerColor =
        if (isSpeeching) HanasTheme.colorScheme.lightBlue.copy(alpha = 0.4f) else HanasTheme.colorScheme.blue
    val borderColor =
        if (isSpeeching) HanasTheme.colorScheme.blue.copy(alpha = 0.5f) else Color.Transparent

    Box(
        modifier
            .size(95.dp)
            .background(color = HanasTheme.colorScheme.gray100)
            .background(color = containerColor, shape = CircleShape)
            .border(color = borderColor, width = 2.dp, shape = CircleShape)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center,
    ) {
        when (isSpeeching) {
            true -> {
                Icon(
                    modifier = Modifier.size(30.dp),
                    imageVector = ImageVector.vectorResource(R.drawable.ic_arrow_up),
                    contentDescription = null,
                    tint = HanasTheme.colorScheme.blue,
                )
            }

            false -> {
                Icon(
                    modifier = Modifier.size(36.dp),
                    imageVector = ImageVector.vectorResource(R.drawable.ic_mic),
                    contentDescription = null,
                    tint = HanasTheme.colorScheme.white,
                )
            }
        }
    }
}
