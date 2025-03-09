package com.example.hanas.domain.repository

import com.example.hanas.domain.entity.Message

interface ChatGptRepository {
    suspend fun sendMessage(
        history: List<Message>,
        userMessage: Message,
    ): Result<Message>
}
