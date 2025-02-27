package com.example.hanas.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

interface ChatGptRepository {
    suspend fun sendMessage(message: String): Result<String>
}

class ChatGptRepositoryImpl : ChatGptRepository {
    override suspend fun sendMessage(message: String): Result<String> {
        return withContext(Dispatchers.IO) {
            return@withContext Result.success("Thanks for messaging me!")
        }
    }
}
