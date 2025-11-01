package br.thiago.splitmateapp.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Routes {
    @Serializable
    data object SplitMateScreen : Routes()
    @Serializable
    data object PixScannerScreen : Routes()
    @Serializable
    data object HistoryScreen : Routes()
}
