package com.vhark.hrforgeapi.position;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionRepository extends JpaRepository<Position, Long> {

  Optional<Position> findByName(String positionName);

  Page<Position> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
