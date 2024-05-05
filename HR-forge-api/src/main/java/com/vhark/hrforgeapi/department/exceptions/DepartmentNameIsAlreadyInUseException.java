package com.vhark.hrforgeapi.department.exceptions;

public class DepartmentNameIsAlreadyInUseException extends RuntimeException {

  public DepartmentNameIsAlreadyInUseException(String departmentName) {
    super("Department name " + departmentName + " is already in use");
  }
}
