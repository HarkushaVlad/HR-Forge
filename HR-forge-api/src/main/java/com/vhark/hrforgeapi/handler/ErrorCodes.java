package com.vhark.hrforgeapi.handler;

import static org.springframework.http.HttpStatus.*;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCodes {
  NO_CODE(NOT_IMPLEMENTED, "No code"),
  INCORRECT_CURRENT_PASSWORD(BAD_REQUEST, "Поточний пароль неправильний"),
  NEW_PASSWORD_DOES_NOT_MATCH(BAD_REQUEST, "Новий пароль не співпадає"),
  BAD_CREDENTIALS(FORBIDDEN, "Логін та / або пароль неправильний"),
  EMPLOYEE_NOT_FOUND(NOT_FOUND, "Співробітника не знайдено"),
  EMAIL_ALREADY_IN_USE(BAD_REQUEST, "Електронна пошта вже використовується"),
  POSITION_NOT_FOUND(NOT_FOUND, "Позицію не знайдено"),
  POSITION_NAME_IN_USE(BAD_REQUEST, "Назва посади вже використовується"),
  DEPARTMENT_NOT_FOUND(NOT_FOUND, "Відділ не знайдено"),
  DEPARTMENT_NAME_IN_USE(BAD_REQUEST, "Назва відділу вже використовується"),
  INVALID_EMPLOYEE_ID(BAD_REQUEST, "Employee ID is invalid"),
  INVALID_POSITION_ID(BAD_REQUEST, "Position ID is invalid"),
  INVALID_DEPARTMENT_ID(BAD_REQUEST, "Department ID is invalid"),
  INVALID_PASSWORD(UNAUTHORIZED, "Неправильний пароль"),
  INVALID_JWT(UNAUTHORIZED, "Invalid JWT"),
  ;

  private final String description;
  private final HttpStatus httpStatus;

  ErrorCodes(HttpStatus httpStatus, String description) {
    this.description = description;
    this.httpStatus = httpStatus;
  }
}
