package com.example.hanas.android.ui.feature.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.hanas.android.ui.common.EventFlow
import com.example.hanas.android.ui.common.component.TopBar
import com.example.hanas.android.ui.common.component.TopBarActionButton
import com.example.hanas.android.ui.common.rememberEventFlow
import com.example.hanas.android.ui.feature.chat.component.CancelRecordButton
import com.example.hanas.android.ui.feature.chat.component.ChangeHintVisibilityButton
import com.example.hanas.android.ui.feature.chat.component.ChatFeed
import com.example.hanas.android.ui.feature.chat.component.ChatFeedComponentType
import com.example.hanas.android.ui.feature.chat.component.MicButton
import com.example.hanas.android.ui.theme.HanasTheme

@Composable
fun ChatScreen(
    navController: NavController,
    eventFlow: EventFlow<ChatScreenEvent> = rememberEventFlow(),
    uiState: ChatUiState = chatScreenPresenter(navController, eventFlow),
) {
    ChatScreen(
        chatFeedComponentTypes = uiState.chatFeedComponentTypes,
        onAppear = {
            eventFlow.tryEmit(ChatScreenEvent.OnAppear)
        },
        onClickPopBackButton = {
            eventFlow.tryEmit(ChatScreenEvent.OnClickPopBackButton)
        },
    )
}

@Composable
fun ChatScreen(
    chatFeedComponentTypes: List<ChatFeedComponentType>,
    onAppear: () -> Unit,
    onClickPopBackButton: () -> Unit,
) {
    val haptic = LocalHapticFeedback.current

    LaunchedEffect(Unit) { onAppear() }

    Scaffold(
        containerColor = HanasTheme.colorScheme.primaryBackground,
        topBar = {
            TopBar(
                modifier = Modifier.statusBarsPadding(),
                title = "フリートーク",
                leftActionButton =
                    TopBarActionButton(
                        icon = Icons.AutoMirrored.Default.ArrowBack,
                        onClick = {
                            haptic.performHapticFeedback(HapticFeedbackType.TextHandleMove)
                            onClickPopBackButton()
                        },
                    ),
            )
        },
    ) { innerPadding ->
        Box(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
        ) {
            // チャットフィード
            ChatFeed(
                modifier =
                    Modifier
                        .fillMaxSize()
                        .padding(horizontal = 12.dp, vertical = 24.dp)
                        .verticalScroll(rememberScrollState()),
                chatFeedComponentTypes = chatFeedComponentTypes,
            )

            Row(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(bottom = 24.dp)
                        .align(Alignment.BottomCenter),
                horizontalArrangement =
                    Arrangement.spacedBy(
                        space = 24.dp,
                        alignment = Alignment.CenterHorizontally,
                    ),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                // 録音停止ボタン
                CancelRecordButton(
                    modifier = Modifier.size(48.dp),
                    onClick = {},
                )

                // 録音ボタン
                MicButton(
                    modifier =
                        Modifier
                            .offset(y = (-15).dp)
                            .shadow(5.dp, shape = CircleShape)
                            .background(HanasTheme.colorScheme.primaryBackground),
                    isRecording = false,
                    onClick = {
                        haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                    },
                )

                // ヒント表示・非表示
                ChangeHintVisibilityButton(
                    modifier = Modifier.size(48.dp),
                    isVisible = true,
                    onClick = { },
                )
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    HanasTheme {
        ChatScreen(emptyList(), {}, {})
    }
}
