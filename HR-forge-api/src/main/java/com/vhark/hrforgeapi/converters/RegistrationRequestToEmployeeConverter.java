package com.vhark.hrforgeapi.converters;

import com.vhark.hrforgeapi.auth.RegistrationRequest;
import com.vhark.hrforgeapi.department.Department;
import com.vhark.hrforgeapi.department.DepartmentService;
import com.vhark.hrforgeapi.employee.Employee;
import com.vhark.hrforgeapi.position.Position;
import com.vhark.hrforgeapi.position.PositionService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RegistrationRequestToEmployeeConverter
    implements Converter<RegistrationRequest, Employee> {

  private final DepartmentService departmentService;
  private final PositionService positionService;
  private final PasswordEncoder passwordEncoder;

  @Override
  public Employee convert(MappingContext<RegistrationRequest, Employee> mappingContext) {
    RegistrationRequest request = mappingContext.getSource();

    Position employeePosition = positionService.findByPositionName(request.getPositionName());

    Department employeeDepartment =
        departmentService.findByDepartmentName(request.getDepartmentName());

    return Employee.builder()
        .firstName(request.getFirstName())
        .lastName(request.getLastName())
        .email(request.getEmail())
        .passwordHash(passwordEncoder.encode(request.getPassword()))
        .position(employeePosition)
        .department(employeeDepartment)
        .salary(request.getSalary())
        .birthDate(request.getBirthDate())
        .hireDate(request.getHireDate())
        .build();
  }
}
