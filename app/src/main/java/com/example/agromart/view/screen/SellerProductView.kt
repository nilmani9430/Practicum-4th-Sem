package com.example.agromart.view.screen

import android.os.Build
import android.widget.Button
import android.widget.DatePicker
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CalendarToday
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerFormatter
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key.Companion.Calendar
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.agromart.R
import com.example.agromart.navigation.AgroMartScreen
import com.example.agromart.ui.theme.App_Gradient
import com.example.agromart.ui.theme.Green
import com.example.agromart.view.component.AgroMartTextField
import com.example.agromart.viewmodel.ProductViewModel
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.Date

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SellerProductView(
    modifier: Modifier,
    navHostController: NavHostController,
    viewModel: ProductViewModel = hiltViewModel()
) {
    val productRequest by viewModel.productRequest.collectAsState()
    var showDatePicker by remember {
        mutableStateOf(false)
    }
    val datePickerState = rememberDatePickerState()
    Scaffold(topBar = {
        TopAppBar(title = { }, navigationIcon = {
            IconButton(onClick = { navHostController.popBackStack() }) {
                Icon(imageVector = Icons.Rounded.ArrowBack, contentDescription = "Back")
            }
        })
    }) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .padding(it)
                .fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.editinfo),
                contentDescription = "editInfo",
                modifier = Modifier
                    .height(100.dp)
                    .padding(20.dp, 5.dp)
                    .width(100.dp),
                alignment = Alignment.Center
            );
            Spacer(modifier = Modifier.height(15.dp));
            Text(
                text = "Edit Product",
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.height(50.dp));

            var nameDefault by remember { mutableStateOf("Default text") }

            TextField(
                value = nameDefault,
                onValueChange = { nameDefault = it },
                label = { Text("Name") },
                modifier = Modifier
                    .background(Color.White)
                    .border(0.dp, Color.White, RoundedCornerShape(8.dp))
                    .padding(10.dp)
            )

            var quantityDefault by remember { mutableStateOf("Default text") }

            TextField(
                value = quantityDefault,
                onValueChange = { quantityDefault = it },
                label = { Text("Quantity") },
                modifier = Modifier
                    .background(Color.White)
                    .border(0.dp, Color.White, RoundedCornerShape(8.dp))
                    .padding(10.dp)
            )

            var mfdDefault by remember { mutableStateOf("Default text") }

            TextField(
                value = mfdDefault,
                onValueChange = { mfdDefault = it },
                label = { Text("MFD") },
                modifier = Modifier
                    .background(Color.White)
                    .border(0.dp, Color.White, RoundedCornerShape(8.dp))
                    .padding(10.dp)
            )

            var expiryDefault by remember { mutableStateOf("Default text") }

            TextField(
                value = expiryDefault,
                onValueChange = { expiryDefault = it },
                label = { Text("Expiry") },
                modifier = Modifier
                    .background(Color.White)
                    .border(0.dp, Color.White, RoundedCornerShape(8.dp))
                    .padding(10.dp)
            )

            var descDefault by remember { mutableStateOf("Default text") }

            TextField(
                value = descDefault,
                onValueChange = { descDefault = it },
                label = { Text("Description") },
                modifier = Modifier
                    .background(Color.White)
                    .border(0.dp, Color.White, RoundedCornerShape(8.dp))
                    .padding(10.dp)
            )

            Spacer(modifier = Modifier.height(50.dp));

            Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
                Button(
                    onClick = {

                    },
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .width(300.dp)
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Green)
                ) {
                    Text(
                        text = "Submit",
                        fontSize = 20.sp
                    )
                }
            }

        }
    }
    if (showDatePicker) {
        DatePickerDialog(onDismissRequest = { showDatePicker = !showDatePicker }, confirmButton = {
            Button(
                onClick = {
                    val formatter = SimpleDateFormat("dd/MM/yyyy")
                    viewModel.onProductRequestChanged(
                        productRequest.copy(
                            expiry = formatter.format(
                                Date(datePickerState.selectedDateMillis!!)
                            )
                        )
                    )
                    showDatePicker = !showDatePicker
                },
                colors = ButtonDefaults.buttonColors(containerColor = Green)
            ) {
                Text("Select")
            }
        }, dismissButton = {
            Button(
                onClick = { showDatePicker = !showDatePicker },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    contentColor = Color.Black
                )
            ) {
                Text("Cancel")
            }
        }) {
            DatePicker(
                state = datePickerState,
                colors = DatePickerDefaults.colors(
                    selectedDayContainerColor = Green,
                    todayDateBorderColor = Green
                )
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun SellerProductViewPreview(){
    SellerProductView(Modifier, rememberNavController())
}
