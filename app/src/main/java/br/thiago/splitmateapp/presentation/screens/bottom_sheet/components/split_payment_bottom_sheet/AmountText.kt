package br.thiago.splitmateapp.presentation.screens.bottom_sheet.components.split_payment_bottom_sheet

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp

@Composable
fun AmountText(amount: Double, color: Color = Color(0xFF5D5FEF), fontSize: Int = 36) {
    Text(
        text = "R$ %.2f".format(amount).replace(".", ","),
        color = color,
        fontSize = fontSize.sp,
        fontWeight = androidx.compose.ui.text.font.FontWeight.ExtraBold
    )
}