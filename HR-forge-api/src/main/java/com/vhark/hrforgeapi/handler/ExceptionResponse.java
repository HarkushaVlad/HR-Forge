package com.vhark.hrforgeapi.handler;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.Map;
import java.util.Set;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ExceptionResponse {

  private Integer errorCode;
  private String errorDescription;
  private String error;
  private Set<String> validationErrors;
  private Map<String, String> errors;
}
