package br.com.fiap.school.controller;

import br.com.fiap.school.dto.endereco.AtualizarEndereco;
import br.com.fiap.school.dto.endereco.CadastrarEndereco;
import br.com.fiap.school.dto.endereco.ListagemEndereco;
import br.com.fiap.school.dto.escola.AtualizarEscola;
import br.com.fiap.school.dto.escola.CadastrarEscola;
import br.com.fiap.school.dto.escola.ListagemEscola;
import br.com.fiap.school.model.Endereco;
import br.com.fiap.school.model.Escola;
import br.com.fiap.school.repository.EnderecoRepository;
import br.com.fiap.school.repository.EscolaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @GetMapping
    public ResponseEntity<List<ListagemEndereco>> listar(Pageable paginacao) {
        var lista = enderecoRepository.findAll(paginacao).stream().map(ListagemEndereco::new).toList();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("{codigo}")
    public ResponseEntity<ListagemEndereco> pesquisa(@PathVariable("codigo") Long codigo) {
        var pesquisa = enderecoRepository.getReferenceById(codigo);
        return ResponseEntity.ok(new ListagemEndereco(pesquisa));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ListagemEndereco> cadastro(@RequestBody CadastrarEndereco dto, UriComponentsBuilder uriBuilder) {
        var endereco = new Endereco(dto);
        enderecoRepository.save(endereco);
        var url = uriBuilder.path("/endereco/{codigo}").buildAndExpand(endereco.getCodigo()).toUri();
        return ResponseEntity.created(url).body(new ListagemEndereco(endereco));
    }

    @PutMapping("{codigo}")
    @Transactional
    public ResponseEntity<ListagemEndereco> altera(@PathVariable("codigo") Long codigo, @RequestBody AtualizarEndereco dto) {
        var endereco = enderecoRepository.getReferenceById(codigo);
        endereco.atualizarEndereco(dto);
        return ResponseEntity.ok(new ListagemEndereco(endereco));
    }

    @DeleteMapping("{codigo}")
    @Transactional
    public ResponseEntity<Void> deletar(@PathVariable("codigo") Long codigo) {
        enderecoRepository.deleteById(codigo);
        return ResponseEntity.noContent().build();
    }
}
