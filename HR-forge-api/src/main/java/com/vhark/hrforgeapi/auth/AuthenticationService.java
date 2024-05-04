package com.vhark.hrforgeapi.auth;

import com.vhark.hrforgeapi.department.Department;
import com.vhark.hrforgeapi.department.DepartmentRepository;
import com.vhark.hrforgeapi.employee.Employee;
import com.vhark.hrforgeapi.employee.EmployeeRepository;
import com.vhark.hrforgeapi.position.Position;
import com.vhark.hrforgeapi.position.PositionRepository;
import com.vhark.hrforgeapi.security.JwtService;
import java.util.HashMap;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

  private final PositionRepository positionRepository;
  private final DepartmentRepository departmentRepository;
  private final EmployeeRepository employeeRepository;
  private final AuthenticationManager authenticationManager;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;

  public void register(RegistrationRequest request) {
    Position employeePosition =
        positionRepository
            .findByName(request.getPositionName())
            .orElseThrow(
                () ->
                    new IllegalArgumentException(
                        "Position not found: " + request.getPositionName()));

    Department employeeDepartment =
        departmentRepository
            .findByName(request.getDepartmentName())
            .orElseThrow(
                () ->
                    new IllegalArgumentException(
                        "Department not found: " + request.getDepartmentName()));

    Employee employee =
        Employee.builder()
            .firstName(request.getFirstName())
            .lastName(request.getLastName())
            .email(request.getEmail())
            .passwordHash(passwordEncoder.encode(request.getPassword()))
            .position(employeePosition)
            .department(employeeDepartment)
            .salary(request.getSalary())
            .birthDate(request.getBirthDate())
            .hireDate(request.getHireDate())
            .accountLocked(false)
            .enabled(true)
            .build();

    employeeRepository.save(employee);
  }

  public AuthenticationResponse authenticate(AuthenticationRequest request) {
    var auth =
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
    var claims = new HashMap<String, Object>();
    var employee = ((Employee) auth.getPrincipal());
    claims.put("fullName", employee.getFullName());
    var jwtToken = jwtService.generateToken(claims, employee);
    return AuthenticationResponse.builder().token(jwtToken).build();
  }
}
