// ui/components/BottomBar.kt
package com.osama.quickchat.ui.components

import AppRoute
import androidx.compose.foundation.background
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.osama.quickchat.R
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.draw.*
import androidx.compose.foundation.layout.*
import androidx.compose.ui.graphics.Color

data class BottomNavItem(val screen: AppRoute, val iconRes: Int, val label: String)
@Composable
fun BottomBar(navController: NavController) {
    val items = listOf(
        BottomNavItem(AppRoute.Home, R.drawable.ic_home, "Home"),
        BottomNavItem(AppRoute.Messages, R.drawable.ic_chat, "Messages"),
        BottomNavItem(AppRoute.Profile, R.drawable.ic_profile, "Profile"),
    )

    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry.value?.destination?.route

    NavigationBar(
        modifier = Modifier.shadow(4.dp),
        containerColor = Color.White // ← تأكيد صريح للون الخلفية
    ) {
        items.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.screen.route,
                onClick = {
                    navController.navigate(item.screen.route)
                },
                icon = {
                    Icon(
                        painter = painterResource(id = item.iconRes),
                        contentDescription = item.label,
                        modifier = Modifier.size(24.dp)
                    )
                },
                label = { Text(item.label) }
            )
        }
    }


}
