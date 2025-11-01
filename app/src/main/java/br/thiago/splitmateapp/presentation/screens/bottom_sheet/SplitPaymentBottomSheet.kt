package br.thiago.splitmateapp.presentation.screens.bottom_sheet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.saveable.rememberSaveable
import br.thiago.splitmateapp.presentation.screens.bottom_sheet.components.split_payment_bottom_sheet.AmountText
import br.thiago.splitmateapp.presentation.screens.bottom_sheet.components.split_payment_bottom_sheet.ConfirmButton
import br.thiago.splitmateapp.presentation.screens.bottom_sheet.components.split_payment_bottom_sheet.Counter
import br.thiago.splitmateapp.presentation.screens.bottom_sheet.components.split_payment_bottom_sheet.InstructionText
import br.thiago.splitmateapp.presentation.screens.bottom_sheet.components.split_payment_bottom_sheet.ScreenTitle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SplitPaymentBottomSheet(
    totalAmount: Double,
    onConfirm: (people: Int) -> Unit,
    onDismiss: () -> Unit
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    LaunchedEffect(sheetState) { sheetState.expand() }

    var people by rememberSaveable { mutableStateOf(1) }

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
        containerColor = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(horizontal = 24.dp, vertical = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ScreenTitle("Em quantas pessoas dividir?")
            Spacer(modifier = Modifier.height(20.dp))

            InstructionText("Valor Total:")
            AmountText(totalAmount)

            Spacer(modifier = Modifier.height(24.dp))

            Counter(
                count = people,
                onIncrease = { people++ },
                onDecrease = { if (people > 1) people-- }
            )

            Spacer(modifier = Modifier.height(24.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFE8F9EE), RoundedCornerShape(16.dp))
                    .padding(vertical = 14.dp)
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
                    InstructionText("Valor por Pessoa:", color = Color.Gray)
                    AmountText(totalAmount / people, color = Color(0xFF16A34A), fontSize = 26)
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            ConfirmButton(
                text = "Confirmar e Salvar Divisão",
                onClick = { onConfirm(people) }
            )

            Spacer(modifier = Modifier.height(12.dp))

            TextButton(onClick = onDismiss) {
                InstructionText("Cancelar")
            }

            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}
