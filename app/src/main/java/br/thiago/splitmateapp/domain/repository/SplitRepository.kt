package br.thiago.splitmateapp.domain.repository



import br.thiago.splitmateapp.domain.model.Split

interface SplitRepository {
    suspend fun getAll(): List<Split>
    suspend fun add(split: Split)
    suspend fun delete(split: Split)
}
