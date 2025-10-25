package org.example.service;

import org.example.data.DataBase;
import org.example.model.Aluno;
import org.example.model.Curso;
import org.example.model.Matricula;

import java.time.LocalDate;
import java.util.*;

public class MatriculasService extends Service {

    private final AlunosService alunosService;
    private final CursosService cursosService;

    public MatriculasService(DataBase dataBase, AlunosService alunosService, CursosService cursosService) {
        super(dataBase);
        this.alunosService = alunosService;
        this.cursosService = cursosService;
    }

    public Matricula matricular(Aluno aluno, Curso curso, LocalDate data) {
        if (aluno == null || curso == null)
            throw new IllegalArgumentException("Aluno e Curso são obrigatórios.");

        // Verifica duplicidade
        boolean existe = dataBase.getMatriculas().stream()
                .anyMatch(m -> m.getAluno().equals(aluno) && m.getCurso().equals(curso));

        if (existe) {
            throw new IllegalStateException("Matrícula já existente para este aluno neste curso.");
        }

        Matricula nova = new Matricula(dataBase.getSeqMatricula().getAndIncrement(), aluno, curso, data);
        dataBase.getMatriculas().add(nova);
        return nova;
    }

    public List<Matricula> listar() {
        return Collections.unmodifiableList(dataBase.getMatriculas());
    }

    public List<Matricula> listarPorAluno(Aluno aluno) {
        return dataBase.getMatriculas().stream().filter(m -> m.getAluno().equals(aluno)).toList();
    }

    public List<Matricula> listarPorCurso(Curso curso) {
        return dataBase.getMatriculas().stream().filter(m -> m.getCurso().equals(curso)).toList();
    }

    public boolean cancelar(Long id) {
        return dataBase.getMatriculas().removeIf(m -> Objects.equals(m.getId(), id));
    }

    public Optional<Matricula> buscarPorId(Long id) {
        return dataBase.getMatriculas().stream().filter(m -> Objects.equals(m.getId(), id)).findFirst();
    }

    public Optional<Aluno> buscarAlunoPorEmail(String email) {
        return alunosService.buscarPorEmail(email);
    }

    public List<Curso> listarCursos() {
        return cursosService.listar();
    }
}
