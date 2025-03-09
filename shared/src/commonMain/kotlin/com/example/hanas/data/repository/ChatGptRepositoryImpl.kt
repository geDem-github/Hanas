package com.example.hanas.data.repository

import com.example.hanas.Config
import com.example.hanas.data.api.ApiClient
import com.example.hanas.data.entity.dto.MessageDto
import com.example.hanas.data.entity.dto.sendMessage.request.SendMessageRequest
import com.example.hanas.data.entity.dto.sendMessage.response.SendMessageResponse
import com.example.hanas.domain.entity.Message
import com.example.hanas.domain.repository.ChatGptRepository
import io.github.aakira.napier.Napier
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

class ChatGptRepositoryImpl(
    apiClient: ApiClient,
) : ChatGptRepository {
    private val httpClient: HttpClient = apiClient.httpClient

    override suspend fun sendMessage(
        history: List<Message>,
        userMessage: Message,
    ): Result<Message> {
        return withContext(Dispatchers.IO) {
            try {
                val result: SendMessageResponse =
                    httpClient
                        .post(BASE_URL) {
                            contentType(ContentType.Application.Json)
                            bearerAuth(API_TOKEN)
                            setBody(
                                SendMessageRequest(
                                    model = CHAT_GPT_MODEL_NAME,
                                    store = false,
                                    messages =
                                        history
                                            .map {
                                                MessageDto(role = it.role.value, content = it.content)
                                            }
                                            .plus(
                                                MessageDto(
                                                    role = userMessage.role.value,
                                                    content = userMessage.content,
                                                ),
                                            ),
                                ),
                            )
                        }
                        .body()
                val responseMessage = result.choices[0].message.toDomain()
                Result.success(responseMessage)
            } catch (e: Exception) {
                Napier.e("ERROR", e)
                Result.failure(e)
            }
        }
    }

    companion object {
        const val CHAT_GPT_MODEL_NAME = "gpt-4o-mini"
        val BASE_URL = Config.CHAT_GPT_API_BASE_URL
        val API_TOKEN = Config.OPENAI_API_TOKEN
    }
}
