package com.example.agromart.view.screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
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
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.agromart.R
import com.example.agromart.navigation.AgroMartScreen
import com.example.agromart.ui.theme.Green
import com.example.agromart.view.component.AgroItem
import com.example.agromart.viewmodel.BuyerItemListViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BuyerItemListScreen(
    modifier: Modifier,
    navHostController: NavHostController,
    viewModel: BuyerItemListViewModel = hiltViewModel()
) {
    val itemList by viewModel.buyerItemList.collectAsState()
    var allButton by remember { mutableStateOf(true) }
    var seedsButton by remember { mutableStateOf(false) }
    var fertillizerButton by remember { mutableStateOf(false) }
    var toolsButton by remember { mutableStateOf(false) }
    var gearsButton by remember { mutableStateOf(false) }
    LaunchedEffect(key1 = itemList, block = {
        viewModel.getList()
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
                        painter = painterResource(id = R.drawable.store),
                        contentDescription = null,
                        modifier
                            .scale(2f)
                            .size(15.dp)
                    )
                    Spacer(modifier = modifier.width(20.dp))
                    Text(
                        text = "Shop",
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
            item {
                LazyRow {
                    item {
                        OutlinedButton(
                            onClick = {
                                seedsButton = false
                                allButton = true
                                gearsButton = false
                                fertillizerButton = false
                                toolsButton = false
                            },
                            shape = RoundedCornerShape(10.dp),
                            modifier = modifier.padding(10.dp),
                            colors = if (allButton) ButtonDefaults.buttonColors(
                                contentColor = Color.White,
                                containerColor = Green
                            ) else ButtonDefaults.buttonColors(
                                contentColor = Color.Black,
                                containerColor = Color.White
                            )
                        ) {
                            Text(text = "All")
                        }
                    }

                    item {
                        OutlinedButton(
                            onClick = {
                                seedsButton = true
                                allButton = false
                                gearsButton = false
                                fertillizerButton = false
                                toolsButton = false
                            },
                            shape = RoundedCornerShape(10.dp),
                            modifier = modifier.padding(10.dp),
                            colors = if (seedsButton) ButtonDefaults.buttonColors(
                                contentColor = Color.White,
                                containerColor = Green
                            ) else ButtonDefaults.buttonColors(
                                contentColor = Color.Black,
                                containerColor = Color.White
                            )
                        ) {
                            Text(text = "Seeds")
                        }
                    }

                    item {
                        OutlinedButton(
                            onClick = {
                                seedsButton = false
                                allButton = false
                                gearsButton = false
                                fertillizerButton = true
                                toolsButton = false
                            },
                            shape = RoundedCornerShape(10.dp),
                            modifier = modifier.padding(10.dp),
                            colors = if (fertillizerButton) ButtonDefaults.buttonColors(
                                contentColor = Color.White,
                                containerColor = Green
                            ) else ButtonDefaults.buttonColors(
                                contentColor = Color.Black,
                                containerColor = Color.White
                            )
                        ) {
                            Text(text = "Fertilizers")
                        }
                    }

                    item {
                        OutlinedButton(
                            onClick = {
                                seedsButton = false
                                allButton = false
                                gearsButton = false
                                fertillizerButton = false
                                toolsButton = true
                            },
                            shape = RoundedCornerShape(10.dp),
                            modifier = modifier.padding(10.dp),
                            colors = if (toolsButton) ButtonDefaults.buttonColors(
                                contentColor = Color.White,
                                containerColor = Green
                            ) else ButtonDefaults.buttonColors(
                                contentColor = Color.Black,
                                containerColor = Color.White
                            )
                        ) {
                            Text(text = "Tools")
                        }
                    }

                    item {
                        OutlinedButton(
                            onClick = {
                                seedsButton = false
                                allButton = false
                                gearsButton = true
                                fertillizerButton = false
                                toolsButton = false
                            },
                            shape = RoundedCornerShape(10.dp),
                            modifier = modifier.padding(10.dp),
                            colors = if (gearsButton) ButtonDefaults.buttonColors(
                                contentColor = Color.White,
                                containerColor = Green
                            ) else ButtonDefaults.buttonColors(
                                contentColor = Color.Black,
                                containerColor = Color.White
                            )
                        ) {
                            Text(text = "Gears")
                        }
                    }
                }
            }
            if (seedsButton)
                items(itemList.data.filter { it.productType == "Seeds" }) {
                    AgroItem(it) { navHostController.navigate("${AgroMartScreen.BUYING_SCREEN.name}/${it._id}") }
                }
            if (gearsButton) {
                items(itemList.data.filter { it.productType == "Gear" }) {
                    AgroItem(it) { navHostController.navigate("${AgroMartScreen.BUYING_SCREEN.name}/${it._id}") }
                }
            }
            if (fertillizerButton) {
                items(itemList.data.filter { it.productType == "fertillizer" }) {
                    AgroItem(it) { navHostController.navigate("${AgroMartScreen.BUYING_SCREEN.name}/${it._id}") }
                }
            }
            if (toolsButton) {
                items(itemList.data.filter { it.productType == "tools" }) {
                    AgroItem(it) { navHostController.navigate("${AgroMartScreen.BUYING_SCREEN.name}/${it._id}") }
                }
            }
            if (allButton) {
                items(itemList.data) {
                    Log.d("hey", "BuyerItemListScreen: ${it}")

                    AgroItem(it) { navHostController.navigate("${AgroMartScreen.BUYING_SCREEN.name}/${it._id}") }
                }
            }
        }
    }
}