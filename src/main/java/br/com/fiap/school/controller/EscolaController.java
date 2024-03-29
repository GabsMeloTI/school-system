package br.com.fiap.school.controller;

import br.com.fiap.school.dto.aluno.AtualizarAluno;
import br.com.fiap.school.dto.aluno.ListagemAluno;
import br.com.fiap.school.dto.escola.AtualizarEscola;
import br.com.fiap.school.dto.escola.CadastrarEscola;
import br.com.fiap.school.dto.escola.ListagemEscola;
import br.com.fiap.school.model.Escola;
import br.com.fiap.school.repository.EscolaRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/escola")
public class EscolaController {

    @Autowired
    private EscolaRepository escolaRepository;

    @GetMapping
    public ResponseEntity<List<ListagemEscola>> listar(Pageable paginacao) {
        var lista = escolaRepository.findAll(paginacao).stream().map(ListagemEscola::new).toList();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("{codigo}")
    public ResponseEntity<ListagemEscola> pesquisa(@PathVariable("codigo") Long codigo) {
        var pesquisa = escolaRepository.getReferenceById(codigo);
        return ResponseEntity.ok(new ListagemEscola(pesquisa));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ListagemEscola> cadastro(@RequestBody CadastrarEscola dto, UriComponentsBuilder uriBuilder) {
        var escola = new Escola(dto);
        escolaRepository.save(escola);
        var url = uriBuilder.path("/escola/{codigo}").buildAndExpand(escola.getCodigo()).toUri();
        return ResponseEntity.created(url).body(new ListagemEscola(escola));
    }

    @PutMapping("{codigo}")
    @Transactional
    public ResponseEntity<ListagemEscola> altera(@PathVariable("codigo") Long codigo, @RequestBody AtualizarEscola dto) {
        var escola = escolaRepository.getReferenceById(codigo);
        escola.atualizarEscola(dto);
        return ResponseEntity.ok(new ListagemEscola(escola));
    }

    @DeleteMapping("{codigo}")
    @Transactional
    public ResponseEntity<Void> deletar(@PathVariable("codigo") Long codigo) {
        escolaRepository.deleteById(codigo);
        return ResponseEntity.noContent().build();
    }
}
