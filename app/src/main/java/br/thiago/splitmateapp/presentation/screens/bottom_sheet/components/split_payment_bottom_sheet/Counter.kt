package br.thiago.splitmateapp.presentation.screens.bottom_sheet.components.split_payment_bottom_sheet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.platform.testTag

@Composable
fun Counter(
    count: Int,
    onIncrease: () -> Unit,
    onDecrease: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        IconButton(
            onClick = onDecrease,
            modifier = Modifier
                .size(48.dp)
                .background(Color(0xFFF3F3F3), CircleShape)
                .testTag("counterMinusButton")
        ) {
            Text("-", fontSize = 26.sp, fontWeight = androidx.compose.ui.text.font.FontWeight.Bold)
        }

        Text(
            text = "$count",
            fontSize = 36.sp,
            fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
            modifier = Modifier
                .padding(horizontal = 32.dp)
                .testTag("counterValue"),
            color = Color(0xFF111111)
        )

        IconButton(
            onClick = onIncrease,
            modifier = Modifier
                .size(48.dp)
                .background(Color(0xFFF3F3F3), CircleShape)
                .testTag("counterPlusButton")
        ) {
            Text("+", fontSize = 26.sp, fontWeight = androidx.compose.ui.text.font.FontWeight.Bold)
        }
    }
}
