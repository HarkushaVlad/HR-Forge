package com.vhark.hrforgeapi.department;

import com.vhark.hrforgeapi.common.PageResponse;
import com.vhark.hrforgeapi.department.exceptions.InvalidDepartmentIdException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("department")
@RequiredArgsConstructor
@Tag(name = "Department", description = "API for managing department data")
public class DepartmentController {

  private final DepartmentService departmentService;

  @Operation(
      summary = "Find all departments",
      description = "Endpoint for retrieving a paginated list of all departments.",
      parameters = {
        @Parameter(name = "page", description = "Page number", example = "0"),
        @Parameter(name = "size", description = "Page size", example = "10"),
        @Parameter(
            name = "sortField",
            description = "Field to sort by",
            example = "name",
            schema = @Schema(implementation = String.class)),
        @Parameter(
            name = "sortDirection",
            description = "Sort direction (ASC or DESC)",
            example = "ASC",
            schema = @Schema(implementation = String.class))
      },
      operationId = "getAllDepartments")
  @GetMapping()
  public ResponseEntity<PageResponse<DepartmentResponse>> findAll(
      @RequestParam(name = "page", defaultValue = "0") int page,
      @RequestParam(name = "size", defaultValue = "10") int size,
      @RequestParam(name = "sortField", defaultValue = "name") String sortField,
      @RequestParam(name = "sortDirection", defaultValue = "ASC") String sortDirection) {
    Sort.Direction direction =
        sortDirection.equalsIgnoreCase("DESC") ? Sort.Direction.DESC : Sort.Direction.ASC;
    return ResponseEntity.ok(departmentService.findAll(page, size, sortField, direction));
  }

  @Operation(
      summary = "Find department by ID or name",
      description = "Endpoint for retrieving a department by ID or name.",
      parameters = {@Parameter(name = "id-or-name", description = "Department ID or name")},
      operationId = "getDepartment")
  @GetMapping("/{id-or-name}")
  public ResponseEntity<DepartmentResponse> update(@PathVariable("id-or-name") String idOrName) {
    if (idOrName.matches("^[0-9]+$")) {
      try {
        long id = Long.parseLong(idOrName);
        return ResponseEntity.ok(departmentService.findById(id));
      } catch (NumberFormatException e) {
        throw new InvalidDepartmentIdException(idOrName);
      }
    } else {
      return ResponseEntity.ok(departmentService.findByName(idOrName));
    }
  }

  @Operation(
      summary = "Create department",
      description = "Endpoint for creating a new department.",
      requestBody =
          @io.swagger.v3.oas.annotations.parameters.RequestBody(
              description = "Department details",
              required = true,
              content = @Content(schema = @Schema(implementation = DepartmentRequest.class))),
      operationId = "createDepartment")
  @PostMapping()
  public ResponseEntity<?> create(@RequestBody @Valid DepartmentRequest request) {
    departmentService.create(request);
    return ResponseEntity.noContent().build();
  }

  @Operation(
      summary = "Update department",
      description = "Endpoint for updating an existing department by ID or name.",
      parameters = {@Parameter(name = "id-or-name", description = "Department ID or name")},
      requestBody =
          @io.swagger.v3.oas.annotations.parameters.RequestBody(
              description = "Department details",
              required = true,
              content = @Content(schema = @Schema(implementation = DepartmentRequest.class))),
      operationId = "updateDepartment")
  @PutMapping("/{id-or-name}")
  public ResponseEntity<?> update(
      @RequestBody @Valid DepartmentRequest request, @PathVariable("id-or-name") String idOrName) {
    if (idOrName.matches("^[0-9]+$")) {
      try {
        long id = Long.parseLong(idOrName);
        departmentService.update(id, request);
        return ResponseEntity.noContent().build();
      } catch (NumberFormatException e) {
        throw new InvalidDepartmentIdException(idOrName);
      }
    } else {
      departmentService.update(idOrName, request);
      return ResponseEntity.noContent().build();
    }
  }

  @Operation(
      summary = "Delete department",
      description = "Endpoint for deleting a department by ID or name.",
      parameters = {@Parameter(name = "id-or-name", description = "Department ID or name")},
      operationId = "deleteDepartment")
  @DeleteMapping("/{id-or-name}")
  public ResponseEntity<?> delete(@PathVariable("id-or-name") String idOrName) {
    if (idOrName.matches("^[0-9]+$")) {
      try {
        long id = Long.parseLong(idOrName);
        departmentService.deleteById(id);
        return ResponseEntity.noContent().build();
      } catch (NumberFormatException e) {
        throw new InvalidDepartmentIdException(idOrName);
      }
    } else {
      departmentService.deleteByName(idOrName);
      return ResponseEntity.noContent().build();
    }
  }
}
