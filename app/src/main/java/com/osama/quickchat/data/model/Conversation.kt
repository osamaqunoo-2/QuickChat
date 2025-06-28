package com.osama.quickchat.data.model

data class Conversation(
    val id: Int,
    val userName: String,
    val userImageRes: Int,
    val messages: MutableList<Message> = mutableListOf(),
    var lastMessage: String = "",
    var lastMessageTime: Long = 0,
    var hasUnread: Boolean = false
)