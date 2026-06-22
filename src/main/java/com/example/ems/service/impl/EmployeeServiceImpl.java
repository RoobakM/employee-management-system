package com.example.ems.service.impl;

import com.example.ems.dto.EmployeeDTO;
import com.example.ems.entity.Employee;
import com.example.ems.exception.DuplicateResourceException;
import com.example.ems.exception.ResourceNotFoundException;
import com.example.ems.repository.EmployeeRepository;
import com.example.ems.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public EmployeeDTO addEmployee(EmployeeDTO employeeDTO) {
        if (employeeRepository.existsByEmail(employeeDTO.getEmail())) {
            throw new DuplicateResourceException(
                    "An employee with email '" + employeeDTO.getEmail() + "' already exists");
        }

        Employee employee = mapToEntity(employeeDTO);
        Employee saved = employeeRepository.save(employee);
        return mapToDTO(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EmployeeDTO> getAllEmployees() {
        return employeeRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public EmployeeDTO getEmployeeById(Long id) {
        Employee employee = findEmployeeOrThrow(id);
        return mapToDTO(employee);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EmployeeDTO> searchEmployeesByName(String name) {
        return employeeRepository
                .findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(name, name)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDTO updateEmployee(Long id, EmployeeDTO employeeDTO) {
        Employee existing = findEmployeeOrThrow(id);

        // If the email is being changed, make sure the new one isn't already taken
        if (!existing.getEmail().equalsIgnoreCase(employeeDTO.getEmail())
                && employeeRepository.existsByEmail(employeeDTO.getEmail())) {
            throw new DuplicateResourceException(
                    "An employee with email '" + employeeDTO.getEmail() + "' already exists");
        }

        existing.setFirstName(employeeDTO.getFirstName());
        existing.setLastName(employeeDTO.getLastName());
        existing.setEmail(employeeDTO.getEmail());
        existing.setPhoneNumber(employeeDTO.getPhoneNumber());
        existing.setDepartment(employeeDTO.getDepartment());
        existing.setDesignation(employeeDTO.getDesignation());
        existing.setSalary(employeeDTO.getSalary());
        existing.setDateOfJoining(employeeDTO.getDateOfJoining());

        Employee updated = employeeRepository.save(existing);
        return mapToDTO(updated);
    }

    @Override
    public void deleteEmployee(Long id) {
        Employee employee = findEmployeeOrThrow(id);
        employeeRepository.delete(employee);
    }

    // ---- helpers ----

    private Employee findEmployeeOrThrow(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));
    }

    private EmployeeDTO mapToDTO(Employee employee) {
        return EmployeeDTO.builder()
                .id(employee.getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .email(employee.getEmail())
                .phoneNumber(employee.getPhoneNumber())
                .department(employee.getDepartment())
                .designation(employee.getDesignation())
                .salary(employee.getSalary())
                .dateOfJoining(employee.getDateOfJoining())
                .build();
    }

    private Employee mapToEntity(EmployeeDTO dto) {
        return Employee.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .phoneNumber(dto.getPhoneNumber())
                .department(dto.getDepartment())
                .designation(dto.getDesignation())
                .salary(dto.getSalary())
                .dateOfJoining(dto.getDateOfJoining())
                .build();
    }
}
