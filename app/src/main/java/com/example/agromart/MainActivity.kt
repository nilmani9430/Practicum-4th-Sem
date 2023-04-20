package com.example.agromart

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.agromart.navigation.AgroMartNavHost
import com.example.agromart.ui.theme.AgroMartTheme
import com.example.agromart.view.screen.DeliveryAgentTrackingScreen
import com.example.agromart.view.screen.LoginScreen
import com.example.agromart.view.screen.MainScreen
import com.example.agromart.view.screen.ProductDescriptionSellerScreen
import com.example.agromart.view.screen.SalesPageScreen
import com.example.agromart.view.screen.SellerProductView
import com.example.agromart.view.screen.SplashScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    companion object {
        var mainActivity: MainActivity? = null

        fun getInstance(): MainActivity? = mainActivity
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        mainActivity = this
        super.onCreate(savedInstanceState)
        setContent {
            AgroMartTheme {
                val navHostController = rememberNavController()
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AgroMartNavHost(navHostController = navHostController, modifier = Modifier)
                }
            }
        }
    }
}
