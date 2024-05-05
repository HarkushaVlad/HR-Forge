package com.vhark.hrforgeapi.employee;

import com.vhark.hrforgeapi.employee.exceptions.EmailIsAlreadyInUseException;
import com.vhark.hrforgeapi.employee.exceptions.EmployeeNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeService {

  private final EmployeeRepository employeeRepository;

  public Employee findById(long id) {
    return employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
  }

  public Employee findByEmail(String email) {
    return employeeRepository
        .findByEmail(email)
        .orElseThrow(() -> new EmployeeNotFoundException(email));
  }

  public Employee create(Employee employee) {
    checkEmailIsFree(employee.getEmail());
    employee.setEmployeeId(null);
    return employeeRepository.save(employee);
  }

  public Employee update(Long id, Employee employee) {
    checkEmployeeExistsById(id);
    return employeeRepository.save(employee);
  }

  public void deleteById(Long id) {
    checkEmployeeExistsById(id);
    employeeRepository.deleteById(id);
  }

  private void checkEmployeeExistsById(Long id) {
    employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
  }

  public void checkEmailIsFree(String email) {
    if (employeeRepository.findByEmail(email).isPresent()) {
      throw new EmailIsAlreadyInUseException(email);
    }
  }
}
