package com.example.hanas

actual object Config {
    actual val OPENAI_API_TOKEN: String = BuildConfig.OPENAI_API_TOKEN
    actual val CHAT_GPT_API_BASE_URL: String = BuildConfig.CHAT_GPT_API_BASE_URL
}
