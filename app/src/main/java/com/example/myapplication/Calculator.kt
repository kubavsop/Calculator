package com.example.myapplication

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
    state: State,
    onDelete: () -> Unit,
    onAction: (Action) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {

        Text(
            text = "Calculator",
            fontSize = TitleSize,
            color = DarkOnBackground,
            fontFamily = GoogleSans,
            fontWeight = FontWeight.Medium
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(DefaultPadding),
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
        ) {

            Text(
                text = state.result,
                textAlign = TextAlign.Left,
                fontSize = MainTextSze,
                color = DarkPrimary,
                maxLines = 1,
                fontFamily = GoogleSans,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .fillMaxWidth()
            )

            deleteButton(
                onclick = { onDelete() },
                modifier = Modifier
                    .align(Alignment.End)
            )

            Divider(
                thickness = 1.dp,
                color = DarkOutlineVariant
            )

            SetButtons(onAction = onAction)

        }
    }
}

@Composable
fun SetButtons(onAction: (Action) -> Unit) {
    val rows = listOf(
        listOf(Action.CLEAR, Action.CHANGE_SIGN, Action.REMAINDER, Action.DIVIDE),
        listOf(Action.SEVEN, Action.EIGHT, Action.NINE, Action.MULTIPLY),
        listOf(Action.FOUR, Action.FIVE, Action.SIX, Action.SUBTRACT),
        listOf(Action.ONE, Action.TWO, Action.THREE, Action.ADD),
        listOf(Action.ZERO, Action.DECIMAL, Action.CALCULATE)
    )

    for (row in rows) {
        SetButtonRow(buttons = row, onAction = onAction)
    }
}

@Composable
fun SetButtonRow(buttons: List<Action>, onAction: (Action) -> Unit) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(DefaultPadding),
        modifier = Modifier
            .fillMaxWidth()
    ) {
        for (i in 0..buttons.size - 2) {
            if (buttons[i] == Action.ZERO) {
                defaultButton(
                    symbol = "0",
                    onclick = {
                        onAction(buttons[i])
                    },
                    modifier = Modifier
                        .weight(ZeroButtonWeight)
                        .aspectRatio(ZeroButtonWeight)
                )
            } else {
                defaultButton(
                    symbol = buttons[i].symbol,
                    onclick = {
                        onAction(buttons[i])
                    },
                    modifier = Modifier
                        .weight(ButtonWeight)
                        .aspectRatio(ButtonWeight)
                )
            }
        }
        accentButton(
            symbol = buttons[buttons.size - 1].symbol,
            onclick = {
                onAction(buttons[buttons.size - 1])
            },
            modifier = Modifier
                .weight(ButtonWeight)
                .aspectRatio(ButtonWeight)
        )
    }
}

