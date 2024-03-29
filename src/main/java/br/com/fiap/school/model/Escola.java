package br.com.fiap.school.model;
import br.com.fiap.school.dto.escola.AtualizarEscola;
import br.com.fiap.school.dto.escola.CadastrarEscola;
import br.com.fiap.school.dto.escola.ListagemEscola;
import br.com.fiap.school.dto.professor.AtualizarProfessor;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ListIterator;

@Getter @Setter
@NoArgsConstructor

@Entity
@Table(name = "cp_escola")
@EntityListeners(ListIterator.class)
public class Escola {
    @Id
    @GeneratedValue
    @Column(name = "cd_escola")
    private Long codigo;

    @Column(name = "nm_escola", length = 100)
    private String nome;

    @Column(name = "nr_telefone", length = 11)
    private String telefone;

    @Column(name = "nm_diretor", length = 100)
    private String diretor;

    @Column(name = "nr_funcionarios")
    private Integer qtdFuncionarios;

    @Column(name = "ds_cantina", nullable = false)
    private Boolean cantina;

    @Column(name = "tp_escola")
    @Enumerated(EnumType.STRING)
    private TipoEscola tipoEscola;

    @Column(name = "ds_estado")
    @Enumerated(EnumType.STRING)
    private Estados estado;


    public Escola(CadastrarEscola dto) {
            nome = dto.nome();
            telefone = dto.telefone();
            diretor = dto.diretor();
            qtdFuncionarios = dto.qtdFuncionarios();
            cantina = dto.cantina();
            tipoEscola = dto.tipoEscola();
            estado = dto.estado();
    }

    public void atualizarEscola(AtualizarEscola dto) {
        if (dto.nome() != null) {
            nome = dto.nome();
        }
        if (dto.telefone() != null) {
            telefone = dto.telefone();
        }
        if (dto.diretor() != null) {
            diretor = dto.diretor();
        }
        if (dto.qtdFuncionarios() != null) {
            qtdFuncionarios = dto.qtdFuncionarios();
        }
        if (dto.cantina() != null) {
            cantina = dto.cantina();
        }
    }
}
