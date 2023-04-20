package com.example.agromart.view.screen

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Looper
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material.icons.rounded.Warning
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
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.agromart.R
import com.example.agromart.model.Location
import com.example.agromart.navigation.AgroMartScreen
import com.example.agromart.ui.theme.Dark_Green
import com.example.agromart.ui.theme.Golden
import com.example.agromart.viewmodel.HomeViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority

private var locationCallback: LocationCallback? = null
var fusedLocationClient: FusedLocationProviderClient? = null
private var locationRequired = false

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    modifier: Modifier,
    navHostController: NavHostController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val weather by viewModel.weather.collectAsState()
    val context = LocalContext.current
    var currentLocation by remember {
        mutableStateOf(Location(0.toDouble(), 0.toDouble()))
    }
    var isLocationRequired by remember {
        mutableStateOf(true)
    }

    val launcherMultiplePermissions = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissionsMap ->
        val areGranted = permissionsMap.values.reduce { acc, next -> acc && next }
        if (areGranted) {
            locationRequired = true
            startLocationUpdates(context)
            Toast.makeText(context, "Permission Granted", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Permission Denied", Toast.LENGTH_SHORT).show()
        }
    }

    val permissions = arrayOf(
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.ACCESS_FINE_LOCATION
    )
    LaunchedEffect(Unit) {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
        locationCallback = object : LocationCallback() {
            override fun onLocationResult(p0: LocationResult) {
                for (lo in p0.locations) {
                    currentLocation = Location(lo.latitude, lo.longitude)
                    val pref =
                        context.getSharedPreferences("my_shared", Context.MODE_PRIVATE)
                    pref.edit().putFloat("lat", lo.latitude.toFloat()).apply()
                    pref.edit().putFloat("long", lo.longitude.toFloat()).apply()
                }
            }
        }
        if (permissions.all {
                ContextCompat.checkSelfPermission(
                    context,
                    it
                ) == PackageManager.PERMISSION_GRANTED
            }) {
            startLocationUpdates(context)
        } else {
            launcherMultiplePermissions.launch(permissions)
        }
    }
    LaunchedEffect(weather, currentLocation) {
        viewModel.fetchWeather(currentLocation.lat, currentLocation.long)
    }

    Scaffold(topBar = {
        TopAppBar(title = { }, navigationIcon = {
            IconButton(onClick = { navHostController.navigate(AgroMartScreen.DELIVERY_AGENT_SCREEN.name) }) {
                Icon(
                    painter = painterResource(id = R.drawable.round_dashboard_24),
                    tint = com.example.agromart.ui.theme.Green,
                    contentDescription = null
                )
            }
        }, actions = {
            IconButton(onClick = { navHostController.navigate(AgroMartScreen.PROFILE_SCREEN.name) }) {
                Icon(Icons.Rounded.Settings, tint = Color.LightGray, contentDescription = null)
            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Rounded.Notifications, tint = Golden, contentDescription = null)
            }
        })
    }) {
        Box(contentAlignment = Alignment.Center) {
            Box(
                modifier = modifier
                    .clip(RoundedCornerShape(topEnd = 20.dp))
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(com.example.agromart.ui.theme.Green, Dark_Green),
                        )
                    )
                    .height(300.dp)
                    .width(170.dp)
                    .align(Alignment.BottomStart)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Temp",
                        style = MaterialTheme.typography.titleLarge.copy(color = Color.White)
                    )
                    Text(
                        text = weather.data.temperature.toInt().toString(),
                        style = MaterialTheme.typography.displayLarge.copy(color = Color.White)
                    )
                }
            }
            Box(
                modifier = modifier
                    .clip(RoundedCornerShape(topStart = 20.dp))
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(com.example.agromart.ui.theme.Green, Dark_Green),
                        )
                    )
                    .height(300.dp)
                    .width(170.dp)
                    .align(Alignment.BottomEnd)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Wind",
                        style = MaterialTheme.typography.titleLarge.copy(color = Color.White)
                    )
                    Text(
                        text = weather.data.windspeed.toInt().toString(),
                        style = MaterialTheme.typography.displayLarge.copy(color = Color.White)
                    )
                }
            }
            Canvas(modifier = modifier.fillMaxSize()) {
                val canvasWidth = size.width
                val canvasHeight = size.height
                drawLine(
                    start = Offset(x = canvasWidth / 2, y = (canvasHeight / 2).minus(240)),
                    end = Offset(x = canvasWidth / 2, y = canvasHeight),
                    brush = Brush.linearGradient(
                        colors = listOf(com.example.agromart.ui.theme.Green, Dark_Green),
                        tileMode = TileMode.Decal
                    ),
                    strokeWidth = 40F
                )
            }
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier
                    .padding(it)
                    .fillMaxSize()
                    .offset(0.dp, -180.dp),
                horizontalArrangement = Arrangement.Center,
                verticalArrangement = Arrangement.Center
            ) {
                item {
                    Card(
                        shape = RoundedCornerShape(topEnd = 60.dp, bottomStart = 60.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
                        modifier = modifier
                            .height(180.dp)
                            .padding(end = 1.dp, start = 20.dp, bottom = 1.dp),
                        onClick = { navHostController.navigate(AgroMartScreen.MY_FARM.name) }
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                            modifier = modifier.fillMaxSize()
                        ) {
                            Image(
                                painterResource(id = R.drawable.wheat),
                                contentDescription = null,
                                modifier.size(100.dp)
                            )
                            Text(text = "My Farm", style = MaterialTheme.typography.titleMedium)
                        }
                    }
                }
                item {
                    Card(
                        shape = RoundedCornerShape(topStart = 60.dp, bottomEnd = 60.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        modifier = modifier
                            .height(180.dp)
                            .padding(end = 20.dp, start = 1.dp, bottom = 1.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
                        onClick = { navHostController.navigate(AgroMartScreen.BUYER_ITEM_LIST_SCREEN.name) }
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                            modifier = modifier.fillMaxSize()
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.store),
                                contentDescription = null,
                                modifier.size(100.dp)
                            )
                            Text(text = "Shop", style = MaterialTheme.typography.titleMedium)
                        }
                    }
                }
                item {
                    Card(
                        shape = RoundedCornerShape(bottomEnd = 60.dp, topStart = 60.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        modifier = modifier
                            .height(180.dp)
                            .padding(start = 20.dp, end = 1.dp, top = 1.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
                        onClick = { navHostController.navigate(AgroMartScreen.BLOG_SCREEN.name) }
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                            modifier = modifier.fillMaxSize()
                        ) {
                            Image(
                                painterResource(id = R.drawable.newspaper),
                                contentDescription = null,
                                modifier.size(100.dp)
                            )
                            Text(text = "Blog", style = MaterialTheme.typography.titleMedium)
                        }
                    }
                }
                item {
                    Card(
                        shape = RoundedCornerShape(topEnd = 60.dp, bottomStart = 60.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        modifier = modifier
                            .height(180.dp)
                            .padding(end = 20.dp, start = 1.dp, top = 1.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
                        onClick = { navHostController.navigate(AgroMartScreen.CHAT_BOT_SCREEN.name) }
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                            modifier = modifier.fillMaxSize()
                        ) {
                            Image(
                                painterResource(id = R.drawable.robot),
                                contentDescription = null,
                                modifier.size(100.dp)
                            )
                            Text(text = "Chatbot", style = MaterialTheme.typography.titleMedium)
                        }
                    }
                }
            }
            Box(
                modifier
                    .offset(y = (-150).dp)
                    .clip(CircleShape)
                    .background(Golden)
                    .size(50.dp)
            )
        }
    }
}


private fun startLocationUpdates(context: Context) {
    locationCallback?.let {
        val locationRequest =
            LocationRequest.Builder(Priority.PRIORITY_BALANCED_POWER_ACCURACY, 10000).build()
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        fusedLocationClient?.requestLocationUpdates(
            locationRequest,
            it,
            Looper.getMainLooper()
        )
    }
}

