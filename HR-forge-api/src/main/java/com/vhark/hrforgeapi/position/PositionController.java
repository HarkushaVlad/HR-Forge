package com.vhark.hrforgeapi.position;

import com.vhark.hrforgeapi.common.PageResponse;
import com.vhark.hrforgeapi.position.exceptions.InvalidPositionIdException;
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
@RequestMapping("position")
@RequiredArgsConstructor
@Tag(name = "Position", description = "API for managing positions")
public class PositionController {

  private final PositionService positionService;

  @Operation(
      summary = "Find all positions",
      description = "Endpoint for retrieving a paginated list of all positions.",
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
      })
  @GetMapping()
  public ResponseEntity<PageResponse<PositionResponse>> findAll(
      @RequestParam(name = "page", defaultValue = "0") int page,
      @RequestParam(name = "size", defaultValue = "10") int size,
      @RequestParam(name = "sortField", defaultValue = "name") String sortField,
      @RequestParam(name = "sortDirection", defaultValue = "ASC") String sortDirection) {
    Sort.Direction direction =
        sortDirection.equalsIgnoreCase("DESC") ? Sort.Direction.DESC : Sort.Direction.ASC;
    return ResponseEntity.ok(positionService.findAll(page, size, sortField, direction));
  }

  @Operation(
      summary = "Find position by ID or name",
      description = "Endpoint for retrieving a position by ID or name.",
      parameters = {@Parameter(name = "id-or-name", description = "Position ID or name")})
  @GetMapping("/{id-or-name}")
  public ResponseEntity<PositionResponse> update(@PathVariable("id-or-name") String idOrName) {
    if (idOrName.matches("^[0-9]+$")) {
      try {
        long id = Long.parseLong(idOrName);
        return ResponseEntity.ok(positionService.findById(id));
      } catch (NumberFormatException e) {
        throw new InvalidPositionIdException(idOrName);
      }
    } else {
      return ResponseEntity.ok(positionService.findByName(idOrName));
    }
  }

  @Operation(
      summary = "Create position",
      description = "Endpoint for creating a new position.",
      requestBody =
          @io.swagger.v3.oas.annotations.parameters.RequestBody(
              description = "Position details",
              required = true,
              content = @Content(schema = @Schema(implementation = PositionRequest.class))))
  @PostMapping()
  public ResponseEntity<?> create(@RequestBody @Valid PositionRequest request) {
    positionService.create(request);
    return ResponseEntity.noContent().build();
  }

  @Operation(
      summary = "Update position",
      description = "Endpoint for updating an existing position by ID or name.",
      parameters = {@Parameter(name = "id-or-name", description = "Position ID or name")},
      requestBody =
          @io.swagger.v3.oas.annotations.parameters.RequestBody(
              description = "Position details",
              required = true,
              content = @Content(schema = @Schema(implementation = PositionRequest.class))))
  @PutMapping("/{id-or-name}")
  public ResponseEntity<?> update(
      @RequestBody @Valid PositionRequest request, @PathVariable("id-or-name") String idOrName) {
    if (idOrName.matches("^[0-9]+$")) {
      try {
        long id = Long.parseLong(idOrName);
        positionService.update(id, request);
        return ResponseEntity.noContent().build();
      } catch (NumberFormatException e) {
        throw new InvalidPositionIdException(idOrName);
      }
    } else {
      positionService.update(idOrName, request);
      return ResponseEntity.noContent().build();
    }
  }

  @Operation(
      summary = "Delete position",
      description = "Endpoint for deleting a position by ID or name.",
      parameters = {@Parameter(name = "id-or-name", description = "Position ID or name")})
  @DeleteMapping("/{id-or-name}")
  public ResponseEntity<?> delete(@PathVariable("id-or-name") String idOrName) {
    if (idOrName.matches("^[0-9]+$")) {
      try {
        long id = Long.parseLong(idOrName);
        positionService.deleteById(id);
        return ResponseEntity.noContent().build();
      } catch (NumberFormatException e) {
        throw new InvalidPositionIdException(idOrName);
      }
    } else {
      positionService.deleteByName(idOrName);
      return ResponseEntity.noContent().build();
    }
  }
}
