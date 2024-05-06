package com.vhark.hrforgeapi.department;

import com.vhark.hrforgeapi.department.exceptions.DepartmentNameIsAlreadyInUseException;
import com.vhark.hrforgeapi.department.exceptions.DepartmentNotFoundException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
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

  public void create(DepartmentRequest departmentRequest) {
    checkDepartmentNameIsFree(departmentRequest.getName());
    Department department = modelMapper.map(departmentRequest, Department.class);
    department.setDepartmentId(null);
    departmentRepository.save(department);
  }

  public Department update(Long id, DepartmentRequest departmentRequest) {
    checkDepartmentNameIsFree(departmentRequest.getName(), id);
    Department department =
        departmentRepository.findById(id).orElseThrow(() -> new DepartmentNotFoundException(id));
    Department updatedDepartment = modelMapper.map(departmentRequest, Department.class);
    updatedDepartment.setDepartmentId(id);
    return departmentRepository.save(department);
  }

  public Department update(String departmentName, DepartmentRequest departmentRequest) {
    Department department =
        departmentRepository
            .findByName(departmentName)
            .orElseThrow(() -> new DepartmentNotFoundException(departmentName));
    checkDepartmentNameIsFree(departmentRequest.getName(), department.getDepartmentId());
    Department updatedDepartment = modelMapper.map(departmentRequest, Department.class);
    updatedDepartment.setDepartmentId(department.getDepartmentId());
    return departmentRepository.save(department);
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
