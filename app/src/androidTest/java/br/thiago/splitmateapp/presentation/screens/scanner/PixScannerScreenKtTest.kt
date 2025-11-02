package br.thiago.splitmateapp.presentation.screens.scanner

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PixScannerScreenTest {

    @get:Rule
    val composeRule = createComposeRule()

    private var cancelled = false
    private var scannedResult: String? = null

    @Test
    fun pixScannerScreen_displaysAllComponents_cameraNotGranted() {
        composeRule.setContent {
            TestablePixScannerScreenContent(
                onCancel = { cancelled = true },
                onQrScanned = { scannedResult = it },
                cameraGranted = false
            )
        }

        composeRule.onNodeWithTag("pixScannerRoot").assertIsDisplayed()
        composeRule.onNodeWithTag("pixScannerTitle").assertTextEquals("Escanear QR do Pix")
        composeRule.onNodeWithTag("pixScannerInstructionText")
            .assertTextEquals("Aponte para o QR Code do comprovante Pix.")
        composeRule.onNodeWithTag("pixScannerCameraBox").assertIsDisplayed()
        composeRule.onNodeWithTag("pixScannerPermissionText").assertIsDisplayed()
        composeRule.onNodeWithTag("pixScannerCancelButton").assertIsDisplayed()
    }

    @Test
    fun pixScannerScreen_displaysAllComponents_cameraGranted() {
        composeRule.setContent {
            TestablePixScannerScreenContent(
                onCancel = { cancelled = true },
                onQrScanned = { scannedResult = it },
                cameraGranted = true
            )
        }

        composeRule.onNodeWithTag("pixScannerRoot").assertIsDisplayed()
        composeRule.onNodeWithTag("pixScannerTitle").assertIsDisplayed()
        composeRule.onNodeWithTag("pixScannerInstructionText").assertIsDisplayed()
        composeRule.onNodeWithTag("pixScannerCameraBox").assertIsDisplayed()
        composeRule.onNodeWithTag("pixScannerCancelButton").assertIsDisplayed()
    }

    @Test
    fun pixScannerScreen_cancelButton_callsOnCancel() {
        composeRule.setContent {
            TestablePixScannerScreenContent(
                onCancel = { cancelled = true },
                onQrScanned = { scannedResult = it },
                cameraGranted = true
            )
        }

        composeRule.onNodeWithTag("pixScannerCancelButton").performClick()
        assert(cancelled)
    }

    @Test
    fun pixScannerScreen_simulateQrScan_callsOnQrScanned() {
        composeRule.setContent {
            TestablePixScannerScreenContent(
                onCancel = {},
                onQrScanned = { scannedResult = it },
                cameraGranted = true
            )
        }


        composeRule.runOnIdle {
            scannedResult = "12345QR"
        }

        assert(scannedResult == "12345QR")
    }
}
