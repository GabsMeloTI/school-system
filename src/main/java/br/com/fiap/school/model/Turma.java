package br.com.fiap.school.model;

import br.com.fiap.school.dto.turma.AtualizarTurma;
import br.com.fiap.school.dto.turma.CadastrarTurma;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor

@Entity
@Table(name = "cp_turmaa")
public class Turma {
    @Id
    @GeneratedValue
    @Column(name = "cd_turma")
    private Long codigo;

    @Column(name = "nm_turma", length = 30)
    private String nome;

    @Column(name = "nr_alunos")
    private Integer qtdAlunos;

    @Column(name = "ds_ativo")
    private Boolean ativo;

    public Turma(CadastrarTurma dto) {
        nome = dto.nome();
        qtdAlunos = dto.qtdAlunos();
        ativo = dto.ativo();
    }

    public void atualizarTurma(AtualizarTurma dto) {
        if (dto.nome() != null) {
            nome = dto.nome();
        }
        if (dto.qtdAlunos() != null) {
            qtdAlunos = dto.qtdAlunos();
        }
        if (dto.ativo() != null) {
            ativo = dto.ativo();
        }
    }
}
