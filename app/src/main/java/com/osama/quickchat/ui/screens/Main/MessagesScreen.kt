package com.osama.quickchat.ui.screens.Main

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.delay
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.material3.Text
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.osama.quickchat.R
import com.osama.quickchat.data.model.MessageItem
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.res.stringResource
import com.osama.quickchat.ui.screens.components.MessageListItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import com.osama.quickchat.data.model.Conversation


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MessagesScreen(
    messages: List<Conversation>,
    onMessageClick: (Conversation) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize().background(Color.White)) {

        // ✅ Top App Bar
        TopAppBar(
            title = { Text(stringResource(id = R.string.messages_screen_title)) },
            navigationIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = stringResource(id = R.string.app_logo_desc),
                    modifier = Modifier
                        .padding(horizontal = 12.dp)
                        .size(32.dp)
                )
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.White,
                navigationIconContentColor = Color.Black,
                titleContentColor = Color.Black
            )
        )

        Box(modifier = Modifier.fillMaxSize()) {
            if (messages.isEmpty()) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(32.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_no_messages),
                        contentDescription = stringResource(id = R.string.no_messages_icon_desc),
                        modifier = Modifier.size(100.dp),
                        tint = Color.Unspecified // ✅ لا تلوّن الأيقونة
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = stringResource(id = R.string.no_messages),
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.Gray
                    )
                }
            } else {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(messages) { message ->
                        MessageListItem(message = message, onClick = { onMessageClick(message) })

//                        MessageListItem(message = message)
                    }
                }
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun MessagesScreenPreview_Empty() {
    MessagesScreen(messages = emptyList(), onMessageClick = {})
}
