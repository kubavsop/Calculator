package com.example.myapplication

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.*

@Composable
fun defaultButton(
    onclick: () -> Unit,
    symbol: String,
    modifier: Modifier
) {
    Button(
        onClick = { onclick() },
        shape = ButtonShape,
        colors = ButtonDefaults.buttonColors(backgroundColor = DarkSecondaryContainer),
        modifier = modifier
    ) {
        Text(
            text = symbol,
            fontSize = ButtonTextSize,
            color = DarkOnSecondaryContainer,
            fontFamily = GoogleSans,
            fontWeight = FontWeight.Medium,
        )
    }
}

@Composable
fun accentButton(
    onclick: () -> Unit,
    symbol: String,
    modifier: Modifier,
) {
    Button(
        onClick = { onclick() },
        shape = ButtonShape,
        colors = ButtonDefaults.buttonColors(backgroundColor = DarkTertiaryContainer),
        modifier = modifier
    ) {
        Text(
            text = symbol,
            fontSize = ButtonTextSize,
            color = DarkOnTertiaryContainer,
            fontFamily = GoogleSans,
            fontWeight = FontWeight.Medium,
        )
    }
}

@Composable
fun deleteButton(
    onclick: () -> Unit,
    modifier: Modifier
) {
    IconButton(
        onClick = onclick,
        modifier = modifier
    ) {
        Icon(
            painter = painterResource(R.drawable.delete_button),
            contentDescription = "Delete Button",
            tint = DarkOnSurface,
            modifier = Modifier
                .width(24.dp)
                .height(18.dp)
        )
    }
}