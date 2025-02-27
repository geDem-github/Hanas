package com.example.hanas.module

import com.example.hanas.repository.ChatGptRepositoryImpl
import com.example.hanas.usecase.SendChatUseCaseImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val appModule =
    module {
        singleOf(::ChatGptRepositoryImpl) { bind<ChatGptRepositoryImpl>() }
        factoryOf(::SendChatUseCaseImpl) { bind<SendChatUseCaseImpl>() }
    }
