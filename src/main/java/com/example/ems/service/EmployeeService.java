package com.example.ems.service;

import com.example.ems.dto.EmployeeDTO;

import java.util.List;

/**
 * Defines the business operations available for employees.
 * The controller depends on this interface, not the implementation,
 * which keeps the web layer decoupled from persistence details.
 */
public interface EmployeeService {

    EmployeeDTO addEmployee(EmployeeDTO employeeDTO);

    List<EmployeeDTO> getAllEmployees();

    EmployeeDTO getEmployeeById(Long id);

    List<EmployeeDTO> searchEmployeesByName(String name);

    EmployeeDTO updateEmployee(Long id, EmployeeDTO employeeDTO);

    void deleteEmployee(Long id);
}
