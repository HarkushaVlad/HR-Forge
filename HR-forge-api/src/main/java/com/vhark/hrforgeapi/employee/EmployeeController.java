package com.vhark.hrforgeapi.employee;

import com.vhark.hrforgeapi.common.PageResponse;
import com.vhark.hrforgeapi.employee.exceptions.InvalidEmployeeIdException;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("employee")
@RequiredArgsConstructor
@Tag(name = "Employee")
public class EmployeeController {

  private final EmployeeService employeeService;

  @GetMapping()
  public ResponseEntity<PageResponse<EmployeeResponse>> findAll(
      @RequestParam(name = "page", defaultValue = "0") int page,
      @RequestParam(name = "size", defaultValue = "10") int size,
      @RequestParam(name = "sortField", defaultValue = "firstName") String sortField,
      @RequestParam(name = "sortDirection", defaultValue = "ASC") String sortDirection) {
    Sort.Direction direction =
        sortDirection.equalsIgnoreCase("DESC") ? Sort.Direction.DESC : Sort.Direction.ASC;
    return ResponseEntity.ok(employeeService.findAll(page, size, sortField, direction));
  }

  @GetMapping("/{id-or-email}")
  public ResponseEntity<EmployeeResponse> find(@PathVariable("id-or-email") String idOrEmail) {
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

  @PutMapping("/password/{id}")
  public ResponseEntity<?> updatePassword(
      @RequestBody @Valid AdminPasswordRequest passwordRequest, @PathVariable long id) {
    employeeService.updatePasswordByAdmin(id, passwordRequest);
    return ResponseEntity.noContent().build();
  }
}
