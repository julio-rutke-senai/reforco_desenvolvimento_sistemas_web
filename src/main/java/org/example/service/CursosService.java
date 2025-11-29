package org.example.service;

import org.example.data.CursoRepository;
import org.example.data.DataBase;
import org.example.model.Curso;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
public class CursosService{

    private final CursoRepository cursoRepository;

    public CursosService(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    public void adicionar(Curso curso) {
        Objects.requireNonNull(curso, "curso null");
        cursoRepository.save(curso);
    }

    public List<Curso> listar() {
        return cursoRepository.findAll();
    }

    public List<Curso> buscarPorPrefixo(String prefixo) {
        List<Curso> byTituloStartingWith = cursoRepository.findByTituloStartingWith(prefixo);
        return byTituloStartingWith;
    }

    public int cargaTotal() {
        return cursoRepository.retornarSomaCargaHoraria();
    }

    public Optional<Curso> incrementarHoras(String titulo, int qtdeHoras) {
        Curso curso = cursoRepository.findByTituloEqualsIgnoreCase(titulo);
        curso.incrementarHoras(qtdeHoras);

        cursoRepository.save(curso);

        return Optional.ofNullable(curso);
    }

    public void excluir(Long id) {
        cursoRepository.deleteById(id);
    }
}
