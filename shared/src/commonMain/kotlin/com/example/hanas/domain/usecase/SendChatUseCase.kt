package com.example.hanas.domain.usecase

import com.example.hanas.domain.entity.Message
import com.example.hanas.domain.entity.Role
import com.example.hanas.domain.repository.ChatGptRepository
import com.example.hanas.domain.repository.ChatHistoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

interface SendChatUseCase {
    suspend fun execute(
        chatId: String,
        message: String,
    ): Flow<Result<String>>
}

@OptIn(ExperimentalUuidApi::class)
class SendChatUseCaseImpl(
    private val historyRepository: ChatHistoryRepository,
    private val chatGptRepository: ChatGptRepository,
) : SendChatUseCase {
    override suspend fun execute(
        chatId: String,
        message: String,
    ): Flow<Result<String>> =
        flow {
            // チャット履歴取得
            val history =
                historyRepository.getHistory(Uuid.parse(chatId))
                    .getOrElse {
                        emit(Result.failure(it))
                        return@flow
                    }

            // 通信実行
            val responseMessage =
                chatGptRepository
                    .sendMessage(
                        history = history,
                        userMessage =
                            Message(
                                role = Role.User,
                                content = message,
                            ),
                    )
                    .getOrElse {
                        emit(Result.failure(it))
                        return@flow
                    }

            // チャット履歴更新
            historyRepository.saveHistory(Uuid.parse(chatId), history.plus(responseMessage))

            emit(Result.success(responseMessage.content))
        }
}
