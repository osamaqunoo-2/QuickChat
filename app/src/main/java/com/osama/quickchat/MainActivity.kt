package com.osama.quickchat

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.fragment.app.FragmentActivity
import androidx.navigation.compose.rememberNavController
import com.osama.quickchat.navigation.MainNavigation
import com.osama.quickchat.ui.theme.QuickChatTheme
import com.osama.quickchat.utils.showLocalNotification
import kotlinx.coroutines.delay
import java.util.Locale

class MainActivity : FragmentActivity() {
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val currentLocale = Locale.getDefault()
            val isRtl = currentLocale.language == "ar"

            CompositionLocalProvider(
                LocalLayoutDirection provides if (isRtl) LayoutDirection.Rtl else LayoutDirection.Ltr
            ) {


                QuickChatTheme {
                    val navController = rememberNavController()
                    val context = applicationContext
                    LaunchedEffect(Unit) {
                        while (true) {
                            delay(60_000) // كل دقيقة

                            val conversation = com.osama.quickchat.data.LocalChatDataProvider.getConversations().randomOrNull()
                            if (conversation != null) {
                                val message = com.osama.quickchat.data.LocalChatDataProvider.addMessage(
                                    conversationId = conversation.id,
                                    text = "📩 Hello! How can I assist you today?",
                                    isSentByUser = false
                                )

                                // ✅ إظهار إشعار
                                context.showLocalNotification(conversation, message.text)
                            }
                        }
                    }
                    Surface {
                        MainNavigation(navController = navController)
                    }

                }
            }
        }
    }
}