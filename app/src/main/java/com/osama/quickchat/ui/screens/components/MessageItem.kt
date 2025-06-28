package com.osama.quickchat.ui.screens.components

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.osama.quickchat.R
import com.osama.quickchat.data.model.MessageItem


@Composable
fun MessageListItem(
    message: MessageItem,
    onClick: () -> Unit
) {
    val backgroundColor = if (message.isRead) Color.White else Color(0xFFF5F5F5)

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
                painter = painterResource(id = message.imageRes),
                contentDescription = message.name,
                contentScale = ContentScale.Crop, // يجعل الصورة تمتلئ الدائرة
                modifier = Modifier
                    .size(54.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = message.name,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = message.lastMessage,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                )
            }
            Text(
                text = message.time,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMessageListItems() {
    Column {
        // غير مقروءة
        MessageListItem(
            message = MessageItem(
                id = 1,
                name = "Osama Store",
                lastMessage = "مرحبًا بك 👋",
                time = "10:45 ص",
                imageRes = R.drawable.sample_profile,
                isRead = false
            ),
            onClick = {}
        )

        // مقروءة
        MessageListItem(
            message = MessageItem(
                id = 2,
                name = "Fresh Shop",
                lastMessage = "تم إرسال الطلب",
                time = "10:30 ص",
                imageRes = R.drawable.sample_profile,
                isRead = true
            ),
            onClick = {}
        )
    }
}

