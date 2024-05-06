package com.vhark.hrforgeapi.employee.exceptions;

public class InvalidEmployeeIdException extends RuntimeException {

  public InvalidEmployeeIdException(String invalidId) {
    super(
        "Invalid employee ID: "
            + invalidId
            + ", please insert a valid employee ID or an employee email");
  }
}
