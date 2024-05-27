package com.vhark.hrforgeapi.employee;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

  Optional<Employee> findByEmail(String email);

  Page<Employee> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(
          String firstName, String lastName, Pageable pageable);
}
