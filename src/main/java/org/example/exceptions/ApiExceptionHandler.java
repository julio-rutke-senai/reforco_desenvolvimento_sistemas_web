package org.example.exceptions;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(RegrasNegocioException.class)
    public ResponseEntity<ResponseDTOError> handleBusiness(RegrasNegocioException ex,
                                                  org.springframework.web.context.request.WebRequest request) {
        ResponseDTOError body = new ResponseDTOError(
                LocalDateTime.now(),
                HttpStatus.CONFLICT.value(),
                ex.getMessage()
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(body);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ResponseDTOError> handleDataIntegrity(DataIntegrityViolationException ex,
                                                       org.springframework.web.context.request.WebRequest request) {
        ResponseDTOError body = new ResponseDTOError(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Operação não permitida pelos vínculos ou restrições do banco."
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDTOError> handleGeneric(Exception ex,
                                                 org.springframework.web.context.request.WebRequest request) {
        ResponseDTOError body = new ResponseDTOError(
                LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Ocorreu um erro inesperado. Tente novamente mais tarde."
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
    }
}
