package com.vhark.hrforgeapi.handler;

import static com.vhark.hrforgeapi.handler.ErrorCodes.*;
import static org.springframework.http.HttpStatus.*;

import com.vhark.hrforgeapi.department.exceptions.DepartmentNameIsAlreadyInUseException;
import com.vhark.hrforgeapi.department.exceptions.DepartmentNotFoundException;
import com.vhark.hrforgeapi.department.exceptions.InvalidDepartmentIdException;
import com.vhark.hrforgeapi.employee.exceptions.EmailIsAlreadyInUseException;
import com.vhark.hrforgeapi.employee.exceptions.EmployeeNotFoundException;
import com.vhark.hrforgeapi.employee.exceptions.InvalidEmployeeIdException;
import com.vhark.hrforgeapi.personal.exceptions.InvalidPasswordException;
import com.vhark.hrforgeapi.position.exceptions.InvalidPositionIdException;
import com.vhark.hrforgeapi.position.exceptions.PositionNameIsAlreadyInUseException;
import com.vhark.hrforgeapi.position.exceptions.PositionNotFoundException;
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
    return ResponseEntity.status(BAD_CREDENTIALS.getHttpStatus())
        .body(
            ExceptionResponse.builder()
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

  @ExceptionHandler(EmployeeNotFoundException.class)
  public ResponseEntity<ExceptionResponse> handleException(EmployeeNotFoundException ex) {
    return ResponseEntity.status(EMPLOYEE_NOT_FOUND.getHttpStatus())
        .body(
            ExceptionResponse.builder()
                .errorDescription(EMPLOYEE_NOT_FOUND.getDescription())
                .error(ex.getMessage())
                .build());
  }

  @ExceptionHandler(EmailIsAlreadyInUseException.class)
  public ResponseEntity<ExceptionResponse> handleException(EmailIsAlreadyInUseException ex) {
    return ResponseEntity.status(EMAIL_ALREADY_IN_USE.getHttpStatus())
        .body(
            ExceptionResponse.builder()
                .errorDescription(EMAIL_ALREADY_IN_USE.getDescription())
                .error(ex.getMessage())
                .build());
  }

  @ExceptionHandler(PositionNotFoundException.class)
  public ResponseEntity<ExceptionResponse> handleException(PositionNotFoundException ex) {
    return ResponseEntity.status(POSITION_NOT_FOUND.getHttpStatus())
        .body(
            ExceptionResponse.builder()
                .errorDescription(POSITION_NOT_FOUND.getDescription())
                .error(ex.getMessage())
                .build());
  }

  @ExceptionHandler(PositionNameIsAlreadyInUseException.class)
  public ResponseEntity<ExceptionResponse> handleException(PositionNameIsAlreadyInUseException ex) {
    return ResponseEntity.status(POSITION_NAME_IN_USE.getHttpStatus())
        .body(
            ExceptionResponse.builder()
                .errorDescription(POSITION_NAME_IN_USE.getDescription())
                .error(ex.getMessage())
                .build());
  }

  @ExceptionHandler(DepartmentNotFoundException.class)
  public ResponseEntity<ExceptionResponse> handleException(DepartmentNotFoundException ex) {
    return ResponseEntity.status(DEPARTMENT_NOT_FOUND.getHttpStatus())
        .body(
            ExceptionResponse.builder()
                .errorDescription(DEPARTMENT_NOT_FOUND.getDescription())
                .error(ex.getMessage())
                .build());
  }

  @ExceptionHandler(DepartmentNameIsAlreadyInUseException.class)
  public ResponseEntity<ExceptionResponse> handleException(
      DepartmentNameIsAlreadyInUseException ex) {
    return ResponseEntity.status(DEPARTMENT_NAME_IN_USE.getHttpStatus())
        .body(
            ExceptionResponse.builder()
                .errorDescription(DEPARTMENT_NAME_IN_USE.getDescription())
                .error(ex.getMessage())
                .build());
  }

  @ExceptionHandler(InvalidEmployeeIdException.class)
  public ResponseEntity<ExceptionResponse> handleException(InvalidEmployeeIdException ex) {
    return ResponseEntity.status(INVALID_EMPLOYEE_ID.getHttpStatus())
        .body(
            ExceptionResponse.builder()
                .errorDescription(INVALID_EMPLOYEE_ID.getDescription())
                .error(ex.getMessage())
                .build());
  }

  @ExceptionHandler(InvalidPositionIdException.class)
  public ResponseEntity<ExceptionResponse> handleException(InvalidPositionIdException ex) {
    return ResponseEntity.status(INVALID_POSITION_ID.getHttpStatus())
        .body(
            ExceptionResponse.builder()
                .errorDescription(INVALID_POSITION_ID.getDescription())
                .error(ex.getMessage())
                .build());
  }

  @ExceptionHandler(InvalidDepartmentIdException.class)
  public ResponseEntity<ExceptionResponse> handleException(InvalidDepartmentIdException ex) {
    return ResponseEntity.status(INVALID_DEPARTMENT_ID.getHttpStatus())
        .body(
            ExceptionResponse.builder()
                .errorDescription(INVALID_DEPARTMENT_ID.getDescription())
                .error(ex.getMessage())
                .build());
  }

  @ExceptionHandler(InvalidPasswordException.class)
  public ResponseEntity<ExceptionResponse> handleException(InvalidPasswordException ex) {
    return ResponseEntity.status(INVALID_PASSWORD.getHttpStatus())
        .body(
            ExceptionResponse.builder()
                .errorDescription(INVALID_PASSWORD.getDescription())
                .error(ex.getMessage())
                .build());
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
