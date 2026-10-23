package org.example.model;

import jakarta.validation.constraints.NotBlank;

public class Employee {

    private String empId; // Unique employee ID (Client-supplied)

    @NotBlank(message = "Employee name cannot be blank")
    private String name;

    @NotBlank(message = "Department cannot be blank")
    private String department;

    @NotBlank(message = "City cannot be blank")
    private String city;

    private boolean active = true; // Default active flag true rakha hai

    // 1. Default Constructor (Spring/Jackson deserialization ke liye zaroori hota hai)
    public Employee() {
    }

    // 2. Parameterized Constructor
    public Employee(String empId, String name, String department, String city, boolean active) {
        this.empId = empId;
        this.name = name;
        this.department = department;
        this.city = city;
        this.active = active;
    }

    // 3. Getters and Setters (Data access aur modification ke liye)
    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}