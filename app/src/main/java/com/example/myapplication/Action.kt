package com.example.myapplication

sealed class Action {
    data class Number(val number: Int) : Action()
    object Clear : Action()
    object Delete : Action()
    object Decimal : Action()
    object Calculate : Action()
    object ChangeSign : Action()
    data class Operation(val operation: com.example.myapplication.Operation) : Action()
}

