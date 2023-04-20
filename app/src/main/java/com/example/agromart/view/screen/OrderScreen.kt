package com.example.agromart.view.screen

import android.content.res.Resources.Theme
import android.graphics.BitmapFactory
import android.util.Base64
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.agromart.model.order.DataResponse
import com.example.agromart.ui.theme.Golden
import com.example.agromart.ui.theme.Green
import com.example.agromart.viewmodel.HomeViewModel
import com.example.agromart.viewmodel.OrderViewModel
import kotlin.math.log

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderScreen(
    modifier: Modifier,
    navHostController: NavHostController,
    viewModel: OrderViewModel = hiltViewModel()
) {
    val user by viewModel.userDetailResponse.collectAsState()
    var showDialog by remember {
        mutableStateOf(false)
    }
    var selectedQr by remember {
        mutableStateOf("")
    }
    LaunchedEffect(user) {
        viewModel.getUserProfile()
    }
    Scaffold(topBar = {
        TopAppBar(
            title = {
                Text(
                    text = "Order",
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
    }) {

        Log.d("hey", "OrderScreen: ${user}")
        LazyColumn(modifier = modifier.padding(it)) {
            items(user.data.ordersListed) {
                OrderItem(data = it) {
                    showDialog = true
                    selectedQr = it.qrcode
                }
            }
        }
    }

    if (showDialog) {
        AlertDialog(onDismissRequest = { showDialog = false }) {
            val byteArray = Base64.decode(selectedQr.split(",")[1], Base64.DEFAULT)
            val bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
            Image(bitmap = bitmap.asImageBitmap(), contentDescription = null,modifier.size(300.dp))
        }
    }
}

@Composable
fun OrderItem(data: DataResponse, onQrClicked: () -> Unit) {
    val byteArray = Base64.decode(data.qrcode.split(",")[1], Base64.DEFAULT)
    val bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Image(
                bitmap = bitmap.asImageBitmap(),
                contentDescription = null,
                colorFilter = ColorFilter.tint(
                    Green,
                    blendMode = BlendMode.ColorDodge
                ),
                modifier = Modifier.clickable { onQrClicked() }
            )
            Column {
                Text(text = data.itemId.name)
                Text(text = "Quantity: " + data.quantity.toString())
                Text(text = "Price: " + data.price.toString())
            }
            Text(
                text = data.status, style = MaterialTheme.typography.titleLarge.copy(
                    color = when (data.status) {
                        "Delivered" -> Green
                        "On the Way" -> Golden
                        else -> Color.Red
                    }, fontWeight = FontWeight.Bold
                )
            )
        }
    }
}