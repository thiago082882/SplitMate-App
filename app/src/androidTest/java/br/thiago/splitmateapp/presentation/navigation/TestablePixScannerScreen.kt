package br.thiago.splitmateapp.presentation.screens.scanner

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import br.thiago.splitmateapp.presentation.screens.scanner.components.DefaultButton
import br.thiago.splitmateapp.presentation.screens.scanner.components.InstructionText
import br.thiago.splitmateapp.presentation.screens.scanner.components.ScreenTitle

@Composable
fun TestablePixScannerScreenContent(
    onCancel: () -> Unit,
    onQrScanned: (String) -> Unit,
    cameraGranted: Boolean = false
) {
    var scanned by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0E1420))
            .testTag("pixScannerRoot")
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            ScreenTitle(
                text = "Escanear QR do Pix",
                modifier = Modifier.testTag("pixScannerTitle")
            )

            Spacer(modifier = Modifier.height(16.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(0xFF1B2433))
                    .testTag("pixScannerCameraBox"),
                contentAlignment = Alignment.Center
            ) {
                if (cameraGranted) {

                    LaunchedEffect(Unit) {
                        if (!scanned) {
                            scanned = true
                            onQrScanned("TEST_QR_12345")
                        }
                    }
                } else {
                    InstructionText(
                        text = "Permita a câmera para escanear",
                        modifier = Modifier
                            .padding(16.dp)
                            .testTag("pixScannerPermissionText")
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            InstructionText(
                text = "Aponte para o QR Code do comprovante Pix.",
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .testTag("pixScannerInstructionText")
            )

            DefaultButton(
                text = "Cancelar",
                onClick = onCancel,
                modifier = Modifier,
                testTag = "pixScannerCancelButton"
            )
        }
    }
}
