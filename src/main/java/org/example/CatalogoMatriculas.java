package org.example;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class CatalogoMatriculas {
    private final List<Matricula> matriculas = new ArrayList<>();
    private final AtomicLong seq = new AtomicLong(1L);

    /**
     * Regra de negócio:
     *  - Não duplicar matrícula do mesmo aluno no mesmo curso.
     *  - Data padrão = hoje, se não informada.
     */
    public Matricula matricular(Aluno aluno, Curso curso, LocalDate data) {
        if (aluno == null || curso == null)
            throw new IllegalArgumentException("Aluno e Curso são obrigatórios.");

        // Verifica duplicidade
        boolean existe = matriculas.stream()
                .anyMatch(m -> m.getAluno().equals(aluno) && m.getCurso().equals(curso));

        if (existe) {
            throw new IllegalStateException("Matrícula já existente para este aluno neste curso.");
        }

        Matricula nova = new Matricula(seq.getAndIncrement(), aluno, curso, data);
        matriculas.add(nova);
        return nova;
    }

    public List<Matricula> listar() {
        return Collections.unmodifiableList(matriculas);
    }

    public List<Matricula> listarPorAluno(Aluno aluno) {
        return matriculas.stream().filter(m -> m.getAluno().equals(aluno)).toList();
    }

    public List<Matricula> listarPorCurso(Curso curso) {
        return matriculas.stream().filter(m -> m.getCurso().equals(curso)).toList();
    }

    public boolean cancelar(Long id) {
        return matriculas.removeIf(m -> Objects.equals(m.getId(), id));
    }

    public Optional<Matricula> buscarPorId(Long id) {
        return matriculas.stream().filter(m -> Objects.equals(m.getId(), id)).findFirst();
    }
}
