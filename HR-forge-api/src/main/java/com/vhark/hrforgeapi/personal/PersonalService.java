package com.vhark.hrforgeapi.personal;

import com.vhark.hrforgeapi.employee.Employee;
import com.vhark.hrforgeapi.employee.EmployeeRepository;
import com.vhark.hrforgeapi.employee.EmployeeResponse;
import com.vhark.hrforgeapi.employee.EmployeeService;
import com.vhark.hrforgeapi.personal.exceptions.InvalidPasswordException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonalService {

  private final EmployeeRepository employeeRepository;
  private final EmployeeService employeeService;
  private final ModelMapper modelMapper;
  private final PasswordEncoder passwordEncoder;

  public EmployeeResponse find(Authentication authentication) {
    Employee employee = ((Employee) authentication.getPrincipal());
    return modelMapper.map(employee, EmployeeResponse.class);
  }

  public void update(Authentication authentication, PersonalRequest personalRequest) {
    Employee employee = ((Employee) authentication.getPrincipal());
    employeeService.checkEmailIsFree(personalRequest.getEmail(), employee.getEmployeeId());
    employee.setFirstName(personalRequest.getFirstName());
    employee.setLastName(personalRequest.getLastName());
    employee.setBirthDate(personalRequest.getBirthDate());
    employee.setEmail(personalRequest.getEmail());
    employeeRepository.save(employee);
  }

  public void updatePassword(Authentication authentication, PasswordRequest passwordRequest) {
    Employee employee = ((Employee) authentication.getPrincipal());
    if (!passwordEncoder.matches(passwordRequest.getOldPassword(), employee.getPasswordHash())) {
      throw new InvalidPasswordException();
    }
    String newPasswordHash = passwordEncoder.encode(passwordRequest.getNewPassword());
    employee.setPasswordHash(newPasswordHash);
    employeeRepository.save(employee);
  }
}
