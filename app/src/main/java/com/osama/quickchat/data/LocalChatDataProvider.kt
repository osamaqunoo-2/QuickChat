package com.osama.quickchat.data

import com.osama.quickchat.R
import com.osama.quickchat.data.model.ChatMessage
import com.osama.quickchat.data.model.Conversation

object LocalChatDataProvider {

    private val conversations = mutableListOf<Conversation>()
    private val messages = mutableListOf<ChatMessage>()

    private var conversationIdCounter = 1
    private var messageIdCounter = 1

    fun getConversations(): List<Conversation> = conversations

    fun getMessagesForConversation(conversationId: Int): List<ChatMessage> =
        messages.filter { it.conversationId == conversationId }

    fun addMessage(conversationId: Int, text: String, isSentByUser: Boolean): ChatMessage {
        val message = ChatMessage(
            id = messageIdCounter++,
            conversationId = conversationId,
            text = text,
            isSentByUser = isSentByUser,
            timestamp = System.currentTimeMillis(),
            isRead = isSentByUser
        )
        messages.add(message)

        val convoIndex = conversations.indexOfFirst { it.id == conversationId }
        if (convoIndex != -1) {
            val updated = conversations[convoIndex].copy(
                lastMessage = text,
                lastMessageTime = message.timestamp,
                hasUnread = !isSentByUser
            )
            conversations[convoIndex] = updated
        }

        return message
    }

    fun createConversation(
        userName: String,
        imageRes: Int = R.drawable.sample_profile
    ): Conversation {
        val conversation = Conversation(
            id = conversationIdCounter++,
            userName = userName,
            userImage = imageRes,
            lastMessage = "",
            lastMessageTime = System.currentTimeMillis(),
            hasUnread = false
        )
        conversations.add(conversation)
        return conversation
    }

    fun markConversationAsRead(conversationId: Int) {
        val convoIndex = conversations.indexOfFirst { it.id == conversationId }
        if (convoIndex != -1) {
            conversations[convoIndex] = conversations[convoIndex].copy(hasUnread = false)
        }

        messages.filter { it.conversationId == conversationId && !it.isSentByUser }
            .forEach { it.isRead = true }
    }

    fun getConversationById(id: Int?): Conversation? {
        return conversations.find { it.id == id }
    }

    fun createConversationWithWelcomeMessage(
        userName: String,
        imageRes: Int = R.drawable.sample_profile,
        welcomeText: String = "Hello! How can I assist you today? I'm here to help with anything you need."


    ): Conversation {
        var conversation = getConversationByUserName(userName)

        if (conversation == null) {
            conversation = createConversation(userName, imageRes)
            addMessage(conversation.id, welcomeText, isSentByUser = false)
        }

        return conversation
    }

    fun getConversationByUserName(userName: String): Conversation? {
        return conversations.find { it.userName == userName }
    }
    fun getUnreadMessagesCount(): Int {
        return messages.count { !it.isSentByUser && !it.isRead }
    }
    fun hasUnreadMessages(): Boolean {
        return messages.any { !it.isSentByUser && !it.isRead }
    }
    fun resetAll() {
        conversations.clear()
        messages.clear()
        conversationIdCounter = 1
        messageIdCounter = 1
    }
}
