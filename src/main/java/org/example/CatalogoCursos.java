package org.example;

import java.util.*;
import java.util.stream.Collectors;

public class CatalogoCursos {
    private final List<Curso> cursos = new ArrayList<>();

    public void adicionar(Curso c) {
        Objects.requireNonNull(c, "curso null");
        if (cursos.contains(c)) throw new IllegalArgumentException("curso duplicado: " + c.getTitulo());
        cursos.add(c);
    }

    public List<Curso> listar() {
        return List.copyOf(cursos);
    }

    public List<Curso> buscarPorPrefixo(String prefixo) {
        String p = Objects.requireNonNullElse(prefixo, "").toLowerCase();
        return cursos.stream()
                .filter(c -> c.getTitulo().toLowerCase().startsWith(p))
                .sorted(Comparator.comparing(Curso::getTitulo))
                .collect(Collectors.toList());
    }

    public int cargaTotal() {
        return cursos.stream().mapToInt(Curso::getCargaHoraria).sum();
    }
}

