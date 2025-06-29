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

import com.osama.quickchat.data.model.Product
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import com.osama.quickchat.data.LocalChatDataProvider
import com.osama.quickchat.navigation.Screen
import com.osama.quickchat.ui.screens.components.ProductCard



val sampleProducts = listOf(
    Product(1, "Apple", "Osama Store", R.drawable.sample_product),
    Product(2, "Orange", "Fresh Shop", R.drawable.sample_product),
    Product(3, "Banana", "Fruit Land", R.drawable.sample_product),
)

@Composable
fun ProductGrid(navController: NavHostController) {
    LazyVerticalGrid(columns = GridCells.Fixed(2), contentPadding = PaddingValues(8.dp)) {
        items(sampleProducts) { product ->
            ProductCard(product,   onMessageClick = {
                val conversation = LocalChatDataProvider.createConversationWithWelcomeMessage(
                    userName = product.merchant,
                    imageRes = R.drawable.sample_profile,
                    welcomeText ="Hello! How can I assist you today? I'm here to help with anything you need."
                )
                navController.navigate("chat/${conversation.id}")
            })
        }
    }
}
@Composable
fun TopBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White) // ✅ خلفية بيضاء
            .padding(horizontal = 16.dp, vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo",
                modifier = Modifier.size(40.dp) // ✅ كبرنا الشعار من 32 إلى 48
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = "QuickChat",
                style = MaterialTheme.typography.headlineSmall // ✅ تكبير الخط
            )
        }

        Icon(
            painter = painterResource(id = R.drawable.ic_notfication),
            contentDescription = "Notifications",
            modifier = Modifier.size(23.dp) // زيادة بسيطة لحجم أيقونة الإشعارات
        )
    }
}


@Composable
fun HomeActivity(navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White) // ✅ خلفية بيضاء لكل الصفحة
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            TopBar()
            Spacer(modifier = Modifier.height(8.dp))
            ProductGrid(navController
//                onMessageClick = {
//                    navController.navigate("chat")
//                }
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeActivity(navController = androidx.navigation.compose.rememberNavController())
}