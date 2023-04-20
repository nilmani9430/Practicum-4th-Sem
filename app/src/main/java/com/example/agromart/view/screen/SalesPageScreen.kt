package com.example.agromart.view.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.agromart.R
import com.example.agromart.model.order.DataResponse
import com.example.agromart.viewmodel.SalesViewModel
import java.text.SimpleDateFormat
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SalesPageScreen(
    modifier: Modifier,
    navHostController: NavHostController,
    viewModel: SalesViewModel = hiltViewModel()
) {
    val sales by viewModel.mySalesResponse.collectAsState()
    LaunchedEffect(key1 = sales, block = {
        viewModel.getSales()
    })
    Scaffold(topBar = {
        TopAppBar(
            title = {
                Row(
                    modifier = modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.sales),
                        contentDescription = null,
                        modifier
                            .scale(2f)
                            .size(15.dp)
                    )
                    Spacer(modifier = modifier.width(20.dp))
                    Text(
                        text = "Sales Log",
                        color = Color.Black,
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
                    )
                }
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
        LazyColumn(modifier.padding(it)) {
            items(sales.data) {
                SalesPageCard(it)
            }
        }
    }
}

@Composable
fun SalesPageCard(dataResponse: DataResponse) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = R.drawable.sprout),
                contentDescription = null,
                modifier = Modifier
                    .scale(0.2f)
                    .size(150.dp)
            )
            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 20.dp)
                ) {
                    Text(
                        dataResponse.itemId.name,
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.SemiBold)
                    )
                }
                Row {
                    Column {
                        Text("Quantity")
                        Text(dataResponse.quantity.toString())
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Text("Price")
                        Text(dataResponse.price.toString())
                    }
                }
            }
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp, 5.dp)
        )

        {
            Column {
                Row(verticalAlignment = Alignment.CenterVertically)
                {
                    val date: Date =
                        SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(dataResponse.createdAt)
                    val formattedDate: String = SimpleDateFormat("dd/MM/yyyy").format(date)
                    Text("Date of Sale : ")
                    Text(formattedDate)
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun SalesPageScreenPreview() {
    SalesPageScreen(Modifier, rememberNavController())
}

