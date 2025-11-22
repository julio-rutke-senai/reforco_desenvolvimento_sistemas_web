package org.example.controller.rest;

import org.example.model.Aluno;
import org.example.service.AlunosService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunoRestController {

    private final AlunosService service;

    public AlunoRestController(AlunosService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> criar(@RequestBody Aluno aluno) {
        Aluno resp = service.criar(aluno.getNome(), aluno.getEmail());
        return ResponseEntity
                .created(URI.create("/alunos/" + resp.getId()))
                .body(resp);
    }

    @GetMapping
    public List<Aluno> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public Aluno buscar(@PathVariable Long id) {
        return service.buscarPorId(id).get();
    }

    @PutMapping("/{id}")
    public ResponseEntity atualizar(@PathVariable Long id,
                                      @RequestBody Aluno aluno) {
        service.atualizar(id, aluno.getNome(), aluno.getEmail());
        return ResponseEntity.ok("Aluno atualizado!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        service.remover(id);
        return ResponseEntity.noContent().build();
    }
}
