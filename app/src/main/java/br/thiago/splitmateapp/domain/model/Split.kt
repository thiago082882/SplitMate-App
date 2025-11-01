package br.thiago.splitmateapp.domain.model


data class Split(
    val id: Int,
    val date: String,
    val total: Double,
    val people: Int
) {
    val perPerson: Double
        get() = total / people
}
