package br.thiago.splitmateapp.domain.usecase

import br.thiago.splitmateapp.domain.repository.SplitRepository

open class GetSplitsUseCase(private val repository: SplitRepository) {
    open suspend operator fun invoke() = repository.getAll()
}
