package com.vhark.hrforgeapi.department;

import com.vhark.hrforgeapi.department.exceptions.DepartmentNotFoundException;
import com.vhark.hrforgeapi.employee.exceptions.EmailIsAlreadyInUseException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DepartmentService {

  private final DepartmentRepository departmentRepository;

  public Department findById(long id) {
    return departmentRepository.findById(id).orElseThrow(() -> new DepartmentNotFoundException(id));
  }

  public Department findByDepartmentName(String departmentName) {
    return departmentRepository
        .findByName(departmentName)
        .orElseThrow(() -> new DepartmentNotFoundException(departmentName));
  }

  public Department update(Long id, Department department) {
    checkDepartmentExistsById(id);
    return departmentRepository.save(department);
  }

  public void deleteById(Long id) {
    checkDepartmentExistsById(id);
    departmentRepository.deleteById(id);
  }

  private void checkDepartmentExistsById(Long id) {
    departmentRepository.findById(id).orElseThrow(() -> new DepartmentNotFoundException(id));
  }

  public void checkDepartmentNameIsFree(String departmentName) {
    if (departmentRepository.findByName(departmentName).isPresent()) {
      throw new EmailIsAlreadyInUseException(departmentName);
    }
  }
}
