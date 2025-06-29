package com.osama.quickchat.data.model

data class Conversation(
    val id: Int,
    val userName: String,
    val userImage: Int, // Resource ID (مثلاً R.drawable.profile)
    var lastMessage: String,
    var lastMessageTime: Long,
    var hasUnread: Boolean = false
)