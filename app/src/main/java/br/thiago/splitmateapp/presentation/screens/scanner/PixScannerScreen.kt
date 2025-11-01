package br.thiago.splitmateapp.presentation.screens.scanner

import android.Manifest
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import br.thiago.splitmateapp.presentation.screens.scanner.components.DefaultButton
import br.thiago.splitmateapp.presentation.screens.scanner.components.InstructionText
import br.thiago.splitmateapp.presentation.screens.scanner.components.ScreenTitle
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.zxing.ResultPoint
import com.journeyapps.barcodescanner.BarcodeCallback
import com.journeyapps.barcodescanner.BarcodeResult
import com.journeyapps.barcodescanner.DecoratedBarcodeView

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun PixScannerScreen(
    onCancel: () -> Unit,
    onQrScanned: (String) -> Unit
) {
    var scanned by remember { mutableStateOf(false) }
    val cameraPermissionState = rememberPermissionState(Manifest.permission.CAMERA)

    LaunchedEffect(Unit) { cameraPermissionState.launchPermissionRequest() }

    Dialog(
        onDismissRequest = onCancel,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF0E1420))
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                ScreenTitle("Escanear QR do Pix")
                Spacer(modifier = Modifier.height(16.dp))

                // Scanner central
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color(0xFF1B2433)),
                    contentAlignment = Alignment.Center
                ) {
                    if (cameraPermissionState.status.isGranted) {
                        AndroidView(
                            factory = { ctx ->
                                DecoratedBarcodeView(ctx).apply {
                                    layoutParams = ViewGroup.LayoutParams(
                                        ViewGroup.LayoutParams.MATCH_PARENT,
                                        ViewGroup.LayoutParams.MATCH_PARENT
                                    )
                                    decodeContinuous(object : BarcodeCallback {
                                        override fun barcodeResult(result: BarcodeResult?) {
                                            if (result != null && !scanned) {
                                                scanned = true
                                                onQrScanned(result.text)
                                            }
                                        }

                                        override fun possibleResultPoints(resultPoints: MutableList<ResultPoint>?) {}
                                    })
                                    resume()
                                }
                            },
                            modifier = Modifier.fillMaxSize()
                        )
                    } else {
                        InstructionText(
                            text = "Permita a câmera para escanear",
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                InstructionText(
                    text = "Aponte para o QR Code do comprovante Pix.",
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                DefaultButton(
                    text = "Cancelar",
                    onClick = onCancel
                )
            }
        }
    }
}
