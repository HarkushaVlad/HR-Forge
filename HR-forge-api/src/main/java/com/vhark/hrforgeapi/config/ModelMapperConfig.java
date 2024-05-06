package com.vhark.hrforgeapi.config;

import com.vhark.hrforgeapi.converters.EmployeeRequestToEmployeeConverter;
import com.vhark.hrforgeapi.converters.EmployeeToEmployeeResponseConverter;
import com.vhark.hrforgeapi.converters.RegistrationRequestToEmployeeConverter;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ModelMapperConfig {

  private final EmployeeToEmployeeResponseConverter employeeToEmployeeResponseConverter;
  private final RegistrationRequestToEmployeeConverter registrationRequestToEmployeeConverter;
  private final EmployeeRequestToEmployeeConverter employeeRequestToEmployeeConverter;

  @Bean
  public ModelMapper getModelMapper() {
    ModelMapper modelMapper = new ModelMapper();
    modelMapper.addConverter(registrationRequestToEmployeeConverter);
    modelMapper.addConverter(employeeToEmployeeResponseConverter);
    modelMapper.addConverter(employeeRequestToEmployeeConverter);
    return modelMapper;
  }
}
