package com.example.TestVersioning.TestVersioning.courses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UnsupportedApiVersionException.class)
    public ResponseEntity<String> handleUnsupportedVersion(UnsupportedApiVersionException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)   // Or HttpStatus.NOT_ACCEPTABLE
                .body(ex.getMessage());
    }

}
