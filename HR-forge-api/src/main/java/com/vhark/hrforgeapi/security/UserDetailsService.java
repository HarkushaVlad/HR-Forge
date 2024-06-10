package com.vhark.hrforgeapi.security;

import com.vhark.hrforgeapi.employee.EmployeeRepository;
import com.vhark.hrforgeapi.security.exceptions.InvalidJWTException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserDetailsService
    implements org.springframework.security.core.userdetails.UserDetailsService {

  private final EmployeeRepository employeeRepository;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String username) throws InvalidJWTException {
    return employeeRepository.findByEmail(username).orElseThrow(InvalidJWTException::new);
  }
}
