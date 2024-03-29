package br.com.fiap.school.dto.endereco;

import br.com.fiap.school.model.Endereco;
import br.com.fiap.school.model.Estados;

public record ListagemEndereco(String logradouro, Integer numero, String complemento, String bairro, String cidade, Estados estado, String cep) {

    public ListagemEndereco(Endereco endereco) {
        this(endereco.getLogradouro(), endereco.getNumero(), endereco.getComplemento(), endereco.getBairro(), endereco.getCidade(), endereco.getEstado(), endereco.getCep());
    }
}
