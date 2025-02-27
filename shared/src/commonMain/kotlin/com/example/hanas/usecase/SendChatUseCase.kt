package com.example.hanas.usecase

import com.example.hanas.repository.ChatGptRepository
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
                .sendMessage(message)
                .fold(
                    onSuccess = { emit(Result.success(it)) },
                    onFailure = { emit(Result.failure(it)) },
                )
        }
}
