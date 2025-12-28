package hu.cubixwebshop.catalogservice.config;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleAll(Exception e) {
        System.out.println("Internal Server Error"+ e);
        return ResponseEntity.status(500).body("Internal Server Error"+e.getMessage());
    }
}
