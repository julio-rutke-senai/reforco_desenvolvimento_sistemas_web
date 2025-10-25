package org.example;

import java.util.Objects;

public class Aluno {
    private Long id;
    private String nome;
    private String email;

    public Aluno() {}

    public Aluno(Long id, String nome, String email) {
        this.id = id;
        this.nome = nome;
        setEmail(email);
    }

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public String getEmail() { return email; }

    public void setId(Long id) { this.id = id; }
    public void setNome(String nome) { this.nome = nome; }

    public void setEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("E-mail não pode ser vazio.");
        }
        this.email = email.trim();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Aluno)) return false;
        Aluno aluno = (Aluno) o;
        if (id != null && aluno.id != null) return Objects.equals(id, aluno.id);
        return email != null && aluno.email != null
                && email.equalsIgnoreCase(aluno.email);
    }

    @Override
    public int hashCode() {
        return (id != null) ? Objects.hash(id) : (email == null ? 0 : email.toLowerCase().hashCode());
    }

    @Override
    public String toString() {
        return "Aluno{id=" + id + ", nome='" + nome + "', email='" + email + "'}";
    }
}

