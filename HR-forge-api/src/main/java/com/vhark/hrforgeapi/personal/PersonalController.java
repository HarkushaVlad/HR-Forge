package com.vhark.hrforgeapi.personal;

import com.vhark.hrforgeapi.employee.EmployeeResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("personal")
@RequiredArgsConstructor
@Tag(name = "Personal")
public class PersonalController {

  private final PersonalService personalService;

  @GetMapping()
  public ResponseEntity<EmployeeResponse> find(Authentication authentication) {
    return ResponseEntity.ok(personalService.find(authentication));
  }

  @PutMapping()
  public ResponseEntity<?> update(
      @RequestBody @Valid PersonalRequest request, Authentication authentication) {
    personalService.update(authentication, request);
    return ResponseEntity.noContent().build();
  }

  @PutMapping("/password")
  public ResponseEntity<?> updatePassword(
          @RequestBody @Valid PasswordRequest request, Authentication authentication) {
    personalService.updatePassword(authentication, request);
    return ResponseEntity.noContent().build();
  }
}
