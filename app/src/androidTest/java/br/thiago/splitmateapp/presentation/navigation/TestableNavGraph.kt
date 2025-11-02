package br.thiago.splitmateapp.presentation.navigation

import androidx.compose.foundation.layout.padding



import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import br.thiago.splitmateapp.domain.model.Split
import br.thiago.splitmateapp.presentation.screens.split_mate.SplitMateScreen

@Composable
fun TestableNavGraph(
    navController: NavHostController,
    paddingValues: PaddingValues,
    history: List<Split>,
    onScanClick: () -> Unit = {},
    onDeleteClick: (Split) -> Unit = {},
    onShareClick: (Split) -> Unit = {},
    onViewAllClick: () -> Unit = {}
) {
    NavHost(
        navController = navController,
        startDestination = "SplitMateScreen",
        modifier = Modifier.padding(paddingValues)
    ) {
        composable("SplitMateScreen") {

            SplitMateScreen(
                historyList = history,
                onScanClick = onScanClick,
                onDeleteClick = onDeleteClick,
                onShareClick = onShareClick,
                onViewAllClick = onViewAllClick
            )
        }
    }
}
