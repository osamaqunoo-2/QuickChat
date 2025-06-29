package com.osama.quickchat.utils

import android.app.*
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import com.osama.quickchat.MainActivity
import com.osama.quickchat.R
import com.osama.quickchat.data.model.Conversation

fun Context.showLocalNotification(conversation: Conversation, message: String) {
    val channelId = "chat_channel"
    val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val channel = NotificationChannel(
            channelId,
            "Chat Messages",
            NotificationManager.IMPORTANCE_DEFAULT
        )
        notificationManager.createNotificationChannel(channel)
    }

    val intent = Intent(this, MainActivity::class.java).apply {
        putExtra("conversationId", conversation.id)
        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    }

    val pendingIntent = PendingIntent.getActivity(
        this,
        conversation.id,
        intent,
        PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
    )

    val notification = NotificationCompat.Builder(this, channelId)
        .setSmallIcon(R.drawable.ic_chat) // تأكد أن هذه الأيقونة موجودة في res/drawable
        .setContentTitle(conversation.userName)
        .setContentText(message)
        .setContentIntent(pendingIntent)
        .setAutoCancel(true)
        .build()

    notificationManager.notify(conversation.id, notification)
}
