package com.example.myapplication

private const val EMPTY_STRING: String = ""

data class State(
    val firstNumber: String = EMPTY_STRING,
    val secondNumber: String = EMPTY_STRING,
    val operation: String? = null,
    val result: String = EMPTY_STRING
)

