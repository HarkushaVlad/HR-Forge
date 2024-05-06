package com.vhark.hrforgeapi.position;

import com.vhark.hrforgeapi.position.exceptions.PositionNameIsAlreadyInUseException;
import com.vhark.hrforgeapi.position.exceptions.PositionNotFoundException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PositionService {

  private final PositionRepository positionRepository;
  private final ModelMapper modelMapper;

  public PositionResponse findById(long id) {
    Position position =
        positionRepository.findById(id).orElseThrow(() -> new PositionNotFoundException(id));
    return modelMapper.map(position, PositionResponse.class);
  }

  public PositionResponse findByName(String positionName) {
    Position position =
        positionRepository
            .findByName(positionName)
            .orElseThrow(() -> new PositionNotFoundException(positionName));
    return modelMapper.map(position, PositionResponse.class);
  }

  public void create(PositionRequest positionRequest) {
    checkPositionNameIsFree(positionRequest.getName());
    Position position = modelMapper.map(positionRequest, Position.class);
    position.setPositionId(null);
    positionRepository.save(position);
  }

  public Position update(Long id, PositionRequest positionRequest) {
    checkPositionNameIsFree(positionRequest.getName(), id);
    Position position =
        positionRepository.findById(id).orElseThrow(() -> new PositionNotFoundException(id));
    Position updatedPosition = modelMapper.map(positionRequest, Position.class);
    updatedPosition.setPositionId(id);
    return positionRepository.save(position);
  }

  public Position update(String positionName, PositionRequest positionRequest) {
    Position position =
        positionRepository
            .findByName(positionName)
            .orElseThrow(() -> new PositionNotFoundException(positionName));
    checkPositionNameIsFree(positionRequest.getName(), position.getPositionId());
    Position updatedPosition = modelMapper.map(positionRequest, Position.class);
    updatedPosition.setPositionId(position.getPositionId());
    return positionRepository.save(position);
  }

  public void deleteById(Long id) {
    checkPositionExistsById(id);
    positionRepository.deleteById(id);
  }

  public void deleteByName(String name) {
    Position position =
        positionRepository.findByName(name).orElseThrow(() -> new PositionNotFoundException(name));
    positionRepository.deleteById(position.getPositionId());
  }

  private void checkPositionExistsById(Long id) {
    positionRepository.findById(id).orElseThrow(() -> new PositionNotFoundException(id));
  }

  private void checkPositionNameIsFree(String positionName) {
    if (positionRepository.findByName(positionName).isPresent()) {
      throw new PositionNameIsAlreadyInUseException(positionName);
    }
  }

  private void checkPositionNameIsFree(String positionName, Long ownerId) {
    Optional<Position> position = positionRepository.findByName(positionName);
    if (position.isPresent() && !position.get().getPositionId().equals(ownerId)) {
      throw new PositionNameIsAlreadyInUseException(positionName);
    }
  }
}
