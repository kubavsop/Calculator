package com.example.myapplication

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.TabRowDefaults.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.*

@Composable
fun Calculator(
    modifier: Modifier = Modifier,
    state: State,
    onAction: (Action) -> Unit
) {
    Box(modifier = modifier) {

        Text(
            text = "Calculator",
            fontSize = TitleSize,
            color = TitleTextСolor,
            fontFamily = GoogleSans,
            fontWeight = FontWeight.Medium
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            verticalArrangement = Arrangement.spacedBy(DefaultPadding)
        ) {

            Text(
                text = state.firstNumber + (state.operation?.symbol ?: "") + state.secondNumber,
                textAlign = TextAlign.Left,
                modifier = Modifier
                    .fillMaxWidth(),
                fontSize = MainTextSze,
                color = MainTextColor,
                maxLines = 1,
                fontFamily = GoogleSans,
                fontWeight = FontWeight.Medium,
            )

            deleteButton(
                onclick = { onAction(Action.Delete) },
                modifier = Modifier
                    .align(Alignment.End),
            )

            Divider(
                thickness = 1.dp,
                color = LineColor
            )

            SetButtons(onAction = onAction)

        }
    }
}

@Composable
fun SetButtons(onAction: (Action) -> Unit) {
    val rows = listOf(
        listOf(
            Action.Clear,
            Action.ChangeSign,
            Action.Operation(Operation.Remainder),
            Action.Operation(Operation.Divide)
        ),
        listOf(
            Action.Number(7),
            Action.Number(8),
            Action.Number(9),
            Action.Operation(Operation.Multiply)
        ),
        listOf(
            Action.Number(4),
            Action.Number(5),
            Action.Number(6),
            Action.Operation(Operation.Subtract)
        ),
        listOf(
            Action.Number(1),
            Action.Number(2),
            Action.Number(3),
            Action.Operation(Operation.Add)
        ),
        listOf(Action.Number(0), Action.Decimal, Action.Calculate)
    )

    for (row in rows) {
        SetButtonRow(buttons = row, onAction = onAction)
    }
}


@Composable
fun SetButtonRow(buttons: List<Action>, onAction: (Action) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(DefaultPadding)
    ) {
        for (button in buttons) {
            if (getSymbol(button) == "0") {
                FirstStyleButton(symbol = "0",
                    modifier = Modifier
                        .background(FirstButtonColor)
                        .weight(ZeroButtonWeight)
                        .aspectRatio(ZeroButtonWeight),
                    onclick = {
                        onAction(Action.Number(0))
                    }
                )
            } else {
                if (button != buttons[buttons.size - 1]) {
                    FirstStyleButton(symbol = getSymbol(button),
                        modifier = Modifier
                            .background(FirstButtonColor)
                            .weight(ButtonWeight)
                            .aspectRatio(ButtonWeight),
                        onclick = {
                            onAction(button)
                        }
                    )
                } else {
                    SecondStyleButton(symbol = getSymbol(button),
                        modifier = Modifier
                            .background(SecondButtonColor)
                            .weight(ButtonWeight)
                            .aspectRatio(ButtonWeight),
                        onclick = {
                            onAction(button)
                        }
                    )
                }
            }
        }
    }
}


fun getSymbol(action: Action): String {
    return when (action) {
        is Action.Clear -> "AC"
        is Action.Operation -> action.operation.symbol
        is Action.ChangeSign -> "±"
        is Action.Decimal -> ","
        is Action.Calculate -> "="
        is Action.Number -> action.number.toString()
        is Action.Delete -> ""
    }
}
