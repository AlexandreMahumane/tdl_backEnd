package com.mahumane.todolist.config.Exceptions;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class NoSuchElementEx {

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseBody
    public ResponseEntity<?> HandlerNoSuchElementException(){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body(new ErrorResponse("Task not found"));

    }

    public static class ErrorResponse{
        private String message;

        public ErrorResponse(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

    }
}
