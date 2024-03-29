package com.winterhazel.sigaaforkotlin.entities

/**
 * Representa uma referência cadastrada na aba "Referências" de uma disciplina.
 */
data class Referencia(
    val nome: String,
    val tipo: Int,
    val url: String,
    val topicoDeAula: String,
    val disciplina: Disciplina
) {
    companion object {
        const val REFERENCIA_OUTROS = 0
        const val REFERENCIA_SITE = 1
        const val REFERENCIA_LIVRO = 2
    }
}
