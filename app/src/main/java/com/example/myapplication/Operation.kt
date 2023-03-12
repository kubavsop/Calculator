package com.example.myapplication

sealed class Operation(val symbol: String) {
    object Add : Operation("+")
    object Subtract : Operation("-")
    object Multiply : Operation("×")
    object Divide : Operation("÷")
    object Remainder : Operation("%")
}
