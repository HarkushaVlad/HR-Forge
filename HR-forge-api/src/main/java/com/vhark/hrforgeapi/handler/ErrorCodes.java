package com.vhark.hrforgeapi.handler;

import static org.springframework.http.HttpStatus.*;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCodes {
  NO_CODE(NOT_IMPLEMENTED, "No code"),
  INCORRECT_CURRENT_PASSWORD(BAD_REQUEST, "Current password is incorrect"),
  NEW_PASSWORD_DOES_NOT_MATCH(BAD_REQUEST, "The new password does not match"),
  BAD_CREDENTIALS(FORBIDDEN, "Login and / or password is incorrect"),
  EMPLOYEE_NOT_FOUND(NOT_FOUND, "Employee not found"),
  EMAIL_ALREADY_IN_USE(BAD_REQUEST, "Email already in use"),
  POSITION_NOT_FOUND(NOT_FOUND, "Position not found"),
  POSITION_NAME_IN_USE(BAD_REQUEST, "Position name already in use"),
  DEPARTMENT_NOT_FOUND(NOT_FOUND, "Department not found"),
  DEPARTMENT_NAME_IN_USE(BAD_REQUEST, "Department name already in use"),
  INVALID_EMPLOYEE_ID(BAD_REQUEST, "Employee ID is invalid"),
  INVALID_POSITION_ID(BAD_REQUEST, "Position ID is invalid");

  private final String description;
  private final HttpStatus httpStatus;

  ErrorCodes(HttpStatus httpStatus, String description) {
    this.description = description;
    this.httpStatus = httpStatus;
  }
}
