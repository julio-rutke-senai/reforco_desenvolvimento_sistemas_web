package org.example.data;

import org.example.model.Aluno;
import org.example.model.Curso;
import org.example.model.Matricula;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class DataBase {

    private final List<Curso> cursos = new ArrayList<>();

    private final List<Aluno> alunos = new ArrayList<>();
    private final AtomicLong seqAluno = new AtomicLong(1L);

    private final List<Matricula> matriculas = new ArrayList<>();
    private final AtomicLong seqMatricula = new AtomicLong(1L);

    public List<Curso> getCursos() {
        return cursos;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public AtomicLong getSeqAluno() {
        return seqAluno;
    }

    public List<Matricula> getMatriculas() {
        return matriculas;
    }

    public AtomicLong getSeqMatricula() {
        return seqMatricula;
    }
}
