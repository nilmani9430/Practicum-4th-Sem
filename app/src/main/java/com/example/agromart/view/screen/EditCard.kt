package com.example.agromart.view.component

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Create
import androidx.compose.material.icons.rounded.Phone
import androidx.compose.material.icons.rounded.Place
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.agromart.R
import com.example.agromart.ui.theme.App_Gradient
import com.example.agromart.view.screen.ListOfProd

@Composable
fun EditCard(onPlaceClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
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
                        "Wheat",
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.SemiBold)
                    )
                    IconButton(onClick = onPlaceClick) {
                        Icon(
                            Icons.Rounded.Create,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier
                                .background(
                                    App_Gradient
                                )
                                .padding(10.dp)
                        )
                    }
                }
                Row {
                    Column {
                        Text("Quantity")
                        Text("120")
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Text("Price")
                        Text("120")
                    }
                }
            }
        }
    }
}


@Composable
fun EditCard2(onPlaceClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
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
                        "Rice",
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.SemiBold)
                    )
                    IconButton(onClick = onPlaceClick) {
                        Icon(
                            Icons.Rounded.Create,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier
                                .background(
                                    App_Gradient
                                )
                                .padding(10.dp)
                        )
                    }
                }
                Row {
                    Column {
                        Text("Quantity")
                        Text("10")
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Text("Price")
                        Text("130")
                    }
                }
            }
        }
    }
}
