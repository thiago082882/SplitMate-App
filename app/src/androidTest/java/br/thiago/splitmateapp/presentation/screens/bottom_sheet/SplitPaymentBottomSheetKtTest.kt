package br.thiago.splitmateapp.presentation.screens.bottom_sheet

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.filterToOne
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class SplitPaymentBottomSheetKtTest {

    @get:Rule
    val composeRule = createComposeRule()

    private val totalAmount = 120.0

    @Test
    fun bottomSheet_displaysAllElements() {
        composeRule.setContent {
            SplitPaymentBottomSheet(
                totalAmount = totalAmount,
                onConfirm = {},
                onDismiss = {}
            )
        }


        composeRule.onNodeWithTag("splitPaymentBottomSheet").assertIsDisplayed()
        composeRule.onNodeWithTag("bottomSheetTotalAmount").assertIsDisplayed()
        composeRule.onNodeWithTag("bottomSheetCounter").assertIsDisplayed()
        composeRule.onNodeWithTag("bottomSheetPerPersonAmount").assertIsDisplayed()
        composeRule.onNodeWithTag("bottomSheetConfirmButton").assertIsDisplayed()
        composeRule.onNodeWithTag("bottomSheetCancelButton").assertIsDisplayed()
    }

    @Test
    fun bottomSheet_counterIncrementsAndDecrements() {
        composeRule.setContent {
            SplitPaymentBottomSheet(
                totalAmount = totalAmount,
                onConfirm = {},
                onDismiss = {}
            )
        }

        val counterNode = composeRule.onNodeWithTag("bottomSheetCounter")
        val perPersonNode = composeRule.onNodeWithTag("bottomSheetPerPersonAmount")


        perPersonNode.assertTextContains("120,00", substring = true)


        counterNode.onChildren().filterToOne(hasText("+")).performClick()
        perPersonNode.assertTextContains("60,00", substring = true)

        counterNode.onChildren().filterToOne(hasText("-")).performClick()
        perPersonNode.assertTextContains("120,00", substring = true)
    }

    @Test
    fun bottomSheet_confirmAndCancelButtons_callCallbacks() {
        var confirmedPeople = 0
        var dismissed = false

        composeRule.setContent {
            SplitPaymentBottomSheet(
                totalAmount = totalAmount,
                onConfirm = { people -> confirmedPeople = people },
                onDismiss = { dismissed = true }
            )
        }


        composeRule.onNodeWithTag("bottomSheetConfirmButton").performClick()
        assertEquals(1, confirmedPeople)


        composeRule.onNodeWithTag("bottomSheetCancelButton").performClick()
        assertTrue(dismissed)
    }
}
