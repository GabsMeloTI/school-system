package br.com.fiap.school.model;

import br.com.fiap.school.dto.aluno.AtualizarAluno;
import br.com.fiap.school.dto.aluno.CadastrarAluno;
import br.com.fiap.school.dto.endereco.AtualizarEndereco;
import br.com.fiap.school.dto.endereco.CadastrarEndereco;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.print.attribute.standard.MediaSize;
import java.util.ListIterator;

@Getter @Setter
@NoArgsConstructor

@Entity
@Table(name = "cp_endereco")
@EntityListeners(ListIterator.class)
public class Endereco {
    @Id
    @GeneratedValue
    @Column(name = "cd_endereco")
    private Long codigo;

    @Column(name = "ds_logradouro", length = 30)
    private String logradouro;

    @Column(name = "nr_numero")
    private Integer numero;

    @Column(name = "ds_complemento", length = 100, nullable = false)
    private String complemento;

    @Column(name = "ds_bairro", length = 50)
    private String bairro;

    @Column(name = "ds_cidade", length = 30)
    private String cidade;

    @Column(name = "ds_estado")
    @Enumerated(EnumType.STRING)
    private Estados estado;

    @Column(name = "ds_cep", length = 8)
    private String cep;

    public Endereco(CadastrarEndereco dto) {
        logradouro = dto.logradouro();
        numero = dto.numero();
        complemento = dto.complemento();
        bairro = dto.bairro();
        cidade = dto.cidade();
        estado = dto.estado();
        cep = dto.cep();
    }

    public void atualizarEndereco(AtualizarEndereco dto) {
        if (dto.logradouro() != null) {
            logradouro = dto.logradouro();
        }
        if (dto.numero() != null) {
            numero = dto.numero();
        }
        if (dto.complemento() != null) {
            complemento = dto.complemento();
        }
        if (dto.bairro() != null) {
            bairro = dto.bairro();
        }
        if (dto.cidade() != null) {
            cidade = dto.cidade();
        }
        if (dto.estado() != null) {
            estado = dto.estado();
        }
        if (dto.cep() != null) {
            cep = dto.cep();
        }
    }
}
