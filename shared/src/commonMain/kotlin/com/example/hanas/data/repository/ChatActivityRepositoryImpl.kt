package com.example.hanas.data.repository

import com.example.hanas.domain.entity.Message
import com.example.hanas.domain.entity.Role
import com.example.hanas.domain.entity.languageActivity.ChatActivity
import com.example.hanas.domain.repository.ChatActivityRepository
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
class ChatActivityRepositoryImpl : ChatActivityRepository {
    private val activities =
        listOf(
            ChatActivity(
                id = Uuid.parse("3e8d913a-03c7-4110-872d-f36754229234"),
                title = "旅行",
                emoji = "✈️",
                color = 0xFF4682B4,
                initialAiMessageOptions =
                    listOf(
                        Message(role = Role.Assistant, content = "What is your dream travel destination?"),
                        Message(role = Role.Assistant, content = "Tell me about your best travel experience."),
                        Message(role = Role.Assistant, content = "Do you prefer adventure or relaxation on trips?"),
                        Message(role = Role.Assistant, content = "How do you plan your travel itineraries?"),
                        Message(role = Role.Assistant, content = "What cultural experiences do you seek when traveling?"),
                    ),
            ),
            ChatActivity(
                id = Uuid.random(),
                title = "言語",
                emoji = "🗣️",
                color = 0xFFFF6347,
                initialAiMessageOptions =
                    listOf(
                        Message(role = Role.Assistant, content = "What languages are you interested in learning?"),
                        Message(role = Role.Assistant, content = "How do you practice speaking new languages?"),
                        Message(role = Role.Assistant, content = "What language do you find most challenging?"),
                        Message(role = Role.Assistant, content = "Do you enjoy language exchange meetups?"),
                        Message(role = Role.Assistant, content = "How has learning a new language impacted your life?"),
                    ),
            ),
            ChatActivity(
                id = Uuid.random(),
                title = "文化",
                emoji = "🎎",
                color = 0xFF8A2BE2,
                initialAiMessageOptions =
                    listOf(
                        Message(role = Role.Assistant, content = "What aspects of your culture do you cherish?"),
                        Message(role = Role.Assistant, content = "How do you celebrate cultural traditions?"),
                        Message(role = Role.Assistant, content = "Can you share a cultural festival you enjoy?"),
                        Message(role = Role.Assistant, content = "What cultural differences interest you the most?"),
                        Message(role = Role.Assistant, content = "How does culture influence your daily life?"),
                    ),
            ),
            ChatActivity(
                id = Uuid.random(),
                title = "技術",
                emoji = "💻",
                color = 0xFF00BFFF,
                initialAiMessageOptions =
                    listOf(
                        Message(role = Role.Assistant, content = "What technological gadget can you not live without?"),
                        Message(role = Role.Assistant, content = "How do you think technology will evolve in the next decade?"),
                        Message(role = Role.Assistant, content = "Do you enjoy using new tech products?"),
                        Message(role = Role.Assistant, content = "What app or software makes your life easier?"),
                        Message(role = Role.Assistant, content = "How do you balance technology use in daily life?"),
                    ),
            ),
            ChatActivity(
                id = Uuid.random(),
                title = "音楽",
                emoji = "🎵",
                color = 0xFFFF69B4,
                initialAiMessageOptions =
                    listOf(
                        Message(role = Role.Assistant, content = "Who is your favorite music artist?"),
                        Message(role = Role.Assistant, content = "What genre of music do you listen to the most?"),
                        Message(role = Role.Assistant, content = "Do you play any musical instruments?"),
                        Message(role = Role.Assistant, content = "Have you been to any live concerts recently?"),
                        Message(role = Role.Assistant, content = "How does music influence your mood?"),
                    ),
            ),
            ChatActivity(
                id = Uuid.random(),
                title = "映画",
                emoji = "🎬",
                color = 0xFFDC143C,
                initialAiMessageOptions =
                    listOf(
                        Message(role = Role.Assistant, content = "What is your favorite movie of all time?"),
                        Message(role = Role.Assistant, content = "Do you prefer watching movies at home or in a theater?"),
                        Message(role = Role.Assistant, content = "Which movie genre do you enjoy the most?"),
                        Message(role = Role.Assistant, content = "Can you recommend a good film?"),
                        Message(role = Role.Assistant, content = "Who is your favorite actor or actress?"),
                    ),
            ),
            ChatActivity(
                id = Uuid.random(),
                title = "料理",
                emoji = "🍳",
                color = 0xFFFFA500,
                initialAiMessageOptions =
                    listOf(
                        Message(role = Role.Assistant, content = "What is your signature dish?"),
                        Message(role = Role.Assistant, content = "Do you enjoy cooking at home?"),
                        Message(role = Role.Assistant, content = "What cuisine do you love the most?"),
                        Message(role = Role.Assistant, content = "Have you tried any new recipes recently?"),
                        Message(role = Role.Assistant, content = "How do you experiment with flavors in your cooking?"),
                    ),
            ),
            ChatActivity(
                id = Uuid.random(),
                title = "科学",
                emoji = "🔬",
                color = 0xFF32CD32,
                initialAiMessageOptions =
                    listOf(
                        Message(role = Role.Assistant, content = "What scientific discovery fascinates you the most?"),
                        Message(role = Role.Assistant, content = "How do you think science impacts our daily lives?"),
                        Message(role = Role.Assistant, content = "Do you enjoy reading about space and astronomy?"),
                        Message(role = Role.Assistant, content = "Which field of science do you find most interesting?"),
                        Message(role = Role.Assistant, content = "How do you stay updated with scientific news?"),
                    ),
            ),
            ChatActivity(
                id = Uuid.random(),
                title = "道案内",
                emoji = "🗺️",
                color = 0xFF008080,
                initialAiMessageOptions =
                    listOf(
                        Message(role = Role.Assistant, content = "Could you tell me how to get to the nearest station?"),
                        Message(role = Role.Assistant, content = "What landmarks should I look for on the way?"),
                        Message(role = Role.Assistant, content = "Is there a shortcut to reach the destination?"),
                        Message(role = Role.Assistant, content = "How long does it take to walk there?"),
                        Message(role = Role.Assistant, content = "Can you provide directions in detail?"),
                    ),
            ),
            ChatActivity(
                id = Uuid.random(),
                title = "学校",
                emoji = "🏫",
                color = 0xFFDA70D6,
                initialAiMessageOptions =
                    listOf(
                        Message(role = Role.Assistant, content = "What subjects do you enjoy studying at school?"),
                        Message(role = Role.Assistant, content = "How do you prepare for exams?"),
                        Message(role = Role.Assistant, content = "Do you participate in any school clubs?"),
                        Message(role = Role.Assistant, content = "What is your favorite memory from school?"),
                        Message(role = Role.Assistant, content = "How do you balance study and leisure at school?"),
                    ),
            ),
            ChatActivity(
                id = Uuid.random(),
                title = "病院",
                emoji = "🏥",
                color = 0xFFCD5C5C,
                initialAiMessageOptions =
                    listOf(
                        Message(role = Role.Assistant, content = "Have you ever visited a hospital for a checkup?"),
                        Message(role = Role.Assistant, content = "What do you think about hospital facilities?"),
                        Message(role = Role.Assistant, content = "How do you feel about health care services?"),
                        Message(role = Role.Assistant, content = "Do you know any tips for a smooth hospital visit?"),
                        Message(role = Role.Assistant, content = "What should patients ask their doctors?"),
                    ),
            ),
            ChatActivity(
                id = Uuid.random(),
                title = "銀行",
                emoji = "🏦",
                color = 0xFFB8860B,
                initialAiMessageOptions =
                    listOf(
                        Message(role = Role.Assistant, content = "Do you find banking services user-friendly?"),
                        Message(role = Role.Assistant, content = "How do you manage your finances?"),
                        Message(role = Role.Assistant, content = "What is your experience with online banking?"),
                        Message(role = Role.Assistant, content = "Have you ever visited a bank in person?"),
                        Message(role = Role.Assistant, content = "What tips do you have for budgeting and saving money?"),
                    ),
            ),
            ChatActivity(
                id = Uuid.random(),
                title = "空港",
                emoji = "🛫",
                color = 0xFF1E90FF,
                initialAiMessageOptions =
                    listOf(
                        Message(role = Role.Assistant, content = "What do you like most about traveling through airports?"),
                        Message(role = Role.Assistant, content = "How do you pass time during a layover?"),
                        Message(role = Role.Assistant, content = "Do you prefer window or aisle seats?"),
                        Message(role = Role.Assistant, content = "What is your experience with airport security?"),
                        Message(role = Role.Assistant, content = "How do you prepare for a long flight?"),
                    ),
            ),
            ChatActivity(
                id = Uuid.random(),
                title = "駅",
                emoji = "🚉",
                color = 0xFF808080,
                initialAiMessageOptions =
                    listOf(
                        Message(role = Role.Assistant, content = "Do you often use the train station for your commute?"),
                        Message(role = Role.Assistant, content = "How do you navigate through busy stations?"),
                        Message(role = Role.Assistant, content = "What tips do you have for first-time visitors to a station?"),
                        Message(role = Role.Assistant, content = "Have you experienced any delays at the station?"),
                        Message(role = Role.Assistant, content = "How do you find the station environment?"),
                    ),
            ),
        )

    override fun getAll(): List<ChatActivity> {
        return activities
    }

    override fun get(id: Uuid): ChatActivity? {
        return activities.find { it.id == id }
    }
}
