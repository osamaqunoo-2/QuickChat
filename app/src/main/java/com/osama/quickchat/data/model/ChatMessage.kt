package com.osama.quickchat.data.model

data class ChatMessage(
    val id: Int,
    val conversationId: Int,
    val text: String,
    val isSentByUser: Boolean,
    val timestamp: Long,
    var isRead: Boolean = false
)