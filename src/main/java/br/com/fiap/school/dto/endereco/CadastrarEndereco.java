package br.com.fiap.school.dto.endereco;

import br.com.fiap.school.model.Estados;

public record CadastrarEndereco(String logradouro, Integer numero, String complemento, String bairro, String cidade, Estados estado, String cep) {
}
