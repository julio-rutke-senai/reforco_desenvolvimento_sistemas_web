package org.example.dto;

public class CursoDTOResponse {
    private Long id;
    private String titulo;
    private int cargaHoraria;

    public CursoDTOResponse(Long id, String titulo, int cargaHoraria) {
        this.id = id;
        this.titulo = titulo;
        this.cargaHoraria = cargaHoraria;
    }

    public CursoDTOResponse() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }
}
