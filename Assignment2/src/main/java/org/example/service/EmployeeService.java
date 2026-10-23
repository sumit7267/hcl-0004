package org.example.service;

import org.example.model.Employee;
import org.example.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee createEmployee(Employee employee) {
        if (employeeRepository.existsById(employee.getEmpId())) {
            throw new RuntimeException("Employee with ID " + employee.getEmpId() + " already exists.");
        }
        return employeeRepository.save(employee);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> getEmployeeById(String id) {
        return employeeRepository.findById(id);
    }

    public Employee updateEmployee(String id, Employee updatedData) {
        Employee existing = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with ID: " + id));
        existing.setName(updatedData.getName());
        existing.setDepartment(updatedData.getDepartment());
        existing.setCity(updatedData.getCity());
        existing.setActive(updatedData.isActive());
        return employeeRepository.save(existing);
    }

    public Employee patchEmployee(String id, Employee partialData) {
        Employee existing = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with ID: " + id));
        if (partialData.getName() != null) existing.setName(partialData.getName());
        if (partialData.getDepartment() != null) existing.setDepartment(partialData.getDepartment());
        if (partialData.getCity() != null) existing.setCity(partialData.getCity());
        return employeeRepository.save(existing);
    }

    public boolean deleteEmployee(String id) {
        if (!employeeRepository.existsById(id)) {
            return false;
        }
        employeeRepository.deleteById(id);
        return true;
    }
}