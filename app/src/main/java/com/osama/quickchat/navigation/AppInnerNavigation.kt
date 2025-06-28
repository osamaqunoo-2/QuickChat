package com.osama.quickchat.navigation

import android.os.Build
import androidx.annotation.RequiresApi

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.osama.quickchat.ui.screens.Main.ChatScreen
import com.osama.quickchat.ui.screens.Main.HomeActivity
import com.osama.quickchat.ui.screens.Splash.AppSplashScreen
import com.osama.quickchat.ui.screens.Main.MessagesScreen
import com.osama.quickchat.ui.screens.Main.ProfileScreen
// navigation/AppInnerNavigation.kt

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.osama.quickchat.R
import com.osama.quickchat.data.model.MessageItem
import com.osama.quickchat.ui.screens.Main.ChatMessage
import com.osama.quickchat.ui.screens.Main.HomeActivity
import com.osama.quickchat.ui.screens.Main.ChatScreen
import com.osama.quickchat.ui.screens.Main.HomeActivity
import com.osama.quickchat.ui.screens.Splash.AppSplashScreen
import com.osama.quickchat.ui.screens.Main.MessagesScreen
import com.osama.quickchat.ui.screens.Main.ProfileScreen
@Composable
fun AppInnerNavigation(
    navController: NavHostController,
    paddingValues: PaddingValues
) {
    // قائمة رسائل وهمية مؤقتًا
    val messageList = listOf(
        MessageItem(1, "Osama Store", "أهلاً بك!", "10:15 ص", R.drawable.sample_profile),
        MessageItem(2, "Fresh Shop", "هل ما زال المنتج متوفر؟", "09:50 ص", R.drawable.sample_profile)
    )

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            HomeActivity(navController)
        }
//        composable("messages") {
//            MessagesScreen(
//                messages = messageList,
//                onMessageClick = { selectedMessage ->
//                    navController.navigate("chat")
//                }
//            )
//        }
//        composable("messages") {
//            val emptyMessages = emptyList<MessageItem>() // أو قائمة وهمية للتجربة
//            MessagesScreen(
//                messages = emptyMessages,
//                onMessageClick = { navController.navigate("chat") }
//            )
//        }
        composable("messages") {
            val fakeMessages = listOf(
                MessageItem(1, "Osama Store", "مرحبًا", "10:00 ص", R.drawable.sample_profile, isRead = false),
                MessageItem(2, "Fresh Shop", "هل ما زال المنتج متوفر؟", "9:45 ص", R.drawable.sample_profile, isRead = true),
                MessageItem(3, "Green Market", "تم التوصيل؟", "9:30 ص", R.drawable.sample_profile, isRead = true)
            )
            MessagesScreen(
                messages = fakeMessages,
                onMessageClick = { navController.navigate("chat") }
            )
        }
        composable("profile") {
            ProfileScreen(navController)
        }


        composable("chat") {
            // إلغاء الـ MainScaffold في هذه الشاشة
            ChatScreen(
                messages = listOf(
                    ChatMessage(1, "مرحبًا", false),
                    ChatMessage(2, "هل المنتج متوفر؟", true),
                    ChatMessage(3, "نعم، متاح الآن", false)
                ),
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}
