package com.example.agromart.view.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.agromart.R
import com.example.agromart.navigation.AgroMartScreen
import com.example.agromart.view.component.AgroItem
import com.example.agromart.view.component.EditCard
import com.example.agromart.view.component.EditCard2

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListOfProd(modifier: Modifier, navHostController: NavHostController) {
    Scaffold(topBar = {
        TopAppBar(
            title = {
                Text(
                    text = "Product Catalog",
                    color = Color.Black,
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
                )
            },
            navigationIcon = {
                IconButton(onClick = { navHostController.popBackStack() }) {
                    Icon(
                        imageVector = Icons.Rounded.ArrowBack,
                        contentDescription = null
                    )
                }
            })
    })
    {
        LazyColumn(modifier.padding(it)) {
            items(2) {
                EditCard { navHostController.navigate(AgroMartScreen.SELLER_PRODUCT_VIEW.name) }
            }

            items(2) {
                EditCard2 { navHostController.navigate(AgroMartScreen.SELLER_PRODUCT_VIEW.name) }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ListOfProdPreview() {
    ListOfProd(Modifier, rememberNavController())
}