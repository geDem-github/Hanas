package com.example.hanas.domain.repository

import com.example.hanas.domain.entity.Message

interface ChatGptRepository {
    suspend fun sendMessage(userMessage: Message): Result<Message>
}
