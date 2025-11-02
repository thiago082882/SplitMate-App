package br.thiago.splitmateapp.presentation.screens.bottom_sheet.components.full_history_bottom_sheet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import br.thiago.splitmateapp.domain.model.Split

@Composable
fun PersonInfo(split: Split, modifier: Modifier = Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        modifier = modifier
    ) {
        Icon(
            imageVector = Icons.Default.Person,
            contentDescription = null
        )
        Text(
            text = "${split.people} pessoas • R$ ${"%.2f".format(split.perPerson).replace('.', ',')} por pessoa",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.testTag("splitCardPersonInfoText_${split.id}")
        )
    }
}
