package com.example.hanas

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
