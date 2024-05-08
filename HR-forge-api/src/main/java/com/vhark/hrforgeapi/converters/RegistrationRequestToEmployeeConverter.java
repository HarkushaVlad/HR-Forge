package com.vhark.hrforgeapi.converters;

import com.vhark.hrforgeapi.auth.RegistrationRequest;
import com.vhark.hrforgeapi.employee.Employee;
import lombok.RequiredArgsConstructor;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RegistrationRequestToEmployeeConverter
    implements Converter<RegistrationRequest, Employee> {

  private final PasswordEncoder passwordEncoder;

  @Override
  public Employee convert(MappingContext<RegistrationRequest, Employee> mappingContext) {
    RegistrationRequest request = mappingContext.getSource();
    return Employee.builder()
        .firstName(request.getFirstName())
        .lastName(request.getLastName())
        .email(request.getEmail())
        .passwordHash(passwordEncoder.encode(request.getPassword()))
        .salary(request.getSalary())
        .birthDate(request.getBirthDate())
        .hireDate(request.getHireDate())
        .build();
  }
}
