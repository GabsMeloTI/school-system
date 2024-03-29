package br.com.fiap.school.dto.escola;

import br.com.fiap.school.model.Escola;
import br.com.fiap.school.model.Estados;
import br.com.fiap.school.model.TipoEscola;

public record ListagemEscola(Long codigo, String nome, String telefone, String diretor, Integer qtdFuncionarios, Boolean cantina, TipoEscola tipoEscola, Estados estado) {

    public ListagemEscola(Escola escola) {
        this(escola.getCodigo(), escola.getNome(), escola.getTelefone(), escola.getDiretor(), escola.getQtdFuncionarios(), escola.getCantina(), escola.getTipoEscola(), escola.getEstado());
    }
}
