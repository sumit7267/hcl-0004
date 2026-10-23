package org.example.repository;

import org.example.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository // Yeh Spring ko batata hai ki yeh ek Data Access component hai
public class EmployeeRepository {

    // In-memory storage ke liye Map ka use kar rahe hain (Key: empId, Value: Employee object)
    private final Map<String, Employee> employeeStore = new HashMap<>();

    // 1. Save ya Create karne ke liye
    public Employee save(Employee employee) {
        employeeStore.put(employee.getEmpId(), employee);
        return employee;
    }

    // 2. Saare employees ki list nikalne ke liye (ArrayList mein convert karke)
    public List<Employee> findAll() {
        return new ArrayList<>(employeeStore.values());
    }

    // 3. ID se ek employee ko dhoondne ke liye
    public Optional<Employee> findById(String empId) {
        return Optional.ofNullable(employeeStore.get(empId));
    }

    // 4. Employee exist karta hai ya nahi check karne ke liye
    public boolean existsById(String empId) {
        return employeeStore.containsKey(empId);
    }

    // 5. Employee ko delete karne ke liye
    public void deleteById(String empId) {
        employeeStore.remove(empId);
    }
}