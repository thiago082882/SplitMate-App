package br.thiago.splitmateapp.presentation.screens.split_mate

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import androidx.test.runner.AndroidJUnit4
import br.thiago.splitmateapp.domain.model.Split
import br.thiago.splitmateapp.presentation.navigation.TestableNavGraph
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class SplitMateScreenTest {


    @get:Rule
    val composeRule = createComposeRule()


    private val mockHistory = listOf(
        Split(1, "01/11/2025", 150.0, 3),
        Split(2, "02/11/2025", 80.0, 2)
    )

    @Before
    fun setup() {

        composeRule.setContent {
            val navController = rememberNavController()
            TestableNavGraph(
                navController = navController,
                paddingValues = PaddingValues(0.dp),
                history = mockHistory,
                onScanClick = {},
                onDeleteClick = {},
                onShareClick = {},
                onViewAllClick = {}
            )
        }
    }


    @Test
    fun splitMateScreen_rootAndTitle_areDisplayed() {
        composeRule.onNodeWithTag("splitMateRoot").assertIsDisplayed()
        composeRule.onNodeWithTag("subtitleText").assertIsDisplayed()
    }

    @Test
    fun mainButton_isDisplayedAndClickable() {
        composeRule.onNodeWithText("Escanear QR do Pix")
            .assertIsDisplayed()
            .assertHasClickAction()
    }

    @Test
    fun historySection_isDisplayed() {
        composeRule.onNodeWithTag("historyTitle").assertIsDisplayed()
        composeRule.onNodeWithTag("btnViewAll").assertIsDisplayed()
    }

    @Test
    fun historyItems_displayCorrectly() {
        mockHistory.forEach { split ->
            composeRule.onNodeWithTag("historyCard_${split.id}").assertIsDisplayed()
            composeRule.onNodeWithTag("historyTotal_${split.id}").assertIsDisplayed()
            composeRule.onNodeWithTag("historyPeople_${split.id}").assertIsDisplayed()
            composeRule.onNodeWithTag("historyShare_${split.id}").assertExists()
            composeRule.onNodeWithTag("historyDelete_${split.id}").assertExists()
        }
    }

}
