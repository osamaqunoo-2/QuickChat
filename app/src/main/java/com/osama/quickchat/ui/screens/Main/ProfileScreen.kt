package com.osama.quickchat.ui.screens.Main



import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.osama.quickchat.R
@Composable
fun ProfileScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(top = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        Image(
            painter = painterResource(id = R.drawable.sample_profile),
            contentDescription = "Profile Image",
            contentScale = ContentScale.Crop, // يجعل الصورة تمتلئ الدائرة
            modifier = Modifier
                .size(150.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.height(12.dp))

        Text(text = "Osama Qunoo", style = MaterialTheme.typography.titleMedium)
        Text(
            text = "+970599000000",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
        )

        Spacer(modifier = Modifier.height(32.dp))

        Column(modifier = Modifier.fillMaxWidth()) {
            ProfileOption(iconRes = R.drawable.info, labelRes = R.string.about_app) {}
            ProfileOption(iconRes = R.drawable.privecy, labelRes = R.string.privacy_policy) {}
            ProfileOption(iconRes = R.drawable.privecy, labelRes = R.string.terms_conditions) {}
            ProfileOption(iconRes = R.drawable.settings, labelRes = R.string.settings) {}
            ProfileOption(iconRes = R.drawable.logout, labelRes = R.string.logout, isLogout = true) {
                // Handle logout
            }
        }
    }
}
@Composable
fun ProfileOption(
    iconRes: Int,
    labelRes: Int,
    isLogout: Boolean = false,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = iconRes),
            contentDescription = stringResource(id = labelRes),
            tint = if (isLogout) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(24.dp) // 👈 هذا هو الحجم المناسب
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = stringResource(id = labelRes),
            style = MaterialTheme.typography.bodyLarge,
            color = if (isLogout) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onSurface
        )
    }
}

