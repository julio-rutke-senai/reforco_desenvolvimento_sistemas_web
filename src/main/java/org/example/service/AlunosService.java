package org.example.service;

import org.example.data.DataBase;
import org.example.model.Aluno;

import java.util.*;

public class AlunosService extends Service {

    public AlunosService(DataBase dataBase) {
        super(dataBase);
    }

    public Aluno criar(String nome, String email) {
        Aluno aluno = new Aluno(dataBase.getSeqAluno().getAndIncrement(), nome, email);
        dataBase.getAlunos().add(aluno);
        return aluno;
    }

    public List<Aluno> listar() {
        return Collections.unmodifiableList(dataBase.getAlunos());
    }

    public Optional<Aluno> buscarPorId(Long id) {
        return dataBase.getAlunos().stream().filter(a -> Objects.equals(a.getId(), id)).findFirst();
    }

    public Optional<Aluno> buscarPorEmail(String email) {
        if (email == null) return Optional.empty();
        String key = email.trim().toLowerCase();
        return dataBase.getAlunos().stream()
                .filter(a -> a.getEmail() != null && a.getEmail().trim().toLowerCase().equals(key))
                .findFirst();
    }

    public boolean atualizar(Long id, String novoNome, String novoEmail) {
        Optional<Aluno> opt = buscarPorId(id);
        if (opt.isEmpty()) return false;
        Aluno a = opt.get();
        if (novoNome != null) a.setNome(novoNome);
        if (novoEmail != null) a.setEmail(novoEmail); // validação no setter
        return true;
    }

    public boolean remover(Long id) {
        return dataBase.getAlunos().removeIf(a -> Objects.equals(a.getId(), id));
    }

}
