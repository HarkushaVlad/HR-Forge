package com.vhark.hrforgeapi.converters;

import com.vhark.hrforgeapi.employee.Employee;
import com.vhark.hrforgeapi.employee.EmployeeResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmployeeToEmployeeResponseConverter implements Converter<Employee, EmployeeResponse> {

  @Override
  public EmployeeResponse convert(MappingContext<Employee, EmployeeResponse> mappingContext) {
    Employee employee = mappingContext.getSource();
    return EmployeeResponse.builder()
        .employeeId(employee.getEmployeeId())
        .firstName(employee.getFirstName())
        .lastName(employee.getLastName())
        .email(employee.getEmail())
        .positionName(employee.getPosition().getName())
        .departmentName(employee.getDepartment().getName())
        .salary(employee.getSalary())
        .birthDate(employee.getBirthDate())
        .hireDate(employee.getHireDate())
        .build();
  }
  ;
}
