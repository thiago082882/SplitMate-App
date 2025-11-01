package br.thiago.splitmateapp.presentation.screens.split_mate

import br.thiago.splitmateapp.presentation.screens.split_mate.components.MainButton
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import br.thiago.splitmateapp.domain.model.Split
import br.thiago.splitmateapp.presentation.screens.split_mate.components.AppText
import br.thiago.splitmateapp.presentation.screens.split_mate.components.HistoryCard
import br.thiago.splitmateapp.presentation.screens.split_mate.components.SplitMateTitle


@Composable
fun SplitMateScreen(
    historyList: List<Split>,
    onScanClick: () -> Unit,
    onDeleteClick: (Split) -> Unit,
    onShareClick: (Split) -> Unit,
    onViewAllClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SplitMateTitle()

        AppText(
            text = "Sua divisão de contas automática.",
            fontSize = 14.sp,
            color = Color(0xFF7D7D7D),
            paddingBottom = 24.dp
        )

        MainButton(text = "Escanear QR do Pix", onClick = onScanClick)

        Spacer(modifier = Modifier.height(28.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            AppText(
                text = "Histórico Recente",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )
            TextButton(onClick = onViewAllClick) {
                Text(
                    text = "Ver Tudo",
                    fontSize = 14.sp,
                    color = Color(0xFF6B4EFF),
                    fontWeight = FontWeight.Medium
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(historyList) { item ->
                HistoryCard(
                    splitItem = item,
                    onShare = { onShareClick(item) },
                    onDelete = { onDeleteClick(item) }
                )
            }
        }
    }
}
