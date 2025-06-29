package com.osama.quickchat.ui.screens.Main
// ui/screens/Main/MainScaffold.kt

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.osama.quickchat.data.LocalChatDataProvider
import com.osama.quickchat.navigation.AppInnerNavigation
import com.osama.quickchat.ui.components.BottomBar
import kotlinx.coroutines.delay

@Composable
fun MainScaffold() {
    val navController = rememberNavController()
    val currentDestination = navController.currentBackStackEntryAsState().value?.destination?.route
    val unreadCount = remember { mutableStateOf(0) }

//    val shouldShowBottomBar = currentDestination !in listOf("chat")
    val shouldShowBottomBar = currentDestination?.startsWith("chat") != true
    LaunchedEffect(Unit) {
        while (true) {
            unreadCount.value = LocalChatDataProvider.getUnreadMessagesCount()
            delay(1000L)
        }
    }
    Scaffold(
        bottomBar = {
            if (shouldShowBottomBar) {

                BottomBar(
                    navController = navController,
                    unreadCount = unreadCount.value
                )
            }
        }
    ) { paddingValues ->
        AppInnerNavigation(navController = navController, paddingValues = paddingValues)
    }
}
