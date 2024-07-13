package com.mahumane.todolist.config.Exceptions;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class DataIntegrityException {
    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseBody
    public ResponseEntity<?> HandleDataIntegrity(){
        return ResponseEntity.status(HttpStatus.CONFLICT)
        .body(new ErroResponse("Username already exists"));
    }

    public static class ErroResponse{
        private String message;

        public ErroResponse(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        
    }
}

