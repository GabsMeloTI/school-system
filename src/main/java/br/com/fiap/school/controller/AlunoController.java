package br.com.fiap.school.controller;

import br.com.fiap.school.dto.aluno.AtualizarAluno;
import br.com.fiap.school.dto.aluno.CadastrarAluno;
import br.com.fiap.school.dto.aluno.ListagemAluno;
import br.com.fiap.school.model.Aluno;
import br.com.fiap.school.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/aluno")
public class AlunoController {

    @Autowired
    private AlunoRepository alunoRepository;

    @GetMapping
    public ResponseEntity<List<ListagemAluno>> listar(Pageable paginacao) {
        var lista = alunoRepository.findAll(paginacao).stream().map(ListagemAluno::new).toList();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("{codigo}")
    public ResponseEntity<ListagemAluno> pesquisa(@PathVariable("codigo") Long codigo) {
        var pesquisa = alunoRepository.getReferenceById(codigo);
        return ResponseEntity.ok(new ListagemAluno(pesquisa));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ListagemAluno> cadastro(@RequestBody CadastrarAluno dto, UriComponentsBuilder uriBuilder) {
        var aluno = new Aluno(dto);
        alunoRepository.save(aluno);
        var url = uriBuilder.path("/aluno/{codigo}").buildAndExpand(aluno.getCodigo()).toUri();
        return ResponseEntity.created(url).body(new ListagemAluno(aluno));
    }

    @PutMapping("{codigo}")
    @Transactional
    public ResponseEntity<ListagemAluno> altera(@PathVariable("codigo") Long codigo, @RequestBody AtualizarAluno dto) {
        var aluno = alunoRepository.getReferenceById(codigo);
        aluno.atualizarAluno(dto);
        return ResponseEntity.ok(new ListagemAluno(aluno));
    }

    @DeleteMapping("{codigo}")
    @Transactional
    public ResponseEntity<Void> deletar(@PathVariable("codigo") Long codigo) {
        alunoRepository.deleteById(codigo);
        return ResponseEntity.noContent().build();
    }
}
