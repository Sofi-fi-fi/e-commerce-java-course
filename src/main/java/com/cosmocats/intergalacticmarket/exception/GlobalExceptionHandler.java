package com.cosmocats.intergalacticmarket.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ProblemDetailResponse> handleValidationException(
            MethodArgumentNotValidException ex, HttpServletRequest request) {

        String errors = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> String.format("Field '%s' %s", error.getField(), error.getDefaultMessage()))
                .collect(Collectors.joining("; "));

        ProblemDetailResponse problem = new ProblemDetailResponse(
                URI.create("https://cosmo-cats.market/errors/validation"),
                "Validation failed",
                HttpStatus.BAD_REQUEST.value(),
                errors,
                URI.create(request.getRequestURI())
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problem);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ProblemDetailResponse> handleNotFoundException(
            ResourceNotFoundException ex, HttpServletRequest request) {

        ProblemDetailResponse problem = new ProblemDetailResponse(
                URI.create("https://cosmo-cats.market/errors/not-found"),
                "Resource Not Found",
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                URI.create(request.getRequestURI())
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(problem);
    }
}
