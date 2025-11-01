package br.thiago.splitmateapp.presentation.screens.split_mate.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun SplitMateTitle() {
    Text(
        text = buildAnnotatedString {
            append("Split")
            addStyle(
                style = SpanStyle(color = Color(0xff121313), fontWeight = FontWeight.Bold, fontSize = 30.sp),
                start = 0,
                end = 5
            )
            append("Mate")
            addStyle(
                style = SpanStyle(color = Color(0xff7f7f85), fontWeight = FontWeight.Bold, fontSize = 30.sp),
                start = 5,
                end = 9
            )
        }
    )
}
