package com.example.agromart.view.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import com.example.agromart.ui.theme.Green
import com.example.agromart.view.component.AgroMartTextField
import com.example.agromart.viewmodel.ProfileViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    modifier: Modifier,
    navHostController: NavHostController,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val userRequest by viewModel.userDetailRequest.collectAsState()
    val userResponse by viewModel.userDetailResponse.collectAsState()
    var isEdited by remember {
        mutableStateOf(false)
    }
    LaunchedEffect(key1 = userResponse, block = {
        viewModel.getData()
        viewModel.onUserRequestChanged(
            userRequest.copy(
                phone = userResponse.data.phone,
                name = userResponse.data.name,
                gender = userResponse.data.gender,
                aadhar = userResponse.data.aadhar,
                dob = userResponse.data.dob,
                userType = userResponse.data.userType
            )
        )
    })
    Scaffold(topBar = {
        TopAppBar(
            title = {
                Text(
                    text = "Profile",
                    color = Color.Black,
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
                )
            },
            navigationIcon = {
                IconButton(onClick = { navHostController.popBackStack() }) {
                    Icon(
                        imageVector = Icons.Rounded.ArrowBack,
                        contentDescription = null
                    )
                }
            },
            actions = {
                IconButton(onClick = { isEdited = !isEdited }) {
                    Icon(
                        imageVector = Icons.Rounded.Edit,
                        contentDescription = null,
                        modifier.padding(end = 10.dp)
                    )
                }
            })
    }) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(it),
        ) {
            AgroMartTextField(
                if (isEdited) userRequest.name else userResponse.data.name,
                modifier, placeHolderText = "Name", enabled = isEdited
            ) { viewModel.onUserRequestChanged(userRequest.copy(name = it)) }
            AgroMartTextField(
                userResponse.data.phone,
                modifier, placeHolderText = "Phone", enabled = false
            ) { viewModel.onUserRequestChanged(userRequest.copy(phone = it)) }
            AgroMartTextField(
                if (isEdited) userRequest.aadhar else userResponse.data.aadhar,
                modifier, placeHolderText = "Aadhar Number", enabled = isEdited
            ) { viewModel.onUserRequestChanged(userRequest.copy(aadhar = it)) }
            AgroMartTextField(
                if (isEdited) userRequest.dob else userResponse.data.dob,
                modifier, placeHolderText = "DOB", enabled = isEdited
            ) { viewModel.onUserRequestChanged(userRequest.copy(dob = it)) }
            AgroMartTextField(
                if (isEdited) userRequest.userType else userResponse.data.userType,
                modifier, placeHolderText = "UserType", enabled = isEdited
            ) { viewModel.onUserRequestChanged(userRequest.copy(userType = it)) }
            AgroMartTextField(
                if (isEdited) userRequest.address else userResponse.data.address,
                modifier, placeHolderText = "Address", enabled = isEdited
            ) { viewModel.onUserRequestChanged(userRequest.copy(address = it)) }
            Button(
                onClick = { viewModel.editData() }, modifier = modifier
                    .fillMaxWidth()
                    .padding(20.dp), colors = ButtonDefaults.buttonColors(containerColor = Green)
            ) {
                Text(text = "Update Data")
            }
        }
    }
}