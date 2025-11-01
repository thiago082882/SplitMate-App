package br.thiago.splitmateapp.presentation.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.thiago.splitmateapp.domain.model.Split

@Composable
fun SplitReceiptCard(split: Split) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(24.dp)
    ) {
        Text(
            text = "Comprovante SplitMate",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF6B4EFF)
        )
        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Data: ${split.date}", fontSize = 16.sp)
        Text(text = "Total: R$ ${"%.2f".format(split.total)}", fontSize = 16.sp)
        Text(text = "Pessoas: ${split.people}", fontSize = 16.sp)
        Text(text = "Valor por pessoa: R$ ${"%.2f".format(split.perPerson)}", fontSize = 16.sp)

        Spacer(modifier = Modifier.height(16.dp))
        Text("💰 Obrigado por usar SplitMate!", fontSize = 14.sp, color = Color.Gray)
    }
}
