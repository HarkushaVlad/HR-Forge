package com.vhark.hrforgeapi.employee;

import com.vhark.hrforgeapi.common.PageResponse;
import com.vhark.hrforgeapi.employee.exceptions.InvalidEmployeeIdException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("employee")
@RequiredArgsConstructor
@Tag(name = "Employee", description = "API for managing employee data")
public class EmployeeController {

  private final EmployeeService employeeService;

  @Operation(
      summary = "Find all employees",
      description = "Endpoint for retrieving a paginated list of all employees.",
      parameters = {
        @Parameter(name = "page", description = "Page number", example = "0"),
        @Parameter(name = "size", description = "Page size", example = "10"),
        @Parameter(
            name = "sortField",
            description = "Field to sort by",
            example = "firstName",
            schema = @Schema(implementation = String.class)),
        @Parameter(
            name = "sortDirection",
            description = "Sort direction (ASC or DESC)",
            example = "ASC",
            schema = @Schema(implementation = String.class))
      },
      operationId = "getAllEmployees")
  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<PageResponse<EmployeeResponse>> findAll(
      @RequestParam(name = "page", defaultValue = "0") int page,
      @RequestParam(name = "size", defaultValue = "10") int size,
      @RequestParam(name = "sortField", defaultValue = "firstName") String sortField,
      @RequestParam(name = "sortDirection", defaultValue = "ASC") String sortDirection) {
    Sort.Direction direction =
        sortDirection.equalsIgnoreCase("DESC") ? Sort.Direction.DESC : Sort.Direction.ASC;
    return ResponseEntity.ok(employeeService.findAll(page, size, sortField, direction));
  }

  @Operation(
      summary = "Find employee by ID or email",
      description = "Endpoint for retrieving an employee by ID or email address.",
      parameters = {@Parameter(name = "id-or-email", description = "Employee ID or email")},
      operationId = "getEmployee")
  @GetMapping(value = "/{id-or-email}", produces = MediaType.APPLICATION_JSON_VALUE)
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

  @Operation(
      summary = "Update employee details",
      description = "Endpoint for updating employee details by ID or email.",
      parameters = {@Parameter(name = "id-or-email", description = "Employee ID or email")},
      operationId = "updateEmployee")
  @PutMapping(value = "/{id-or-email}", produces = MediaType.APPLICATION_JSON_VALUE)
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

  @Operation(
      summary = "Delete employee",
      description = "Endpoint for deleting an employee by ID or email.",
      parameters = {@Parameter(name = "id-or-email", description = "Employee ID or email")},
      operationId = "deleteEmployee")
  @DeleteMapping(value = "/{id-or-email}", produces = MediaType.APPLICATION_JSON_VALUE)
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

  @Operation(
      summary = "Update employee password",
      description = "Endpoint for updating an employee's password by ID (admin operation).",
      parameters = {@Parameter(name = "id", description = "Employee ID")},
      operationId = "updateEmployeePassword")
  @PutMapping(value = "/password/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> updatePassword(
      @RequestBody @Valid AdminPasswordRequest passwordRequest, @PathVariable long id) {
    employeeService.updatePasswordByAdmin(id, passwordRequest);
    return ResponseEntity.noContent().build();
  }
}
