package com.osama.quickchat.data.model

data class Message(
    val id: Int,
    val text: String,
    val timestamp: Long,
    val isSentByUser: Boolean,
    val isRead: Boolean = false
)