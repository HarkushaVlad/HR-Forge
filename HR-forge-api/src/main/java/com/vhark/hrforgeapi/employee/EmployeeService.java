package com.vhark.hrforgeapi.employee;

import com.vhark.hrforgeapi.employee.exceptions.EmailIsAlreadyInUseException;
import com.vhark.hrforgeapi.employee.exceptions.EmployeeNotFoundException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeService {

  private final EmployeeRepository employeeRepository;
  private final ModelMapper modelMapper;

  public EmployeeResponse findById(long id) {
    Employee employee =
        employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
    return modelMapper.map(employee, EmployeeResponse.class);
  }

  public EmployeeResponse findByEmail(String email) {
    Employee employee =
        employeeRepository
            .findByEmail(email)
            .orElseThrow(() -> new EmployeeNotFoundException(email));
    return modelMapper.map(employee, EmployeeResponse.class);
  }

  public void create(Employee employee) {
    checkEmailIsFree(employee.getEmail());
    employee.setEmployeeId(null);
    employeeRepository.save(employee);
  }

  public void update(Long id, EmployeeRequest employeeRequest) {
    checkEmailIsFree(employeeRequest.getEmail(), id);
    Employee employee =
        employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
    Employee updatedEmployee = modelMapper.map(employeeRequest, Employee.class);
    updatedEmployee.setEmployeeId(id);
    updatedEmployee.setPasswordHash(employee.getPasswordHash());
    employeeRepository.save(updatedEmployee);
  }

  public void update(String email, EmployeeRequest employeeRequest) {
    Employee employee =
            employeeRepository.findByEmail(email).orElseThrow(() -> new EmployeeNotFoundException(email));
    checkEmailIsFree(employeeRequest.getEmail(), employee.getEmployeeId());
    Employee updatedEmployee = modelMapper.map(employeeRequest, Employee.class);
    updatedEmployee.setEmployeeId(employee.getEmployeeId());
    updatedEmployee.setPasswordHash(employee.getPasswordHash());
    employeeRepository.save(updatedEmployee);
  }

  public void deleteById(Long id) {
    checkEmployeeExistsById(id);
    employeeRepository.deleteById(id);
  }

  public void deleteByEmail(String email) {
    Employee employee =
        employeeRepository
            .findByEmail(email)
            .orElseThrow(() -> new EmployeeNotFoundException(email));
    employeeRepository.deleteById(employee.getEmployeeId());
  }

  private void checkEmployeeExistsById(Long id) {
    employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
  }

  private void checkEmailIsFree(String email) {
    if (employeeRepository.findByEmail(email).isPresent()) {
      throw new EmailIsAlreadyInUseException(email);
    }
  }

  private void checkEmailIsFree(String email, Long ownerId) {
    Optional<Employee> employee = employeeRepository.findByEmail(email);
    if (employee.isPresent() && !employee.get().getEmployeeId().equals(ownerId)) {
      throw new EmailIsAlreadyInUseException(email);
    }
  }
}
