package com.example.hanas.domain.entity

data class Message(
    val role: Role,
    val content: String,
)

enum class Role(val value: String) {
    Developer("developer"),
    Assistant("assistant"),
    User("user"),
    ;

    companion object {
        fun fromValue(value: String): Role {
            return entries.find { it.value == value } ?: throw IllegalArgumentException()
        }
    }
}
