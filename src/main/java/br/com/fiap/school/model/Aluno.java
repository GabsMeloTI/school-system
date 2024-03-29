package br.com.fiap.school.model;

import br.com.fiap.school.dto.aluno.AtualizarAluno;
import br.com.fiap.school.dto.aluno.CadastrarAluno;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.ListIterator;

@Getter @Setter
@NoArgsConstructor
@Entity
@Table(name = "cp_aluno")
@EntityListeners(ListIterator.class)
public class Aluno {

    @Id
    @GeneratedValue
    @Column(name = "cd_aluno")
    private Long codigo;

    @Column(name = "nm_aluno", length = 100)
    private String nome;

    @Column(name = "dt_nascimento")
    private Date dataNascimento;

    @Column(name = "nr_telefone", length = 11)
    private String telefone;


    public Aluno(CadastrarAluno dto) {
        nome = dto.nome();
        dataNascimento = dto.dataNascimento();
        telefone = dto.telefone();
    }

    public void atualizarAluno(AtualizarAluno dto) {
        if (dto.nome() != null) {
            nome = dto.nome();
        }
        if (dto.telefone() != null) {
            telefone = dto.telefone();
        }
    }
}
