package org.example.model;

import java.util.Objects;

public class Curso {
    private final String titulo;
    private int cargaHoraria;

    public Curso(){
        titulo = "Titulo Padrão";
        cargaHoraria = 0;
    }

    public Curso(String titulo, int cargaHoraria) {
        if (titulo == null || titulo.isBlank()) throw new IllegalArgumentException("titulo vazio");
        if (cargaHoraria <= 0) throw new IllegalArgumentException("cargaHoraria <= 0");
        this.titulo = titulo.trim();
        this.cargaHoraria = cargaHoraria;
    }

    public String getTitulo() { return titulo; }
    public int getCargaHoraria() { return cargaHoraria; }

    public void aumentarCarga(int horas) {
        if (horas <= 0) throw new IllegalArgumentException("horas <= 0");
        this.cargaHoraria += horas;
    }

    @Override public String toString() { return titulo + " (" + cargaHoraria + "h)"; }
    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Curso)) return false;
        Curso c = (Curso) o;
        return titulo.equalsIgnoreCase(c.titulo);
    }
    @Override public int hashCode() { return Objects.hash(titulo.toLowerCase()); }

    public void incrementarHoras(int horas) {
        this.cargaHoraria += horas;
    }
}

