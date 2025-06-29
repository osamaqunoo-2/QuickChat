package com.osama.quickchat.ui.screens.components

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.osama.quickchat.R
import com.osama.quickchat.data.model.Conversation
import com.osama.quickchat.data.model.MessageItem
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.concurrent.TimeUnit


@Composable
fun MessageListItem(
    message: Conversation,
    onClick: () -> Unit
) {
    val context = LocalContext.current

    val backgroundColor = if (message.hasUnread) Color.White else Color(0xFFF5F5F5)

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 6.dp)
            .clickable { onClick() },
        shadowElevation = 2.dp,
        shape = MaterialTheme.shapes.medium,
        color = backgroundColor
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = message.userImage),
                contentDescription = message.userName,
                contentScale = ContentScale.Crop, // يجعل الصورة تمتلئ الدائرة
                modifier = Modifier
                    .size(54.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = message.userName,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = message.lastMessage,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                )
            }
            Text(

                text = getTimeAgo(context, message.lastMessageTime),
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
            )
        }
    }
}

fun getTimeAgo(context: Context, timestamp: Long): String {
    val now = System.currentTimeMillis()
    val diff = now - timestamp

    val minutes = TimeUnit.MILLISECONDS.toMinutes(diff)
    val hours = TimeUnit.MILLISECONDS.toHours(diff)
    val days = TimeUnit.MILLISECONDS.toDays(diff)

    return when {
        diff < 60_000 -> context.getString(R.string.just_now)
        minutes < 60 -> context.getString(R.string.minutes_ago, minutes)
        hours < 24 -> context.getString(R.string.hours_ago, hours)
        days == 1L -> context.getString(R.string.yesterday)
        days < 7 -> context.getString(R.string.days_ago, days)
        else -> {
            val sdf = SimpleDateFormat(context.getString(R.string.date_format), Locale.getDefault())
            sdf.format(Date(timestamp))
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun PreviewMessageListItems() {
//    Column {
//        // غير مقروءة
//        MessageListItem(
//            message = MessageItem(
//                id = 1,
//                name = "Osama Store",
//                lastMessage = "مرحبًا بك 👋",
//                time = "10:45 ص",
//                imageRes = R.drawable.sample_profile,
//                isRead = false
//            ),
//            onClick = {}
//        )
//
//        // مقروءة
//        MessageListItem(
//            message = MessageItem(
//                id = 2,
//                name = "Fresh Shop",
//                lastMessage = "تم إرسال الطلب",
//                time = "10:30 ص",
//                imageRes = R.drawable.sample_profile,
//                isRead = true
//            ),
//            onClick = {}
//        )
//    }
//}

