package com.osama.quickchat.data.model

data class MessageItem(
    val id: Int,
    val name: String,
    val lastMessage: String,
    val time: String,
    val imageRes: Int,
    val isRead: Boolean = true // افتراضيًا مقروء
)