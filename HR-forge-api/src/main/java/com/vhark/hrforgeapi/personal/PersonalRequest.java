package com.vhark.hrforgeapi.personal;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.Date;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PersonalRequest {

  @NotEmpty(message = "Firstname is mandatory")
  @NotBlank(message = "Firstname is mandatory")
  private String firstName;

  @NotEmpty(message = "Lastname is mandatory")
  @NotBlank(message = "Lastname is mandatory")
  private String lastName;

  @NotNull(message = "Birth date is mandatory")
  private Date birthDate;

  @Email(message = "Email is not formatted")
  @NotEmpty(message = "Email is mandatory")
  @NotBlank(message = "Email is mandatory")
  private String email;
}
