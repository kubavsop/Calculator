package com.example.myapplication

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class CalculatorViewModel : ViewModel() {
    var state by mutableStateOf(State())

    private companion object {
        const val MAX_LENGTH = 15
        const val ERROR = "Error"
        const val COMMA = ","
        const val DOT = "."
        const val MINUS = "-"
        const val EMPTY_STRING = ""
    }

    private var isError = false
    fun onAction(action: Action) {
        if ((state.firstNumber + state.secondNumber).length >= MAX_LENGTH) {
            isError = true
            state = State(result = ERROR)
            return
        }

        when (action) {
            Action.DECIMAL -> enterDecimal()
            Action.CLEAR -> state = State()
            Action.ADD, Action.SUBTRACT, Action.MULTIPLY, Action.DIVIDE, Action.REMAINDER -> enterOperation(
                action.symbol
            )
            Action.CALCULATE -> performCalculation()
            Action.CHANGE_SIGN -> performSignСhange()
            else -> enterNumber(action.symbol)
        }
    }

    fun onDelete() {
        if (isError) return
        when {
            state.secondNumber.isNotEmpty() -> state = state.copy(
                secondNumber = state.secondNumber.dropLast(1)
            )
            state.operation != null -> state = state.copy(
                operation = null
            )
            state.firstNumber.isNotEmpty() -> state = state.copy(
                firstNumber = state.firstNumber.dropLast(1)
            )
        }
        getResult()
    }

    private fun getResult() {
        isError = false
        state = state.copy(result = state.firstNumber + (state.operation ?: EMPTY_STRING) + state.secondNumber)
    }

    private fun performSignСhange() {
        if (state.firstNumber != MINUS && state.firstNumber.isNotEmpty()) {

            performCalculation()
            if (isError) return

            val result = -state.firstNumber.replace(COMMA, DOT).toDouble()
            state = state.copy(
                firstNumber = result.toString().replace(DOT, COMMA),
            )

            if (result == result.toInt().toDouble()) {
                state = state.copy(
                    firstNumber = result.toInt().toString()
                )
            }

        } else if (state.firstNumber.isEmpty()) {
            state = state.copy(firstNumber = MINUS)
        }
        getResult()
    }

    private fun performCalculation() {
        if (
            isError || (state.operation == Action.DIVIDE.symbol || state.operation == Action.REMAINDER.symbol)
            && state.secondNumber.replace(COMMA, DOT).toDoubleOrNull() == 0.0
        ) {
            isError = true
            state = State(result = ERROR)
            return
        }

        val firstNumber = state.firstNumber.replace(COMMA, DOT).toDoubleOrNull()
        val secondNumber = state.secondNumber.replace(COMMA, DOT).toDoubleOrNull()

        if (firstNumber != null && secondNumber != null) {
            val result = when (state.operation) {
                Action.ADD.symbol -> firstNumber + secondNumber
                Action.SUBTRACT.symbol -> firstNumber - secondNumber
                Action.MULTIPLY.symbol -> firstNumber * secondNumber
                Action.DIVIDE.symbol -> firstNumber / secondNumber
                Action.REMAINDER.symbol -> firstNumber % secondNumber
                else -> return
            }

            state = state.copy(
                firstNumber = result.toString().replace(DOT, COMMA),
                secondNumber = EMPTY_STRING,
                operation = null
            )

            if (result == result.toInt().toDouble()) {
                state = state.copy(firstNumber = result.toInt().toString())
            }
        }
        getResult()
    }

    private fun enterOperation(operation: String) {
        if (isError) return
        if (state.secondNumber.isNotEmpty()) {
            performCalculation()
            enterOperation(operation)
        } else if (state.firstNumber != MINUS && state.firstNumber.isNotEmpty()) {
            state = state.copy(operation = operation)
        }
        getResult()
    }

    private fun enterDecimal() {
        if (isError) return
        if (state.operation == null && !state.firstNumber.contains(COMMA)
            && state.firstNumber != MINUS && state.firstNumber.isNotEmpty()
        ) {
            state = state.copy(firstNumber = state.firstNumber + COMMA)
        } else if (!state.secondNumber.contains(COMMA) && state.secondNumber != EMPTY_STRING) {
            state = state.copy(secondNumber = state.secondNumber + COMMA)
        }
        getResult()
    }

    private fun enterNumber(number: String) {
        if (state.operation == null) {
            state = if (isError || state.firstNumber.toDoubleOrNull() == 0.0) {
                state.copy(firstNumber = number)
            } else {
                state.copy(firstNumber = state.firstNumber + number)
            }
        } else {
            state = if (state.secondNumber.toDoubleOrNull() == 0.0) {
                state.copy(secondNumber = number)
            } else {
                state.copy(secondNumber = state.secondNumber + number)
            }
        }
        getResult()
    }
}