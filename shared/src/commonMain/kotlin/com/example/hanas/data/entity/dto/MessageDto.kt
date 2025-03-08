package com.example.hanas.data.entity.dto

import com.example.hanas.domain.entity.Message
import com.example.hanas.domain.entity.Role
import kotlinx.serialization.Serializable

@Serializable
data class MessageDto(
    val role: String,
    val content: String,
) {
    fun toDomain(): Message {
        return Message(
            role = Role.fromValue(role),
            content = content,
        )
    }
}
