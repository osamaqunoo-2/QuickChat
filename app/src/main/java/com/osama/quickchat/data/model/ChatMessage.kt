package com.osama.quickchat.data.model

data class ChatMessage(
    val id: Int,
    val text: String,
    val isSentByUser: Boolean,
    val timestamp: Long = System.currentTimeMillis(),
    var isRead: Boolean = false
)