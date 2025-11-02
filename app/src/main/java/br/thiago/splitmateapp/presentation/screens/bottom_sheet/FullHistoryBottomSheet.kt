package br.thiago.splitmateapp.presentation.screens.bottom_sheet

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import br.thiago.splitmateapp.domain.model.Split
import br.thiago.splitmateapp.presentation.screens.bottom_sheet.components.full_history_bottom_sheet.HeaderText
import br.thiago.splitmateapp.presentation.screens.bottom_sheet.components.full_history_bottom_sheet.SplitCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FullHistoryBottomSheet(
    historyList: List<Split>,
    onDismiss: () -> Unit,
    onShareClick: (Split) -> Unit,
    onDeleteClick: (Split) -> Unit
) {
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
        shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 20.dp)
        ) {
            HeaderText("Histórico Completo")

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                items(historyList) { split ->
                    SplitCard(
                        split = split,
                        onShareClick = onShareClick,
                        onDeleteClick = onDeleteClick
                    )
                }
            }
        }
    }
}
