package com.mahumane.todolist.config.Exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class BadCredentials {

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseBody
    public ResponseEntity<?> HandleBadCredentialsException(){
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(new ErrorResponse("Username or Password is invalid."));
    }

    public static class ErrorResponse{
        
        public ErrorResponse(String message) {
        this.message = message;
    }
        private String message;

        public String getMessage() {
            return message;
        }
        
    }
}
