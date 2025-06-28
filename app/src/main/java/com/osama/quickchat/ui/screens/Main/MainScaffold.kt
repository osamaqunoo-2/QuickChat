package com.osama.quickchat.ui.screens.Main
// ui/screens/Main/MainScaffold.kt

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.osama.quickchat.navigation.AppInnerNavigation
import com.osama.quickchat.ui.components.BottomBar

@Composable
fun MainScaffold() {
    val navController = rememberNavController()
    val currentDestination = navController.currentBackStackEntryAsState().value?.destination?.route

    val shouldShowBottomBar = currentDestination !in listOf("chat")
    Scaffold(
        bottomBar = {
            if (shouldShowBottomBar) {
                BottomBar(navController = navController)
            }
        }
    ) { paddingValues ->
        AppInnerNavigation(navController = navController, paddingValues = paddingValues)
    }
}
