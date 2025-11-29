package org.example.dto;

import java.time.LocalDateTime;

public class NovoCursoDTOResponse {

    private String mensagem;
    private LocalDateTime dataCriacao;

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
}
