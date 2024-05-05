package com.vhark.hrforgeapi.auth;

import com.vhark.hrforgeapi.department.Department;
import com.vhark.hrforgeapi.department.DepartmentService;
import com.vhark.hrforgeapi.employee.Employee;
import com.vhark.hrforgeapi.employee.EmployeeService;
import com.vhark.hrforgeapi.position.Position;
import com.vhark.hrforgeapi.position.PositionService;
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

  private final AuthenticationManager authenticationManager;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final EmployeeService employeeService;
  private final PositionService positionService;
  private final DepartmentService departmentService;

  public void register(RegistrationRequest request) {
    Position employeePosition = positionService.findByPositionName(request.getPositionName());

    Department employeeDepartment =
        departmentService.findByDepartmentName(request.getDepartmentName());

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

    employeeService.create(employee);
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
