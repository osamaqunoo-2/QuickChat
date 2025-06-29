package com.osama.quickchat.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.osama.quickchat.R
import com.osama.quickchat.data.LocalChatDataProvider
import com.osama.quickchat.data.model.Conversation
import com.osama.quickchat.data.model.MessageItem
import com.osama.quickchat.ui.screens.Main.ChatMessage
import com.osama.quickchat.ui.screens.Main.ChatScreen
import com.osama.quickchat.ui.screens.Main.HomeActivity
import com.osama.quickchat.ui.screens.Main.MainScaffold
import com.osama.quickchat.ui.screens.Splash.AppSplashScreen
import com.osama.quickchat.ui.screens.Main.MessagesScreen
import com.osama.quickchat.ui.screens.Main.ProfileScreen


sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Home : Screen("home")
    object Messages : Screen("messages")
    object Profile : Screen("profile")
    object Chat : Screen("chat")

//    object OtpVerificationScreen : Screen("OtpVerificationScreen")
//    object CreateNewPasswordScreen : Screen("CreateNewPasswordScreen")
//    object OtpVerificationScreen : Screen("otp_verification/{phone}/{source}") {
//        fun createRoute(phone: String, source: String): String = "otp_verification/$phone/$source"
//    }
//    object CreateNewPasswordScreen : Screen("CreateNewPasswordScreen/{phone}/{otp}") {
//        fun createRoute(phone: String, otp: String): String = "CreateNewPasswordScreen/$phone/$otp"
//    }
}
@RequiresApi(Build.VERSION_CODES.M)
@Composable
fun MainNavigation(navController: NavHostController) {
    val messageList = listOf(
        MessageItem(1, "Osama Store", "أهلاً بك!", "10:15 ص", R.drawable.sample_profile),
        MessageItem(2, "Fresh Shop", "هل ما زال المنتج متوفر؟", "09:50 ص", R.drawable.sample_profile)
    )

    NavHost(navController = navController, startDestination = Screen.Splash.route) {
        composable(Screen.Splash.route) {
            AppSplashScreen(navController)
        }

        composable(Screen.Home.route) {
            HomeActivity(navController)
        }
        @Composable
        fun MessagesScreen(
            onMessageClick: (Conversation) -> Unit
        ) {
            val conversations = remember { LocalChatDataProvider.getConversations() }

            // تمرير conversations إلى LazyColumn مثلاً
        }
//        composable(Screen.Messages.route) {
//            MessagesScreen(
//                messages = messageList,
//                onMessageClick = { selectedMessage ->
//                    navController.navigate("chat/${selectedMessage.id}")
//                }
//            )
//        }

        composable(Screen.Profile.route) {
            ProfileScreen(navController)
        }



        composable("chat/{id}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")?.toIntOrNull()
            val conversation = id?.let { LocalChatDataProvider.getConversationById(it) }
//            val messages = id?.let { LocalChatDataProvider.getMessagesForConversation(it) } ?: emptyList()

            if (conversation != null) {
                ChatScreen(
                    conversation = conversation,
                    onBackClick = { navController.popBackStack() }
                )
            }
        }
        composable("welcome") {
            MainScaffold()
        }
    }
}
