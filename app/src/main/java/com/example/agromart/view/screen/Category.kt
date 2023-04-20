package com.example.agromart.view.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.example.agromart.R
import com.example.agromart.navigation.AgroMartScreen
import com.example.agromart.ui.theme.Green

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Category(modifier: Modifier, navHostController: NavHostController) {
    Scaffold(topBar = {
        TopAppBar(title = { /*TODO*/ }, actions = {
            Image(
                painter = painterResource(id = R.drawable.sales),
                contentDescription = null,
                modifier.size(50.dp).padding(10.dp).clickable { navHostController.navigate(AgroMartScreen.SALES_PAGE.name) }
            )
            Spacer(modifier = modifier.width(20.dp))
            Image(
                painter = painterResource(id = R.drawable.delivery_box),
                contentDescription = null,
                modifier.size(50.dp).padding(10.dp).clickable { navHostController.navigate(AgroMartScreen.LIST_OF_PROD.name) }
            )
        })
    }) {
        Column(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxSize()
                .padding(it),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Choose Category",
                fontSize = 40.sp,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge.copy(color = Green),
                modifier = Modifier.padding(0.dp, 10.dp)
            )

            Spacer(modifier = Modifier.height(100.dp));

            Row {

                Box(modifier = Modifier
                    .clickable { navHostController.navigate("${AgroMartScreen.PRODUCT_DESCRIPTION_SCREEN.name}/Seeds") }
                    .height(160.dp)
                    .width(160.dp)
                    .padding(5.dp)
                    .background(Color.LightGray, shape = RoundedCornerShape(10.dp))
                    .padding(0.dp, 5.dp), contentAlignment = Alignment.Center) {

                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Image(
                            painter = painterResource(id = R.drawable.seedbag),
                            contentDescription = "Seeds",
                            modifier = Modifier
                                .height(100.dp)
                                .padding(20.dp, 1.dp)
                                .width(100.dp),
                            alignment = Alignment.Center
                        );
                        Text(
                            text = "Seeds",
                            fontSize = 20.sp,
                            textAlign = TextAlign.Center
                        )
                    }

                }

                Box(modifier = Modifier
                    .clickable { navHostController.navigate(AgroMartScreen.PRODUCT_DESCRIPTION_SCREEN.name + "/Fertilizers") }
                    .height(160.dp)
                    .width(160.dp)
                    .padding(5.dp)
                    .background(Color.LightGray, shape = RoundedCornerShape(10.dp))
                    .padding(0.dp, 5.dp), contentAlignment = Alignment.Center) {

                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Image(
                            painter = painterResource(id = R.drawable.fertilizer),
                            contentDescription = "Fertilizers",
                            modifier = Modifier
                                .height(100.dp)
                                .padding(20.dp, 1.dp)
                                .width(100.dp),
                            alignment = Alignment.Center
                        );
                        Text(
                            text = "Fertilizers",
                            fontSize = 20.sp,
                            textAlign = TextAlign.Center
                        )
                    }

                }

            }

            Spacer(modifier = Modifier.height(40.dp));

            Row {

                Box(modifier = Modifier
                    .clickable { navHostController.navigate(AgroMartScreen.PRODUCT_DESCRIPTION_SELLER_SCREEN_SECOND.name + "/Tools") }
                    .height(160.dp)
                    .width(160.dp)
                    .padding(5.dp)
                    .background(Color.LightGray, shape = RoundedCornerShape(10.dp))
                    .padding(0.dp, 5.dp), contentAlignment = Alignment.Center) {

                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Image(
                            painter = painterResource(id = R.drawable.spade),
                            contentDescription = "Tools",
                            modifier = Modifier
                                .height(100.dp)
                                .padding(20.dp, 1.dp)
                                .width(100.dp),
                            alignment = Alignment.Center
                        );
                        Text(
                            text = "Tools",
                            fontSize = 20.sp,
                            textAlign = TextAlign.Center
                        )
                    }

                }

                Box(modifier = Modifier
                    .clickable { navHostController.navigate(AgroMartScreen.PRODUCT_DESCRIPTION_SELLER_SCREEN_SECOND.name + "/Gears") }
                    .height(160.dp)
                    .width(160.dp)
                    .padding(5.dp)
                    .background(Color.LightGray, shape = RoundedCornerShape(10.dp))
                    .padding(0.dp, 5.dp), contentAlignment = Alignment.Center) {

                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Image(
                            painter = painterResource(id = R.drawable.protectiveclothing),
                            contentDescription = "Gears",
                            modifier = Modifier
                                .height(100.dp)
                                .padding(20.dp, 1.dp)
                                .width(100.dp),
                            alignment = Alignment.Center
                        );
                        Text(
                            text = "Gears",
                            fontSize = 20.sp,
                            textAlign = TextAlign.Center
                        )
                    }

                }

            }
        }
    }
}
