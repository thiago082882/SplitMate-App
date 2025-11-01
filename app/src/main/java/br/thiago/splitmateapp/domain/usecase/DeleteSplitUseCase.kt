package br.thiago.splitmateapp.domain.usecase

import br.thiago.splitmateapp.domain.model.Split
import br.thiago.splitmateapp.domain.repository.SplitRepository

class DeleteSplitUseCase(private val repository: SplitRepository) {
    suspend operator fun invoke(split: Split) = repository.delete(split)
}
