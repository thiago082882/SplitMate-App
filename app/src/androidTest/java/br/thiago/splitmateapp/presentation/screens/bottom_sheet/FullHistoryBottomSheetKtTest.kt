package br.thiago.splitmateapp.presentation.screens.bottom_sheet

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import br.thiago.splitmateapp.domain.model.Split
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FullHistoryBottomSheetTest {

    @get:Rule
    val composeRule = createComposeRule()

    private val sampleSplits = listOf(
        Split(id = 1, date = "01/11/2025", total = 120.0, people = 2),
        Split(id = 2, date = "02/11/2025", total = 200.0, people = 4)
    )

    private var sharedSplit: Split? = null
    private var deletedSplit: Split? = null
    private var dismissed = false

    @Before
    fun setup() {
        sharedSplit = null
        deletedSplit = null
        dismissed = false

        composeRule.setContent {
            FullHistoryBottomSheet(
                historyList = sampleSplits,
                onDismiss = { dismissed = true },
                onShareClick = { sharedSplit = it },
                onDeleteClick = { deletedSplit = it }
            )
        }
    }

    @Test
    fun bottomSheet_displaysHeaderAndAllSplits() {

        composeRule.onNodeWithText("Histórico Completo").assertIsDisplayed()


        sampleSplits.forEach { split ->
            composeRule.onNodeWithTag("splitCard_${split.id}").assertIsDisplayed()
            composeRule.onNodeWithTag("splitCardDate_${split.id}")
                .assertTextContains(split.date)
            composeRule.onNodeWithTag("splitCardTotal_${split.id}")
                .assertTextContains("R$ %.2f".format(split.total).replace(".", ","))
            composeRule.onNodeWithTag("splitCardShare_${split.id}").assertIsDisplayed()
            composeRule.onNodeWithTag("splitCardDelete_${split.id}").assertIsDisplayed()
            composeRule.onNodeWithTag("splitCardPersonInfoText_${split.id}", useUnmergedTree = true)
                .assertTextContains("${split.people} pessoas", substring = true)
        }
    }

    @Test
    fun bottomSheet_shareAndDeleteButtons_callCallbacks() {
        composeRule.onNodeWithTag("splitCardShare_1").performClick()
        assert(sharedSplit?.id == 1)

        composeRule.onNodeWithTag("splitCardDelete_2").performClick()
        assert(deletedSplit?.id == 2)
    }

    @Test
    fun bottomSheet_dismiss_callsCallback() {

        composeRule.runOnUiThread {
            dismissed = true
        }

        composeRule.waitForIdle()
        assert(dismissed)
    }
}
