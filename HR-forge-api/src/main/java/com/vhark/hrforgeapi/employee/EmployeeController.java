package com.vhark.hrforgeapi.employee;

import com.vhark.hrforgeapi.employee.exceptions.InvalidEmployeeIdException;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("employee")
@RequiredArgsConstructor
@Tag(name = "Employee")
public class EmployeeController {

  private final EmployeeService employeeService;

  @GetMapping("/{id-or-email}")
  public ResponseEntity<EmployeeResponse> update(@PathVariable("id-or-email") String idOrEmail) {
    if (idOrEmail.contains("@")) {
      return ResponseEntity.ok(employeeService.findByEmail(idOrEmail));
    } else {
      try {
        long id = Long.parseLong(idOrEmail);
        return ResponseEntity.ok(employeeService.findById(id));
      } catch (NumberFormatException e) {
        throw new InvalidEmployeeIdException(idOrEmail);
      }
    }
  }

  @PutMapping("/{id-or-email}")
  public ResponseEntity<?> update(
      @RequestBody @Valid EmployeeRequest request, @PathVariable("id-or-email") String idOrEmail) {
    if (idOrEmail.contains("@")) {
      employeeService.update(idOrEmail, request);
      return ResponseEntity.noContent().build();
    } else {
      try {
        long id = Long.parseLong(idOrEmail);
        employeeService.update(id, request);
        return ResponseEntity.noContent().build();
      } catch (NumberFormatException e) {
        throw new InvalidEmployeeIdException(idOrEmail);
      }
    }
  }

  @DeleteMapping("/{id-or-email}")
  public ResponseEntity<?> delete(@PathVariable("id-or-email") String idOrEmail) {
    if (idOrEmail.contains("@")) {
      employeeService.deleteByEmail(idOrEmail);
      return ResponseEntity.noContent().build();
    } else {
      try {
        long id = Long.parseLong(idOrEmail);
        employeeService.deleteById(id);
        return ResponseEntity.noContent().build();
      } catch (NumberFormatException e) {
        throw new InvalidEmployeeIdException(idOrEmail);
      }
    }
  }
}
