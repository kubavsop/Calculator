package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.myapplication.ui.theme.DefaultPadding


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel by viewModels<CalculatorViewModel>()
            val state = viewModel.state
            Calculator(
                state = state,
                onDelete = viewModel::onDelete,
                onAction = viewModel::onAction,
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black)
                    .padding(DefaultPadding)
            )
        }
    }
}