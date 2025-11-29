package org.example.service;

import org.example.data.CursoRepository;
import org.example.data.DataBase;
import org.example.dto.CursoDTORequest;
import org.example.dto.CursoDTOResponse;
import org.example.dto.NovoCursoDTOResponse;
import org.example.exceptions.RegrasNegocioException;
import org.example.model.Curso;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
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

    public NovoCursoDTOResponse adicionar(CursoDTORequest cursoDTORequest) throws Exception {
        Objects.requireNonNull(cursoDTORequest, "curso não informado!");

        Curso cursoPeloTitulo = cursoRepository.findByTituloEqualsIgnoreCase(cursoDTORequest.getTitulo());

        if(Optional.ofNullable(cursoPeloTitulo).isPresent())
            throw new RegrasNegocioException("Titulo já Cadastado");

        Curso curso = new Curso(cursoDTORequest.getTitulo(), cursoDTORequest.getCargaHoraria());

        cursoRepository.save(curso);

        NovoCursoDTOResponse response = new NovoCursoDTOResponse();
        response.setMensagem("Curso Criado com Sucesso");
        response.setDataCriacao(LocalDateTime.now());

        return response;
    }

    public List<CursoDTOResponse> listar() {
        List<CursoDTOResponse> cursos = cursoRepository.retornarDTOCurso();

        return cursos;
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
