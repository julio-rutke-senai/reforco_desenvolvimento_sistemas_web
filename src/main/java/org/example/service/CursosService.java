package org.example.service;

import org.example.data.DataBase;
import org.example.model.Curso;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CursosService extends Service {


    public CursosService(DataBase dataBase) {
        super(dataBase);
    }

    public void adicionar(Curso c) {
        Objects.requireNonNull(c, "curso null");
        if (dataBase.getCursos().contains(c)) throw new IllegalArgumentException("curso duplicado: " + c.getTitulo());
        dataBase.getCursos().add(c);
    }

    public List<Curso> listar() {
        return List.copyOf(dataBase.getCursos());
    }

    public List<Curso> buscarPorPrefixo(String prefixo) {
        String p = Objects.requireNonNullElse(prefixo, "").toLowerCase();
        return dataBase.getCursos().stream()
                .filter(c -> c.getTitulo().toLowerCase().startsWith(p))
                .sorted(Comparator.comparing(Curso::getTitulo))
                .collect(Collectors.toList());
    }

    public int cargaTotal() {
        return dataBase.getCursos().stream().mapToInt(Curso::getCargaHoraria).sum();
    }

}
