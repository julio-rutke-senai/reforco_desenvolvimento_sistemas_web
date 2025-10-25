package org.example;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class CatalogoAlunos {
    private final List<Aluno> alunos = new ArrayList<>();
    private final AtomicLong seq = new AtomicLong(1L);

    public Aluno criar(String nome, String email) {
        Aluno aluno = new Aluno(seq.getAndIncrement(), nome, email);
        alunos.add(aluno);
        return aluno;
    }

    public List<Aluno> listar() {
        return Collections.unmodifiableList(alunos);
    }

    public Optional<Aluno> buscarPorId(Long id) {
        return alunos.stream().filter(a -> Objects.equals(a.getId(), id)).findFirst();
    }

    public Optional<Aluno> buscarPorEmail(String email) {
        if (email == null) return Optional.empty();
        String key = email.trim().toLowerCase();
        return alunos.stream()
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
        return alunos.removeIf(a -> Objects.equals(a.getId(), id));
    }
}
