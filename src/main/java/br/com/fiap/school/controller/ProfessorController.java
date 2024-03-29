package br.com.fiap.school.controller;

import br.com.fiap.school.dto.escola.AtualizarEscola;
import br.com.fiap.school.dto.escola.ListagemEscola;
import br.com.fiap.school.dto.professor.AtualizarProfessor;
import br.com.fiap.school.dto.professor.CadastrarProfessor;
import br.com.fiap.school.dto.professor.ListagemProfessor;
import br.com.fiap.school.model.Professor;
import br.com.fiap.school.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/professor")
public class ProfessorController {

    @Autowired
    private ProfessorRepository professorRepository;

    @GetMapping
    public ResponseEntity<List<ListagemProfessor>> listar(Pageable paginacao) {
        var lista = professorRepository.findAll(paginacao).stream().map(ListagemProfessor::new).toList();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("{codigo}")
    public ResponseEntity<ListagemProfessor> pesquisa(@PathVariable("codigo") Long codigo) {
        var pesquisa = professorRepository.getReferenceById(codigo);
        return ResponseEntity.ok(new ListagemProfessor(pesquisa));
    }

    @PostMapping
    @Transactional
        public ResponseEntity<ListagemProfessor> cadastro(@RequestBody CadastrarProfessor dto, UriComponentsBuilder uriBuilder) {
        var professor = new Professor(dto);
        professorRepository.save(professor);
        var url = uriBuilder.path("/professor/{codigo}").buildAndExpand(professor.getCodigo()).toUri();
        return ResponseEntity.created(url).body(new ListagemProfessor(professor));
    }

    @PutMapping("{codigo}")
    @Transactional
    public ResponseEntity<ListagemProfessor> altera(@PathVariable("codigo") Long codigo, @RequestBody AtualizarProfessor dto) {
        var professor = professorRepository.getReferenceById(codigo);
        professor.atualizarProfessor(dto);
        return ResponseEntity.ok(new ListagemProfessor(professor));
    }

    @DeleteMapping("{codigo}")
    @Transactional
    public ResponseEntity<Void> deletar(@PathVariable("codigo") Long codigo) {
        professorRepository.deleteById(codigo);
        return ResponseEntity.noContent().build();
    }
}
