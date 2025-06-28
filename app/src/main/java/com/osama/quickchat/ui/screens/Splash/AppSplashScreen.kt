package com.osama.quickchat.ui.screens.Splash

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
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.osama.quickchat.R


@Composable
fun AppSplashScreen(navController: NavHostController) {
    val scale = remember { Animatable(0.5f) }
    val context = LocalContext.current

    // Animation and navigation effect
    LaunchedEffect(Unit) {
        scale.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = 1000,
                easing = FastOutSlowInEasing
            )
        )
        delay(2000) // Wait 2 more seconds

        navController.navigate("welcome") {
            popUpTo("splash") { inclusive = true }
        }


    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "QuickChat Logo",
                modifier = Modifier
                    .size(150.dp)
                    .scale(scale = scale.value)
            )
        }

        Text(
            text =  stringResource(id = R.string.powered_by_osama),
            color = Color.Gray,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 24.dp)
        )
    }
}

@Composable
fun SplashContent(scale: Float = 1f) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)

            .padding(16.dp),

        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "QuickChat Logo",
                modifier = Modifier
                    .size(150.dp)
                    .scale(scale)
            )
        }

        Text(
            text =  stringResource(id = R.string.powered_by_osama),
            color = Color.Gray,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 24.dp)
        )
    }
}

@Preview
@Composable
fun SplashContentPreview() {
    SplashContent(scale = 1f)
}