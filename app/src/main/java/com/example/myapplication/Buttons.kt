package com.example.myapplication

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.*

@Composable
fun FirstStyleButton(
    onclick: () -> Unit,
    symbol: String,
    modifier: Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .clip(ButtonShape)
            .clickable { onclick() }
            .then(modifier)
    ) {
        Text(
            text = symbol,
            fontSize = ButtonTextSize,
            color = FirstSymbolColor,
            fontFamily = GoogleSans,
            fontWeight = FontWeight.Medium,
        )
    }
}

@Composable
fun SecondStyleButton(
    onclick: () -> Unit,
    symbol: String,
    modifier: Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .clip(ButtonShape)
            .clickable { onclick() }
            .then(modifier)
    ) {
        Text(
            text = symbol,
            fontSize = ButtonTextSize,
            color = SecondSymbolColor,
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
            tint = DeleteButtonColor,
            modifier = Modifier
                .width(24.dp)
                .height(18.dp)
        )
    }
}