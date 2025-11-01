package br.thiago.splitmateapp.domain.usecase

import br.thiago.splitmateapp.domain.repository.SplitRepository

class GetSplitsUseCase(private val repository: SplitRepository) {
    suspend operator fun invoke() = repository.getAll()
}
