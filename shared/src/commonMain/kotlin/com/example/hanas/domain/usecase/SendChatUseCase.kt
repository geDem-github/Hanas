package com.example.hanas.domain.usecase

import com.example.hanas.domain.entity.Message
import com.example.hanas.domain.entity.Role
import com.example.hanas.domain.repository.ChatGptRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface SendChatUseCase {
    suspend fun execute(message: String): Flow<Result<String>>
}

class SendChatUseCaseImpl(
    private val chatGptRepository: ChatGptRepository,
) : SendChatUseCase {
    override suspend fun execute(message: String): Flow<Result<String>> =
        flow {
            chatGptRepository
                .sendMessage(
                    userMessage =
                        Message(
                            role = Role.User,
                            content = message,
                        ),
                )
                .fold(
                    onSuccess = { emit(Result.success(it.content)) },
                    onFailure = { emit(Result.failure(it)) },
                )
        }
}
