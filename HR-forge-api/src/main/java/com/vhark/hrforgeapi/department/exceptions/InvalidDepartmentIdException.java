package com.vhark.hrforgeapi.department.exceptions;

public class InvalidDepartmentIdException extends RuntimeException {

  public InvalidDepartmentIdException(String invalidId) {
    super(
        "Invalid department ID: "
            + invalidId
            + ", please insert a valid department ID or a department name");
  }
}
