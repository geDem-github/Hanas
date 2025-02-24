package com.example.hanas.android.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.hanas.android.ui.theme.HanasTheme

data class ChatNavCardUiModel(
    val title: String,
    val emoji: String,
    val color: Color,
)

@Composable
fun ChatNavCard(
    modifier: Modifier = Modifier,
    uiModel: ChatNavCardUiModel,
) {
    Row(
        modifier =
            modifier
                .heightIn(min = 75.dp)
                .background(HanasTheme.colorScheme.secondaryBackground)
                .padding(vertical = 12.dp, horizontal = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        GradientEmojiContainer(
            modifier = Modifier.size(48.dp),
            emoji = uiModel.emoji,
            color = uiModel.color,
        )

        Text(
            text = uiModel.title,
            style = HanasTheme.typography.sBold,
            color = HanasTheme.colorScheme.primaryText,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}
