package com.osama.quickchat.ui.screens.Main


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*

import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.osama.quickchat.R

// ✅ نموذج بيانات الرسالة
data class ChatMessage(
    val id: Int,
    val text: String,
    val isSentByUser: Boolean
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(
    userName: String = "Osama Store",
    userImageRes: Int = R.drawable.sample_profile,
    messages: List<ChatMessage> = emptyList(),
    onBackClick: () -> Unit = {}
) {
    var messageText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // ✅ Top Bar
        TopAppBar(
            title = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = userImageRes),
                        contentDescription = stringResource(id = R.string.profile_image_desc),
                        modifier = Modifier
                            .size(36.dp)
                            .clip(CircleShape)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = userName)
                }
            },
            navigationIcon = {
                IconButton(onClick = onBackClick) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = stringResource(id = R.string.back)
                    )
                }
            }
        )

        // ✅ الرسائل
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(8.dp)
        ) {
            items(messages) { msg ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    horizontalArrangement = if (msg.isSentByUser) Arrangement.End else Arrangement.Start
                ) {
                    Text(
                        text = msg.text,
                        modifier = Modifier
                            .background(
                                if (msg.isSentByUser)
                                    MaterialTheme.colorScheme.primary.copy(alpha = 0.2f)
                                else
                                    Color.LightGray.copy(alpha = 0.3f),
                                shape = RoundedCornerShape(12.dp)
                            )
                            .padding(horizontal = 12.dp, vertical = 8.dp),
                        color = Color.Black
                    )
                }
            }
        }

        // ✅ إدخال الرسالة
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                value = messageText,
                onValueChange = { messageText = it },
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp),
                placeholder = { Text(stringResource(id = R.string.write_message)) },
                shape = RoundedCornerShape(20.dp),
                trailingIcon = {
                    if (messageText.isNotEmpty()) {
                        IconButton(onClick = { messageText = "" }) {
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_clear_24), // أيقونة الحذف (يجب إضافتها في drawable)
                                contentDescription = "Clear text",
                                tint = Color.Gray
                            )
                        }
                    }
                },
                singleLine = true,

//                keyboardOptions = androidx.compose.ui.text.input.KeyboardOptions.Default.copy(
//                    imeAction = androidx.compose.ui.text.input.ImeAction.Send
//                ),
                keyboardActions = KeyboardActions(
                    onSend = {
                        // تنفيذ الإرسال من الكيبورد
                        if (messageText.isNotBlank()) {
                            println("Send message: $messageText")
                            messageText = ""
                        }
                    }
                )
            )

            IconButton(
                onClick = {
                    if (messageText.isNotBlank()) {
                        println("Send message: $messageText")
                        messageText = ""
                    }
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Send,
                    contentDescription = stringResource(id = R.string.send),
                    tint = MaterialTheme.colorScheme.primary // ✅ لون التطبيق الأساسي
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ChatScreenPreview() {
    ChatScreen(
        messages = listOf(
            ChatMessage(1, "مرحبًا", false),
            ChatMessage(2, "هل المنتج متوفر؟", true),
            ChatMessage(3, "نعم، متاح الآن", false)
        )
    )
}
