package com.winterhazel.sigaaforkotlin.network

import com.winterhazel.sigaaforkotlin.entities.*
import okhttp3.FormBody

/**
 * Classe com funções para criar o FormBody das solicitações POST.
 */
class SIGAAFormBuilder(private val parser: SIGAAParser) {
    fun buildLoginForm(login: String, senha: String): FormBody =
        FormBody.Builder().add("dispatch", "logOn")
            .add("urlRedirect", "")
            .add("subsistemaRedirect", "")
            .add("acao", "")
            .add("acessibilidade", "")
            .add("user.login", login)
            .add("user.senha", senha)
            .build()

    fun buildOpenPortalDisciplinaPeloPortalDiscenteForm(
        disciplina: Disciplina,
        javaxViewState: String
    ): FormBody = FormBody.Builder()
        .add(disciplina.formAcessarTurmaVirtual, disciplina.formAcessarTurmaVirtual)
        .add("javax.faces.ViewState", javaxViewState)
        .add(disciplina.formAcessarTurmaVirtualCompleto, disciplina.formAcessarTurmaVirtualCompleto)
        .add("frontEndIdTurma", disciplina.frontEndIdTurma)
        .build()

    fun buildOpenPortalDisciplinaPelasTurmasForm(
        disciplina: Disciplina,
        javaxViewState: String
    ): FormBody = FormBody.Builder()
        .add(disciplina.formAcessarTurmaVirtual, disciplina.formAcessarTurmaVirtual)
        .add("javax.faces.ViewState", javaxViewState)
        .add(disciplina.formAcessarTurmaVirtualCompleto, disciplina.formAcessarTurmaVirtualCompleto)
        .add("frontEndIdTurma", disciplina.frontEndIdTurma)
        .add("inciadoPelaBusca", "true")
        .add("paginaListaTurmasOrigem", "/portais/discente/turmas.jsp")
        .build()

    fun buildOpenPaginaPortalDisciplinaForm(
        body: String,
        pagina: Int,
        javaxViewState: String
    ): FormBody {
        val args = parser.getArgsBotaoPortalDisciplina(body, pagina)
        return FormBody.Builder().add("formMenu", "formMenu")
            .add(args[0][0], args[0][1])
            .add("javax.faces.ViewState", javaxViewState)
            .add(args[1][0], args[1][1])
            .build()
    }

    fun buildOpenNoticiaForm(noticia: Noticia, javaxViewState: String): FormBody =
        FormBody.Builder().add(noticia.jIdJsp, noticia.jIdJsp)
            .add("javax.faces.ViewState", javaxViewState)
            .add(noticia.jIdJspCompleto, noticia.jIdJspCompleto).add("id", noticia.id.toString())
            .build()

    fun buildOpenConteudoForm(conteudo: Conteudo, javaxViewState: String): FormBody =
        FormBody.Builder().add(conteudo.jIdJsp, conteudo.jIdJsp)
            .add("javax.faces.ViewState", javaxViewState)
            .add(conteudo.jIdJspCompleto, conteudo.jIdJspCompleto).add("id", conteudo.id.toString())
            .build()

    fun buildOpenPaginaQuestionarioPeloPortalDiscenteForm(
        questionario: Questionario,
        disciplina: Disciplina,
        javaxViewState: String
    ): FormBody = FormBody.Builder().add("formAtividades", "formAtividades")
        .add("javax.faces.ViewState", javaxViewState)
        .add(
            "formAtividades:visualizarQuestionarioTurmaVirtual",
            "formAtividades:visualizarQuestionarioTurmaVirtual"
        )
        .add("id", questionario.id.toString())
        .add(
            "idTurma",
            disciplina.id
                ?: "31464" // O segundo ID é de uma turma pública. O questionário abre normalmente mesmo assim
        )
        .build()

    fun buildDownloadArquivoForm(arquivo: Arquivo, javaxViewState: String): FormBody =
        FormBody.Builder().add("formAva", "formAva")
            .add("javax.faces.ViewState", javaxViewState)
            .add(arquivo.jIdJsp, arquivo.jIdJsp)
            .add("id", arquivo.id)
            .build()
}
