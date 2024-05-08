package com.vhark.hrforgeapi.employee;

import com.vhark.hrforgeapi.auth.RegistrationRequest;
import com.vhark.hrforgeapi.common.PageResponse;
import com.vhark.hrforgeapi.department.Department;
import com.vhark.hrforgeapi.department.DepartmentRepository;
import com.vhark.hrforgeapi.department.exceptions.DepartmentNotFoundException;
import com.vhark.hrforgeapi.employee.exceptions.EmailIsAlreadyInUseException;
import com.vhark.hrforgeapi.employee.exceptions.EmployeeNotFoundException;
import com.vhark.hrforgeapi.position.Position;
import com.vhark.hrforgeapi.position.PositionRepository;
import com.vhark.hrforgeapi.position.exceptions.PositionNotFoundException;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeService {

  private final EmployeeRepository employeeRepository;
  private final DepartmentRepository departmentRepository;
  private final PositionRepository positionRepository;
  private final ModelMapper modelMapper;
  private final PasswordEncoder passwordEncoder;

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

  public PageResponse<EmployeeResponse> findAll(
      int page, int size, String sortField, Sort.Direction sortDirection) {
    Sort sortBy = Sort.by(sortDirection, sortField);
    Pageable pageable = PageRequest.of(page, size, sortBy);
    Page<Employee> employees = employeeRepository.findAll(pageable);
    List<EmployeeResponse> employeeResponses =
        employees.stream()
            .map(employee -> modelMapper.map(employee, EmployeeResponse.class))
            .toList();
    return new PageResponse<>(
        employeeResponses,
        employees.getNumber(),
        employees.getSize(),
        employees.getTotalElements(),
        employees.getTotalPages(),
        employees.isFirst(),
        employees.isLast());
  }

  public void create(RegistrationRequest registrationRequest) {
    checkEmailIsFree(registrationRequest.getEmail());
    Position employeePosition =
        positionRepository
            .findByName(registrationRequest.getPositionName())
            .orElseThrow(
                () -> new PositionNotFoundException(registrationRequest.getPositionName()));
    Department employeeDepartment =
        departmentRepository
            .findByName(registrationRequest.getDepartmentName())
            .orElseThrow(
                () -> new DepartmentNotFoundException(registrationRequest.getDepartmentName()));
    Employee employee = modelMapper.map(registrationRequest, Employee.class);
    employee.setEmployeeId(null);
    employee.setPosition(employeePosition);
    employee.setDepartment(employeeDepartment);
    employee.setEnabled(true);
    employee.setAccountLocked(false);
    employeeRepository.save(employee);
  }

  public void update(Long id, EmployeeRequest employeeRequest) {
    checkEmailIsFree(employeeRequest.getEmail(), id);
    Employee employee =
        employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
    Employee updatedEmployee = modelMapper.map(employeeRequest, Employee.class);
    updatedEmployee.setEmployeeId(id);
    updatedEmployee.setPasswordHash(employee.getPasswordHash());
    updatedEmployee.setEnabled(true);
    employeeRepository.save(updatedEmployee);
  }

  public void update(String email, EmployeeRequest employeeRequest) {
    Employee employee =
        employeeRepository
            .findByEmail(email)
            .orElseThrow(() -> new EmployeeNotFoundException(email));
    checkEmailIsFree(employeeRequest.getEmail(), employee.getEmployeeId());
    Employee updatedEmployee = modelMapper.map(employeeRequest, Employee.class);
    updatedEmployee.setEmployeeId(employee.getEmployeeId());
    updatedEmployee.setPasswordHash(employee.getPasswordHash());
    updatedEmployee.setEnabled(true);
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

  public void checkEmailIsFree(String email, Long ownerId) {
    Optional<Employee> employee = employeeRepository.findByEmail(email);
    if (employee.isPresent() && !employee.get().getEmployeeId().equals(ownerId)) {
      throw new EmailIsAlreadyInUseException(email);
    }
  }

  public void updatePasswordByAdmin(long id, AdminPasswordRequest passwordRequest) {
    Employee employee =
        employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
    String newPasswordHash = passwordEncoder.encode(passwordRequest.getNewPassword());
    employee.setPasswordHash(newPasswordHash);
    employeeRepository.save(employee);
  }
}
