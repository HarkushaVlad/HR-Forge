package com.vhark.hrforgeapi.department.exceptions;

public class DepartmentNotFoundException extends RuntimeException {

  public DepartmentNotFoundException(Long id) {
    super("Department with id " + id + " not found");
  }

  public DepartmentNotFoundException(String name) {
    super("Department with name " + name + " not found");
  }
}
