package org.example.exceptions;

import java.time.LocalDateTime;

public class ResponseDTOError {

    private LocalDateTime timestamp;
    private int status;
    private String erro;

    public ResponseDTOError(LocalDateTime now, int status, String message) {
        this.timestamp = now;
        this.status = status;
        this.erro = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getErro() {
        return erro;
    }

    public void setErro(String erro) {
        this.erro = erro;
    }
}
