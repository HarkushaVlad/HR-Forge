package com.vhark.hrforgeapi.position;

import com.vhark.hrforgeapi.common.PageResponse;
import com.vhark.hrforgeapi.position.exceptions.InvalidPositionIdException;
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
@Tag(name = "Position")
public class PositionController {

  private final PositionService positionService;

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

  @PostMapping()
  public ResponseEntity<?> create(@RequestBody @Valid PositionRequest request) {
    positionService.create(request);
    return ResponseEntity.noContent().build();
  }

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

  @DeleteMapping("/{id-or-name}")
  public ResponseEntity<?> delete(
      @RequestBody @Valid PositionRequest request, @PathVariable("id-or-name") String idOrName) {
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
