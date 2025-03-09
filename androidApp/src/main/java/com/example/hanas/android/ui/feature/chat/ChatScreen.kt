package com.example.hanas.android.ui.feature.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import com.example.hanas.android.ui.feature.chat.component.CancelSpeechButton
import com.example.hanas.android.ui.feature.chat.component.ChangeHintVisibilityButton
import com.example.hanas.android.ui.feature.chat.component.ChatFeed
import com.example.hanas.android.ui.feature.chat.component.MicButton
import com.example.hanas.android.ui.theme.HanasTheme

@Composable
fun ChatScreen(
    chatId: String,
    navController: NavController,
    eventFlow: EventFlow<ChatScreenEvent> = rememberEventFlow(),
    uiState: ChatUiState =
        chatScreenPresenter(
            navController = navController,
            eventFlow = eventFlow,
            chatId = chatId,
        ),
) {
    ChatScreen(
        uiState = uiState,
        onAppear = {
            eventFlow.tryEmit(ChatScreenEvent.OnAppear)
        },
        onClickPopBackButton = {
            eventFlow.tryEmit(ChatScreenEvent.OnClickPopBackButton)
        },
        onClickMicButton = {
            eventFlow.tryEmit(ChatScreenEvent.OnClickMicButton)
        },
        onClickStopSpeechButton = {
            eventFlow.tryEmit(ChatScreenEvent.OnClickCancelSpeechButton)
        },
        onClickHintButton = {
            eventFlow.tryEmit(ChatScreenEvent.OnClickHintButton)
        },
    )
}

@Composable
fun ChatScreen(
    uiState: ChatUiState,
    onAppear: () -> Unit,
    onClickPopBackButton: () -> Unit,
    onClickMicButton: () -> Unit,
    onClickStopSpeechButton: () -> Unit,
    onClickHintButton: () -> Unit,
) {
    val haptic = LocalHapticFeedback.current

    LaunchedEffect(Unit) { onAppear() }

    Scaffold(
        containerColor = HanasTheme.colorScheme.primaryBackground,
        topBar = {
            TopBar(
                modifier = Modifier.statusBarsPadding(),
                title = uiState.topic ?: "",
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
                        .verticalScroll(rememberScrollState())
                        .padding(horizontal = 12.dp)
                        .padding(top = 24.dp, bottom = 160.dp),
                isAiChatTextVisible = uiState.isAiChatTextVisible,
                isHintVisible = uiState.isHintVisible,
                chatFeedComponentTypes = uiState.chatFeedComponentTypes,
            )

            Row(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .height(130.dp)
                        .padding(bottom = 24.dp)
                        .align(Alignment.BottomCenter),
                horizontalArrangement =
                    Arrangement.spacedBy(
                        space = 24.dp,
                        alignment = Alignment.CenterHorizontally,
                    ),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                if (uiState.isSpeeching) {
                    // 音声入力停止ボタン
                    CancelSpeechButton(
                        modifier = Modifier.size(48.dp),
                        onClick = onClickStopSpeechButton,
                    )
                } else {
                    Spacer(Modifier.size(48.dp))
                }

                // マイクボタン
                MicButton(
                    modifier =
                        Modifier
                            .offset(y = (-15).dp)
                            .shadow(5.dp, shape = CircleShape)
                            .background(HanasTheme.colorScheme.primaryBackground),
                    isSpeeching = uiState.isSpeeching,
                    onClick = {
                        haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                        onClickMicButton()
                    },
                )

                // ヒント表示・非表示
                ChangeHintVisibilityButton(
                    modifier = Modifier.size(48.dp),
                    isVisible = uiState.isHintVisible,
                    onClick = onClickHintButton,
                )
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    HanasTheme {
        ChatScreen(
            uiState = TODO(),
            onAppear = TODO(),
            onClickPopBackButton = TODO(),
            onClickMicButton = TODO(),
            onClickStopSpeechButton = TODO(),
            onClickHintButton = TODO(),
        )
    }
}
