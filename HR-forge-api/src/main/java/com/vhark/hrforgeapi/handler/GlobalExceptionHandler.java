package com.vhark.hrforgeapi.handler;

import static com.vhark.hrforgeapi.handler.ErrorCodes.*;
import static org.springframework.http.HttpStatus.*;

import java.util.HashSet;
import java.util.Set;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(BadCredentialsException.class)
  public ResponseEntity<ExceptionResponse> handleException(BadCredentialsException ex) {
    return ResponseEntity.status(UNAUTHORIZED)
        .body(
            ExceptionResponse.builder()
                .errorCode(BAD_CREDENTIALS.getCode())
                .errorDescription(BAD_CREDENTIALS.getDescription())
                .error(BAD_CREDENTIALS.getDescription())
                .build());
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ExceptionResponse> handleException(MethodArgumentNotValidException ex) {
    Set<String> errors = new HashSet<>();
    ex.getBindingResult()
        .getAllErrors()
        .forEach(
            error -> {
              var errorMessage = error.getDefaultMessage();
              errors.add(errorMessage);
            });
    return ResponseEntity.status(BAD_REQUEST)
        .body(ExceptionResponse.builder().validationErrors(errors).build());
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ExceptionResponse> handleException(Exception ex) {
    ex.printStackTrace();
    return ResponseEntity.status(INTERNAL_SERVER_ERROR)
        .body(
            ExceptionResponse.builder()
                .errorDescription("Internal error")
                .error(ex.getMessage())
                .build());
  }
}
