package com.example.ems.controller;

import com.example.ems.dto.EmployeeDTO;
import com.example.ems.response.ApiResponse;
import com.example.ems.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST API for managing employees.
 * Base path: /api/v1/employees
 */
@RestController
@RequestMapping("/api/v1/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    // POST /api/v1/employees  -> add a new employee
    @PostMapping
    public ResponseEntity<ApiResponse<EmployeeDTO>> addEmployee(
            @Valid @RequestBody EmployeeDTO employeeDTO) {

        EmployeeDTO created = employeeService.addEmployee(employeeDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Employee created successfully", created));
    }

    // GET /api/v1/employees  -> get all employees
    @GetMapping
    public ResponseEntity<ApiResponse<List<EmployeeDTO>>> getAllEmployees() {
        List<EmployeeDTO> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(ApiResponse.success("Employees fetched successfully", employees));
    }

    // GET /api/v1/employees/{id}  -> get a single employee by id
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<EmployeeDTO>> getEmployeeById(@PathVariable Long id) {
        EmployeeDTO employee = employeeService.getEmployeeById(id);
        return ResponseEntity.ok(ApiResponse.success("Employee fetched successfully", employee));
    }

    // GET /api/v1/employees/search?name=John  -> search by first/last name (partial match)
    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<EmployeeDTO>>> searchEmployeesByName(
            @RequestParam String name) {

        List<EmployeeDTO> results = employeeService.searchEmployeesByName(name);
        return ResponseEntity.ok(ApiResponse.success("Search results fetched successfully", results));
    }

    // PUT /api/v1/employees/{id}  -> update an existing employee
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<EmployeeDTO>> updateEmployee(
            @PathVariable Long id,
            @Valid @RequestBody EmployeeDTO employeeDTO) {

        EmployeeDTO updated = employeeService.updateEmployee(id, employeeDTO);
        return ResponseEntity.ok(ApiResponse.success("Employee updated successfully", updated));
    }

    // DELETE /api/v1/employees/{id}  -> delete an employee
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.ok(ApiResponse.success("Employee deleted successfully", null));
    }
}
