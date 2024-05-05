package com.vhark.hrforgeapi.position;

import com.vhark.hrforgeapi.employee.exceptions.EmailIsAlreadyInUseException;
import com.vhark.hrforgeapi.position.exceptions.PositionNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PositionService {

  private final PositionRepository positionRepository;

  public Position findById(long id) {
    return positionRepository.findById(id).orElseThrow(() -> new PositionNotFoundException(id));
  }

  public Position findByPositionName(String positionName) {
    return positionRepository
        .findByName(positionName)
        .orElseThrow(() -> new PositionNotFoundException(positionName));
  }

  public Position update(Long id, Position position) {
    checkPositionExistsById(id);
    return positionRepository.save(position);
  }

  public void deleteById(Long id) {
    checkPositionExistsById(id);
    positionRepository.deleteById(id);
  }

  private void checkPositionExistsById(Long id) {
    positionRepository.findById(id).orElseThrow(() -> new PositionNotFoundException(id));
  }

  public void checkPositionNameIsFree(String positionName) {
    if (positionRepository.findByName(positionName).isPresent()) {
      throw new EmailIsAlreadyInUseException(positionName);
    }
  }
}
