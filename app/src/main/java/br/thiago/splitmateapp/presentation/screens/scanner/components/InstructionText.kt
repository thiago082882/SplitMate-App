package br.thiago.splitmateapp.presentation.screens.scanner.components


import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag

@Composable
fun InstructionText(text: String, modifier: Modifier = Modifier, testTag: String? = null) {
    Text(
        text = text,
        fontSize = 14.sp,
        color = Color(0xFFB0B0B0),
        textAlign = TextAlign.Center,
        modifier = modifier.then(if (testTag != null) Modifier.testTag(testTag) else Modifier)
    )
}
