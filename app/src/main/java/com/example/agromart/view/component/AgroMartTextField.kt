package com.example.agromart.view.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.agromart.ui.theme.Green

@Composable
fun AgroMartTextField(
    value: String,
    modifier: Modifier=Modifier,
    placeHolderText: String,
    isNumber: Boolean=false,
    enabled:Boolean=true,
    onValueChange: (String) -> Unit,

) {
    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp),
        value = value,
        label = { Text(placeHolderText) },
        enabled=enabled,
        onValueChange = onValueChange,
        placeholder = { Text(placeHolderText) },
        keyboardOptions = if (isNumber) (KeyboardOptions(keyboardType = KeyboardType.Number)) else (KeyboardOptions()),
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color.Transparent,
            focusedContainerColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Black,
            focusedIndicatorColor = Green,
            cursorColor = Green,
            focusedLabelColor = Green,
            unfocusedLabelColor = Color.Black
        )
    )
}