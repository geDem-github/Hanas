package com.example.hanas.data.entity.dto.sendMessage.response

import com.example.hanas.data.entity.dto.ChoiceDto
import kotlinx.serialization.Serializable

@Serializable
data class SendMessageResponse(
    val choices: List<ChoiceDto>,
)
