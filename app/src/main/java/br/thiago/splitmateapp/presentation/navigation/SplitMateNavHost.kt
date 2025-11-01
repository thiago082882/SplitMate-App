package br.thiago.splitmateapp.presentation.navigation


import android.annotation.SuppressLint
import android.app.Activity
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import br.thiago.splitmateapp.domain.model.Split
import br.thiago.splitmateapp.presentation.screens.bottom_sheet.FullHistoryBottomSheet
import br.thiago.splitmateapp.presentation.screens.bottom_sheet.SplitPaymentBottomSheet
import br.thiago.splitmateapp.presentation.screens.scanner.PixScannerScreen
import br.thiago.splitmateapp.presentation.screens.split_mate.SplitMateScreen
import br.thiago.splitmateapp.presentation.screens.split_mate.viewmodel.SplitViewModel
import br.thiago.splitmateapp.presentation.utils.extractPixAmount
import br.thiago.splitmateapp.presentation.utils.shareReceiptAsImage
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@SuppressLint("ContextCastToActivity")
@Composable
fun SplitMateNavGraph(
    navController: NavHostController,
    paddingValues: PaddingValues
) {
    val viewModel: SplitViewModel = hiltViewModel()
    val activity = LocalContext.current as? Activity
    val history by viewModel.splits.collectAsState()

    var scannedAmount by remember { mutableDoubleStateOf(0.0) }
    var showBottomSheet by remember { mutableStateOf(false) }

    NavHost(
        navController = navController,
        startDestination = Routes.SplitMateScreen,
        modifier = Modifier.padding(paddingValues)
    ) {
        composable<Routes.SplitMateScreen> {
            SplitMateScreen(
                historyList = history,
                onScanClick = { navController.navigate(Routes.PixScannerScreen) },
                onDeleteClick = { viewModel.removeSplit(it) },
                onShareClick = { split -> activity?.let { activity -> split.shareReceiptAsImage(activity) } },
                onViewAllClick = { navController.navigate(Routes.HistoryScreen) }
            )

            if (showBottomSheet) {
                SplitPaymentBottomSheet(
                    totalAmount = scannedAmount,
                    onConfirm = { people ->
                        val currentDate = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())
                        viewModel.addNewSplit(Split(0, currentDate, scannedAmount, people))
                        showBottomSheet = false
                    },
                    onDismiss = { showBottomSheet = false }
                )
            }
        }

        composable<Routes.PixScannerScreen>(
            enterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Start,
                    animationSpec = tween(400)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.End,
                    animationSpec = tween(400)
                )
            }
        ) {
            PixScannerScreen(
                onCancel = { navController.navigateUp() },
                onQrScanned = { qrValue ->
                    scannedAmount = qrValue.extractPixAmount()
                    showBottomSheet = true
                    navController.navigateUp()
                }
            )
        }

        composable<Routes.HistoryScreen>(
            enterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Start,
                    animationSpec = tween(400)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.End,
                    animationSpec = tween(400)
                )
            }
        ) {
            FullHistoryBottomSheet(
                historyList = history,
                onDismiss = { navController.navigateUp() },
                onShareClick = { split -> activity?.let { activity -> split.shareReceiptAsImage(activity) } },
                onDeleteClick = { split -> viewModel.removeSplit(split) }
            )
        }
    }
}
