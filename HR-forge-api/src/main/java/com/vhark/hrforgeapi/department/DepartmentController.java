package com.vhark.hrforgeapi.department;

import com.vhark.hrforgeapi.common.PageResponse;
import com.vhark.hrforgeapi.department.exceptions.InvalidDepartmentIdException;
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
@Tag(name = "Department")
public class DepartmentController {

  private final DepartmentService departmentService;

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

  @PostMapping()
  public ResponseEntity<?> create(@RequestBody @Valid DepartmentRequest request) {
    departmentService.create(request);
    return ResponseEntity.noContent().build();
  }

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

  @DeleteMapping("/{id-or-name}")
  public ResponseEntity<?> delete(
      @RequestBody @Valid DepartmentRequest request, @PathVariable("id-or-name") String idOrName) {
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
