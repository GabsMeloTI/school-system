package br.com.fiap.school.controller;

import br.com.fiap.school.dto.professor.AtualizarProfessor;
import br.com.fiap.school.dto.professor.ListagemProfessor;
import br.com.fiap.school.dto.turma.AtualizarTurma;
import br.com.fiap.school.dto.turma.CadastrarTurma;
import br.com.fiap.school.dto.turma.ListagemTurma;
import br.com.fiap.school.model.Turma;
import br.com.fiap.school.repository.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/turma")
public class TurmaController {

    @Autowired
    private TurmaRepository turmaRepository;

    @GetMapping
    public ResponseEntity<List<ListagemTurma>> listar(Pageable paginacao) {
        var lista = turmaRepository.findAll(paginacao).stream().map(ListagemTurma::new).toList();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("{codigo}")
    public ResponseEntity<ListagemTurma> pesquisa(@PathVariable("codigo") Long codigo) {
        var pesquisa = turmaRepository.getReferenceById(codigo);
        return ResponseEntity.ok(new ListagemTurma(pesquisa));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ListagemTurma> cadastrar(@RequestBody CadastrarTurma dto, UriComponentsBuilder uriBuilder) {
        var turma = new Turma(dto);
        turmaRepository.save(turma);
        var url = uriBuilder.path("/turma/{codigo}").buildAndExpand(turma.getCodigo()).toUri();
        return ResponseEntity.created(url).body(new ListagemTurma(turma));
    }

    @PutMapping("{codigo}")
    @Transactional
    public ResponseEntity<ListagemTurma> altera(@PathVariable("codigo") Long codigo, @RequestBody AtualizarTurma dto) {
        var turma = turmaRepository.getReferenceById(codigo);
        turma.atualizarTurma(dto);
        return ResponseEntity.ok(new ListagemTurma(turma));
    }

    @DeleteMapping("{codigo}")
    @Transactional
    public ResponseEntity<Void> deletar(@PathVariable("codigo") Long codigo) {
        turmaRepository.deleteById(codigo);
        return ResponseEntity.noContent().build();
    }
}
