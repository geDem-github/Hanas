package com.example.hanas.data.entity.dto.sendMessage.request

import com.example.hanas.data.entity.dto.MessageDto
import kotlinx.serialization.Serializable

@Serializable
data class SendMessageRequest(
    val model: String,
    val store: Boolean,
    val messages: List<MessageDto>,
)
