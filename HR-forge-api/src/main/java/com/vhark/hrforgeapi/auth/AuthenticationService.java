package com.vhark.hrforgeapi.auth;

import com.vhark.hrforgeapi.employee.Employee;
import com.vhark.hrforgeapi.employee.EmployeeService;
import com.vhark.hrforgeapi.security.JwtService;
import java.util.HashMap;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

  private final AuthenticationManager authenticationManager;
  private final ModelMapper modelMapper;
  private final JwtService jwtService;
  private final EmployeeService employeeService;

  public void register(RegistrationRequest request) {
    Employee employee = modelMapper.map(request, Employee.class);
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
