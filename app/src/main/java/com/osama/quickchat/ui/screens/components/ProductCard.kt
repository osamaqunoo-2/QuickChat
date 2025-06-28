package com.osama.quickchat.ui.screens.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.draw.shadow
import com.osama.quickchat.R
import com.osama.quickchat.data.model.Product
import com.osama.quickchat.ui.theme.Pink80
import com.osama.quickchat.ui.theme.Purple80

@Composable
fun ProductCard(
    product: Product,
    onMessageClick: () -> Unit,
    onDetailsClick: () -> Unit = {}
) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .height(220.dp)
            .shadow(4.dp, RoundedCornerShape(12.dp)),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = product.imageRes),
                contentDescription = stringResource(id = R.string.product_image_desc),
                modifier = Modifier
                    .height(100.dp)
                    .fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(text = product.name, style = MaterialTheme.typography.titleMedium)

            Text(
                text = stringResource(id = R.string.by_merchant, product.merchant),
                style = MaterialTheme.typography.bodySmall
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(id = R.string.see_details),
                        color = Pink80,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier
                            .padding(8.dp)
                    )

                    IconButton(onClick = onMessageClick) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_chat),
                            contentDescription = stringResource(id = R.string.chat),
                            tint = Purple80
                        )
                    }
                }


                IconButton(onClick = onMessageClick) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_chat),
                        contentDescription = stringResource(id = R.string.chat),
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProductCardPreview() {
    val sampleProduct = Product(
        id = 1,
        name = "Apple",
        merchant = "Osama Store",
        imageRes = R.drawable.sample_product
    )

    MaterialTheme {
        ProductCard(
            product = sampleProduct,
            onMessageClick = {},
            onDetailsClick = {}
        )
    }
}
