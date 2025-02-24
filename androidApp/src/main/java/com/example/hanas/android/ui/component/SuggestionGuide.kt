package com.example.hanas.android.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.hanas.android.R
import com.example.hanas.android.ui.theme.HanasTheme

@Composable
fun SuggestionGuide(
    modifier: Modifier = Modifier,
    sentence: String,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            modifier = Modifier.size(24.dp),
            imageVector = ImageVector.vectorResource(R.drawable.ic_exclamation_1),
            contentDescription = null,
            tint = HanasTheme.colorScheme.yellow.copy(alpha = 0.5f),
        )

        Column {
            Text(
                text = "言ってみてください：",
                style = HanasTheme.typography.xsRegular,
            )
            Text(
                text = "\"$sentence\"",
                style = HanasTheme.typography.sRegular,
            )
        }
    }
}
