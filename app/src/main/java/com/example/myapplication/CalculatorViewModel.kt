package com.example.myapplication

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class CalculatorViewModel : ViewModel() {
    var state by mutableStateOf(State())

    fun onAction(action: Action) {
        if ((state.firstNumber + state.secondNumber).length >= 15) {
            state = State()
            state = state.copy(firstNumber = "Error")
            return
        }

        when (action) {
            is Action.Number -> enterNumber(action.number)
            is Action.Decimal -> enterDecimal()
            is Action.Clear -> state = State()
            is Action.Operation -> enterOperation(action.operation)
            is Action.Calculate -> performCalculation()
            is Action.Delete -> performDeletion()
            is Action.ChangeSign -> performSignСhange()
        }
    }

    private fun performSignСhange() {
        if (state.firstNumber != "-" && state.firstNumber != "") {

            performCalculation()
            if (state.firstNumber == "Error") return

            val result = -state.firstNumber.replace(",", ".").toDouble()
            state = state.copy(
                firstNumber = result.toString().replace(".", ","),
            )

            if (result == result.toInt().toDouble()) {
                state = state.copy(
                    firstNumber = result.toInt().toString()
                )
            }

        } else if (state.firstNumber == "") {
            state = state.copy(firstNumber = "-")
        }
    }

    private fun performCalculation() {
        if (
            state.firstNumber == "Error" || (state.operation == Operation.Divide || state.operation == Operation.Remainder)
            && state.secondNumber.replace(",", ".").toDoubleOrNull() == 0.0
        ) {
            state = State()
            state = state.copy(firstNumber = "Error")
            return
        }

        val firstNumber = state.firstNumber.replace(",", ".").toDoubleOrNull()
        val secondNumber = state.secondNumber.replace(",", ".").toDoubleOrNull()

        if (firstNumber != null && secondNumber != null) {
            val result = when (state.operation) {
                is Operation.Add -> firstNumber + secondNumber
                is Operation.Subtract -> firstNumber - secondNumber
                is Operation.Multiply -> firstNumber * secondNumber
                is Operation.Divide -> firstNumber / secondNumber
                is Operation.Remainder -> firstNumber % secondNumber
                null -> return
            }

            state = state.copy(
                firstNumber = result.toString().replace(".", ","),
                secondNumber = "",
                operation = null
            )

            if (result == result.toInt().toDouble()) {
                state = state.copy(firstNumber = result.toInt().toString())
            }
        }
    }

    private fun performDeletion() {
        if (state.firstNumber == "Error") return
        when {
            state.secondNumber != "" -> state = state.copy(
                secondNumber = state.secondNumber.dropLast(1)
            )
            state.operation != null -> state = state.copy(
                operation = null
            )
            state.firstNumber != "" -> state = state.copy(
                firstNumber = state.firstNumber.dropLast(1)
            )
        }
    }

    private fun enterOperation(operation: Operation) {
        if (state.firstNumber == "Error") return
        if (state.secondNumber != "") {
            performCalculation()
            enterOperation(operation)
        } else if (state.firstNumber != "-" && state.firstNumber != "") {
            state = state.copy(operation = operation)
        }
    }

    private fun enterDecimal() {
        if (state.firstNumber == "Error") return
        if (state.operation == null && !state.firstNumber.contains(",")
            && state.firstNumber != "-" && state.firstNumber != ""
        ) {
            state = state.copy(
                firstNumber = state.firstNumber + ","
            )
            return
        }
        if (!state.secondNumber.contains(",") && state.secondNumber != "") {
            state = state.copy(secondNumber = state.secondNumber + ",")
        }
    }

    private fun enterNumber(number: Int) {
        if (state.operation == null) {
            state = if (state.firstNumber == "Error" || state.firstNumber.toDoubleOrNull() == 0.0) {
                state.copy(firstNumber = number.toString())
            } else {
                state.copy(firstNumber = state.firstNumber + number.toString())
            }
            return
        }

        state = if (state.firstNumber.toDoubleOrNull() == 0.0) {
            state.copy(secondNumber = number.toString())
        } else {
            state.copy(secondNumber = state.secondNumber + number.toString())
        }
    }
}