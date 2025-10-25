package org.example;

import java.time.LocalDate;
import java.util.Objects;

public class Matricula {
    private Long id;
    private Aluno aluno;
    private Curso curso;
    private LocalDate data;

    public Matricula() {}

    public Matricula(Long id, Aluno aluno, Curso curso, LocalDate data) {
        this.id = id;
        setAluno(aluno);
        setCurso(curso);
        setData(data);
    }

    public Long getId() { return id; }
    public Aluno getAluno() { return aluno; }
    public Curso getCurso() { return curso; }
    public LocalDate getData() { return data; }

    public void setId(Long id) { this.id = id; }

    public void setAluno(Aluno aluno) {
        if (aluno == null) throw new IllegalArgumentException("Aluno não pode ser nulo.");
        if (aluno.getEmail() == null || aluno.getEmail().trim().isEmpty())
            throw new IllegalArgumentException("Aluno com e-mail vazio não é permitido.");
        this.aluno = aluno;
    }

    public void setCurso(Curso curso) {
        if (curso == null) throw new IllegalArgumentException("Curso não pode ser nulo.");
        this.curso = curso;
    }

    public void setData(LocalDate data) {
        this.data = (data == null) ? LocalDate.now() : data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Matricula)) return false;
        Matricula that = (Matricula) o;
        return Objects.equals(aluno, that.aluno) &&
                Objects.equals(curso, that.curso);
    }

    @Override
    public int hashCode() {
        return Objects.hash(aluno, curso);
    }

    @Override
    public String toString() {
        return "Matricula{id=" + id +
                ", aluno=" + (aluno == null ? null : aluno.getEmail()) +
                ", curso=" + (curso == null ? null : curso.getTitulo()) +
                ", data=" + data + "}";
    }
}

