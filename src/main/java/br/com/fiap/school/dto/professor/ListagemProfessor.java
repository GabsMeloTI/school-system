package br.com.fiap.school.dto.professor;

import br.com.fiap.school.model.Professor;

public record ListagemProfessor(Long codigo, String nome, String especializacao, String email) {

        public ListagemProfessor(Professor professor) {
            this(professor.getCodigo(), professor.getNome(), professor.getEspecializacao(), professor.getEmail());
        }
}
