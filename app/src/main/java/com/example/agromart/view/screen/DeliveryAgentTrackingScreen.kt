package com.example.agromart.view.screen

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.agromart.model.map.MapRequest
import com.example.agromart.navigation.AgroMartScreen
import com.example.agromart.ui.theme.Green
import com.example.agromart.viewmodel.MapViewModel
import com.google.android.gms.maps.CameraUpdate
import com.google.android.gms.maps.GoogleMapOptions
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.PatternItem
import com.google.maps.android.PolyUtil
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.GoogleMapComposable
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.Polygon
import com.google.maps.android.compose.Polyline
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun DeliveryAgentTrackingScreen(
    modifier: Modifier,
    sellerId: String,
    buyerId: String,
    navHostController: NavHostController,
    viewModel: MapViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val request by viewModel.mapRequest.collectAsState()
    val response by viewModel.mapResponse.collectAsState()
    val pref =
        context.getSharedPreferences("my_shared", Context.MODE_PRIVATE)
    LaunchedEffect(key1 = Unit, block = {
        viewModel.onMapRequestChanged(
            MapRequest(
                pref.getFloat("lat", 0f).toString(),
                pref.getFloat("long", 0f).toString(),
                "643a6e7835868c426a784856",
                "643b7218f0b0e302fed392c3"
            )
        )
        viewModel.getMap()
        Log.d("hey", "DeliveryAgentTrackingScreen: ")
    })

    Scaffold {
        val list = PolyUtil.decode(
            response.data?.polyline?.points ?: ""
        )
        val singapore =
            LatLng(pref.getFloat("lat", 0f).toDouble(), pref.getFloat("long", 0f).toDouble())
        val cameraPositionState = rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(singapore, 10f)
        }

        Column {
            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                cameraPositionState = cameraPositionState,
            ) {
                Polyline(
                    points = list, color = Green
                )
//            Marker(
//                state = MarkerState(position = singapore),
//                title = "Singapore",
//                snippet = "Marker in Singapore"
//            )
            }
            Button(
                onClick = { navHostController.navigate(AgroMartScreen.MY_FARM.name) },
                colors = ButtonDefaults.buttonColors(containerColor = Green)
            ) {
                Text("My Order")
            }
        }

    }
}