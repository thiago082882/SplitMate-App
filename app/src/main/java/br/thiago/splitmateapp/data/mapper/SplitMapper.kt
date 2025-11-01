package br.thiago.splitmateapp.data.mapper



import br.thiago.splitmateapp.data.local.SplitEntity
import br.thiago.splitmateapp.domain.model.Split

fun SplitEntity.toDomain() = Split(id, date, total, people)
fun Split.toEntity() = SplitEntity(id, date, total, people)
