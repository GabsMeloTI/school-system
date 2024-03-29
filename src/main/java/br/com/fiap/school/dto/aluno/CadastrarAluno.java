package br.com.fiap.school.dto.aluno;

import br.com.fiap.school.model.Aluno;

import java.util.Date;

public record CadastrarAluno(String nome, Date dataNascimento, String telefone) {

}
