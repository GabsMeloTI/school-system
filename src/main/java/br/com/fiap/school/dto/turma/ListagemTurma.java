package br.com.fiap.school.dto.turma;

import br.com.fiap.school.model.Turma;

public record ListagemTurma(Long codigo, String nome, Integer qtdAlunos, Boolean ativo) {
    public ListagemTurma(Turma turma) {
        this(turma.getCodigo(), turma.getNome(), turma.getQtdAlunos(), turma.getAtivo());
    }
}

