package br.thiago.splitmateapp.presentation.screens.bottom_sheet.components.split_payment_bottom_sheet

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp

@Composable
fun InstructionText(text: String, color: Color = Color.Gray,modifier: Modifier = Modifier) {
    Text(text = text, color = color, fontSize = 14.sp)
}