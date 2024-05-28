package com.vhark.hrforgeapi.department;

import com.vhark.hrforgeapi.common.PageResponse;
import com.vhark.hrforgeapi.department.exceptions.DepartmentNameIsAlreadyInUseException;
import com.vhark.hrforgeapi.department.exceptions.DepartmentNotFoundException;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DepartmentService {

  private final DepartmentRepository departmentRepository;
  private final ModelMapper modelMapper;

  public DepartmentResponse findById(long id) {
    Department department =
        departmentRepository.findById(id).orElseThrow(() -> new DepartmentNotFoundException(id));
    return modelMapper.map(department, DepartmentResponse.class);
  }

  public DepartmentResponse findByName(String departmentName) {
    Department department =
        departmentRepository
            .findByName(departmentName)
            .orElseThrow(() -> new DepartmentNotFoundException(departmentName));
    return modelMapper.map(department, DepartmentResponse.class);
  }

  public PageResponse<DepartmentResponse> findAll(
      String name, int page, int size, String sortField, Sort.Direction sortDirection) {
    Sort sortBy = Sort.by(sortDirection, sortField);
    Pageable pageable = PageRequest.of(page, size, sortBy);
    Page<Department> departments =
        departmentRepository.findByNameContainingIgnoreCase(name, pageable);
    List<DepartmentResponse> departmentResponses =
        departments.stream()
            .map(department -> modelMapper.map(department, DepartmentResponse.class))
            .toList();
    return new PageResponse<>(
        departmentResponses,
        departments.getNumber(),
        departments.getSize(),
        departments.getTotalElements(),
        departments.getTotalPages(),
        departments.isFirst(),
        departments.isLast());
  }

  public void create(DepartmentRequest departmentRequest) {
    checkDepartmentNameIsFree(departmentRequest.getName());
    Department department = modelMapper.map(departmentRequest, Department.class);
    department.setDepartmentId(null);
    departmentRepository.save(department);
  }

  public Department update(Long id, DepartmentRequest departmentRequest) {
    checkDepartmentNameIsFree(departmentRequest.getName(), id);
    departmentRepository.findById(id).orElseThrow(() -> new DepartmentNotFoundException(id));
    Department updatedDepartment = modelMapper.map(departmentRequest, Department.class);
    updatedDepartment.setDepartmentId(id);
    return departmentRepository.save(updatedDepartment);
  }

  public Department update(String departmentName, DepartmentRequest departmentRequest) {
    Department department =
        departmentRepository
            .findByName(departmentName)
            .orElseThrow(() -> new DepartmentNotFoundException(departmentName));
    checkDepartmentNameIsFree(departmentRequest.getName(), department.getDepartmentId());
    Department updatedDepartment = modelMapper.map(departmentRequest, Department.class);
    updatedDepartment.setDepartmentId(department.getDepartmentId());
    return departmentRepository.save(updatedDepartment);
  }

  public void deleteById(Long id) {
    checkDepartmentExistsById(id);
    departmentRepository.deleteById(id);
  }

  public void deleteByName(String name) {
    Department department =
        departmentRepository
            .findByName(name)
            .orElseThrow(() -> new DepartmentNotFoundException(name));
    departmentRepository.deleteById(department.getDepartmentId());
  }

  private void checkDepartmentExistsById(Long id) {
    departmentRepository.findById(id).orElseThrow(() -> new DepartmentNotFoundException(id));
  }

  private void checkDepartmentNameIsFree(String departmentName) {
    if (departmentRepository.findByName(departmentName).isPresent()) {
      throw new DepartmentNameIsAlreadyInUseException(departmentName);
    }
  }

  private void checkDepartmentNameIsFree(String departmentName, Long ownerId) {
    Optional<Department> department = departmentRepository.findByName(departmentName);
    if (department.isPresent() && !department.get().getDepartmentId().equals(ownerId)) {
      throw new DepartmentNameIsAlreadyInUseException(departmentName);
    }
  }
}
