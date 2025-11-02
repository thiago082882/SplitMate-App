package br.thiago.splitmateapp.presentation.screens.bottom_sheet.components.full_history_bottom_sheet

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import br.thiago.splitmateapp.domain.model.Split
@Composable
fun SplitCard(
    split: Split,
    onShareClick: (Split) -> Unit,
    onDeleteClick: (Split) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth().testTag("splitCard_${split.id}"),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(modifier = Modifier.fillMaxWidth().padding(12.dp)) {
            Text(
                text = split.date,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                modifier = Modifier.testTag("splitCardDate_${split.id}")
            )

            Spacer(modifier = Modifier.height(4.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "R$ ${"%.2f".format(split.total).replace(".", ",")}",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.testTag("splitCardTotal_${split.id}")
                )

                Row {
                    IconButton(
                        onClick = { onShareClick(split) },
                        modifier = Modifier.testTag("splitCardShare_${split.id}")
                    ) {
                        Icon(
                            imageVector = Icons.Default.Share,
                            contentDescription = "Compartilhar"
                        )
                    }
                    IconButton(
                        onClick = { onDeleteClick(split) },
                        modifier = Modifier.testTag("splitCardDelete_${split.id}")
                    ) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Excluir"
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(6.dp))

            PersonInfo(split, modifier = Modifier.testTag("splitCardPersonInfo_${split.id}"))
        }
    }
}
