package com.example.agromart.view.screen

import android.content.Context
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.example.agromart.R
import com.example.agromart.navigation.AgroMartScreen
import com.example.agromart.ui.theme.Dark_Green
import com.example.agromart.ui.theme.Green

@Composable
fun BuySellScreen(modifier: Modifier, navHostController: NavHostController) {
    val context= LocalContext.current
    Column(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = " Choose your \n\n Domain ",
            fontSize = 40.sp,
            style = MaterialTheme.typography.titleLarge.copy(color = Green),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(80.dp))

        Box(modifier = Modifier
            .clickable { navHostController.navigate(AgroMartScreen.MAIN_SCREEN.name) }
            .height(250.dp)
            .width(250.dp)
            .padding(5.dp)
            .clip(CircleShape)
            .padding(0.dp, 5.dp), contentAlignment = Alignment.Center) {

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painter = painterResource(id = R.drawable.farmer),
                    contentDescription = "Buyer",
                    modifier = Modifier
                        .height(150.dp)
                        .padding(20.dp, 1.dp)
                        .width(150.dp),
                    alignment = Alignment.Center
                );
                Text(
                    text = "Buyer",
                    fontSize = 25.sp,
                    textAlign = TextAlign.Center
                )
            }

        }

        Spacer(modifier = Modifier.height(50.dp))

        Box(modifier = Modifier
            .clickable {
                val pref = context.getSharedPreferences("my_shared", Context.MODE_PRIVATE)
                if (!pref.getBoolean("isLogged", false)) {
                    navHostController.navigate(AgroMartScreen.LOGIN_SCREEN.name)
                } else {
                    navHostController.navigate(AgroMartScreen.CATEGORY_SCREEN.name)
                }
            }
            .height(250.dp)
            .width(250.dp)
            .padding(5.dp)
            .clip(CircleShape)
            .padding(0.dp, 5.dp), contentAlignment = Alignment.Center) {

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painter = painterResource(id = R.drawable.bussinessman),
                    contentDescription = "Seller",
                    modifier = Modifier
                        .height(150.dp)
                        .padding(20.dp, 1.dp)
                        .width(150.dp),
                    alignment = Alignment.Center
                );
                Text(
                    text = "Seller",
                    fontSize = 25.sp,
                    textAlign = TextAlign.Center
                )
            }

        }

    }
}

