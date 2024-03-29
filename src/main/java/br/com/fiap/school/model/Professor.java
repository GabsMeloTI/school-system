package br.com.fiap.school.model;
import br.com.fiap.school.dto.aluno.ListagemAluno;
import br.com.fiap.school.dto.professor.AtualizarProfessor;
import br.com.fiap.school.dto.professor.CadastrarProfessor;
import br.com.fiap.school.dto.professor.ListagemProfessor;
import br.com.fiap.school.dto.turma.AtualizarTurma;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ListIterator;

@Getter @Setter
@NoArgsConstructor

@Entity
@Table(name = "cp_professor")
@EntityListeners(ListIterator.class)
public class Professor {
    @Id
    @GeneratedValue
    @Column(name = "cd_professor")
    private Long codigo;

    @Column(name = "nm_professor", length = 100)
    private String nome;

    @Column(name = "ds_especializacao", length = 30)
    private String especializacao;

    @Column(name = "ds_email", length = 150)
    private String email;

    public Professor(CadastrarProfessor dto) {
        nome = dto.nome();
        especializacao = dto.especializacao();
        email = dto.email();
    }

    public void atualizarProfessor(AtualizarProfessor dto) {
        if (dto.nome() != null) {
            nome = dto.nome();
        }
        if (dto.especializacao() != null) {
            especializacao = dto.especializacao();
        }
        if (dto.email() != null) {
            email = dto.email();
        }
    }
}
