package br.thiago.splitmateapp.data.repository


import br.thiago.splitmateapp.data.local.SplitDao
import br.thiago.splitmateapp.data.mapper.toDomain
import br.thiago.splitmateapp.data.mapper.toEntity
import br.thiago.splitmateapp.domain.model.Split
import br.thiago.splitmateapp.domain.repository.SplitRepository

class SplitRepositoryImpl(
    private val dao: SplitDao
) : SplitRepository {
    override suspend fun getAll() = dao.getAllSplits().map { it.toDomain() }
    override suspend fun add(split: Split) = dao.insertSplit(split.toEntity())
    override suspend fun delete(split: Split) = dao.deleteSplit(split.toEntity())
}
