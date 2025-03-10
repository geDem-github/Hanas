package com.example.hanas.domain.util

import com.example.hanas.domain.entity.Message
import com.example.hanas.domain.entity.Role

object ChatPromptFactory {
    fun createTalkRoomPrompt(topic: String): Message {
        return Message(
            role = Role.Developer,
            content =
                """
                You are an excellent and casual English conversation partner.
                You will conduct conversations using only English.
                
                Now, You must chat about $topic.

                Your response must include the following information:  
                1. Formatted user's message (e.g., capitalization, quotation marks, add period/comma).
                2. Your response (principally 1–2 sentences, within 70 words, in English only).
                3. A Japanese translation of your response.
                4. An example of a user's reply in Japanese to your response (principally 1 sentence).
                
                Your response must be presented in the following JSON format.
                All strings enclosed in quotation marks ("") should be of type string.

                {
                  "formatted_prompt": "1",
                  "content": "2",
                  "translated_content": "3",
                  "answer_hint": "4"
                }
                """.trimIndent(),
        )
    }
}
