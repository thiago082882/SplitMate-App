package br.thiago.splitmateapp.domain.usecase

data class SplitUseCases(
    val getSplits: GetSplitsUseCase,
    val addSplit: AddSplitUseCase,
    val deleteSplit: DeleteSplitUseCase
)
