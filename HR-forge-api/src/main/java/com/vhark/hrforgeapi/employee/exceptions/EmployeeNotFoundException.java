package com.vhark.hrforgeapi.employee.exceptions;

public class EmployeeNotFoundException extends RuntimeException {

  public EmployeeNotFoundException(Long id) {
    super("Employee with id " + id + " not found");
  }

  public EmployeeNotFoundException(String email) {
    super("Employee with email " + email + " not found");
  }
}
