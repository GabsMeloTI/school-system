package br.com.fiap.school.dto.aluno;

import br.com.fiap.school.model.Aluno;
import java.util.Date;

public record ListagemAluno(Long codigo, String nome, Date dataNascimento, String telefone) {
    public ListagemAluno(Aluno aluno) {
       this(aluno.getCodigo(), aluno.getNome(), aluno.getDataNascimento(), aluno.getTelefone());
    }
}
