package com.example.agromart.view.screen

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.BoxScopeInstance.align
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.RowScopeInstance.align
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.agromart.R
import com.example.agromart.model.product.Datum
import com.example.agromart.navigation.AgroMartScreen
import com.example.agromart.ui.theme.Green
import com.example.agromart.viewmodel.BuyingViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BuyingScreen(
    modifier: Modifier,
    navHostController: NavHostController,
    id: String,
    viewModel: BuyingViewModel = hiltViewModel()
) {
    val itemList by viewModel.buyerItemList.collectAsState()
    val order by viewModel.orderRequest.collectAsState()
    val orderResponse by viewModel.orderResponse.collectAsState()
    val isOrderPlaced by viewModel.orderPlaced.collectAsState()
    val context = LocalContext.current
    var dat by remember {
        mutableStateOf(Datum())
    }
    var showDialog by remember {
        mutableStateOf(false)
    }
    var count by remember { mutableStateOf(0) }

    LaunchedEffect(key1 = itemList, block = {
        viewModel.getItemDetail()
        dat = itemList.data.find { it._id == id } ?: Datum()
        viewModel.onOrderRequestChanged(
            order.copy(
                data =
                order.data.copy(sellerId = dat.sellerId)
            )
        )

        viewModel.onOrderRequestChanged(
            order.copy(
                data =
                order.data.copy(itemId = dat._id)
            )
        )
    })
    Column(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = painterResource(id = R.drawable.box),
            contentDescription = "Box",
            modifier = Modifier
                .height(130.dp)
                .padding(20.dp, 10.dp)
                .width(130.dp),
            alignment = Alignment.Center
        );

        Spacer(modifier = Modifier.height(10.dp));

        Text(
            text = dat.name,
            fontSize = 20.sp,
            textAlign = TextAlign.Center
        );

        Spacer(modifier = Modifier.height(30.dp));

        Text(
            text = dat.description,
            fontSize = 15.sp,
            textAlign = TextAlign.Center
        );

        Spacer(modifier = Modifier.height(30.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(
                text = dat.quantity.toString(),
                fontSize = 15.sp,
                textAlign = TextAlign.Left
            );
            Row(verticalAlignment = Alignment.CenterVertically) {


                Image(
                    painter = painterResource(id = R.drawable.plus),
                    contentDescription = "plus",
                    modifier = Modifier
                        .height(30.dp)
                        .width(30.dp)
                        .padding(2.dp, 2.dp)
                        .clickable {
                            if (dat.quantity > order.data.quantity)
                                viewModel.onOrderRequestChanged(
                                    orderRequest = order.copy(
                                        data = order.data.copy(
                                            quantity = order.data.quantity + 1
                                        )
                                    )
                                )
                        }
                    //                    .align(alignment = Alignment.End),

                );

                Text(
                    text = "Quantity: ${order.data.quantity}",
                    fontSize = 15.sp,
                    textAlign = TextAlign.Center
                );

                Image(
                    painter = painterResource(id = R.drawable.minus),
                    contentDescription = "minus",
                    modifier = Modifier
                        .height(27.dp)
                        .width(27.dp)
                        .padding(2.dp, 2.dp)
                        .clickable {

                            if (order.data.quantity >= 0)
                                viewModel.onOrderRequestChanged(
                                    orderRequest = order.copy(
                                        data = order.data.copy(
                                            quantity = order.data.quantity - 1
                                        )
                                    )
                                )
                        }
                    //                    .align(alignment = Alignment.End)

                );
            }
        }

        Spacer(modifier = Modifier.height(20.dp));

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {

            Text(
                text = "Price: " + dat.price.toString(),
                fontSize = 15.sp,
                textAlign = TextAlign.Left
            );
            Text(text = "Total Price: " + ((dat.price) * (order.data.quantity)).toString())
        }

        Spacer(modifier = Modifier.height(40.dp));

        Button(
            onClick = {
                val pref = context.getSharedPreferences("my_shared", Context.MODE_PRIVATE)
                if (!pref.getBoolean("isLogged", false)) {
                    navHostController.navigate(AgroMartScreen.LOGIN_SCREEN.name)
                } else {
                    viewModel.onOrderRequestChanged(
                        order.copy(
                            order.data.copy(
                                price = ((dat.price) * (order.data.quantity)),
                                sellerId = dat.sellerId,
                                itemId = dat._id
                            )
                        )
                    )
                    viewModel.placeOrder()
                }
            },
            shape = RoundedCornerShape(50.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Green)
        ) {
            Text(text = "Continue")
        }

    }

    if (isOrderPlaced) {
        viewModel.onOrderChanged(false)
        navHostController.navigate(AgroMartScreen.DELIVERY_AGENT_SCREEN.name + "/${dat.sellerId}/${orderResponse.data.buyerId}")
    }
}
