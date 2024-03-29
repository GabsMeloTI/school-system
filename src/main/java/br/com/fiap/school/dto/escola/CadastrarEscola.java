package br.com.fiap.school.dto.escola;

import br.com.fiap.school.model.Estados;
import br.com.fiap.school.model.TipoEscola;

public record CadastrarEscola(String nome, String telefone, String diretor, Integer qtdFuncionarios, Boolean cantina, TipoEscola tipoEscola, Estados estado) {
}
