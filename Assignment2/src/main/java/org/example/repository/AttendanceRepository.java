package org.example.repository;

import org.example.model.Attendance;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class AttendanceRepository {
    private final Map<String, Attendance> attendanceStore = new HashMap<>();

    public Attendance save(Attendance attendance) {
        attendanceStore.put(attendance.getAttendanceId(), attendance);
        return attendance;
    }

    public Optional<Attendance> findById(String id) {
        return Optional.ofNullable(attendanceStore.get(id));
    }

    public List<Attendance> findByEmployeeId(String employeeId) {
        return attendanceStore.values().stream()
                .filter(a -> a.getEmployeeId().equals(employeeId))
                .collect(Collectors.toList());
    }

    public List<Attendance> findByEmployeeIdAndDate(String employeeId, LocalDate date) {
        return attendanceStore.values().stream()
                .filter(a -> (employeeId == null || a.getEmployeeId().equals(employeeId)) &&
                        (date == null || a.getDate().equals(date)))
                .collect(Collectors.toList());
    }

    public boolean existsByEmployeeIdAndDate(String employeeId, LocalDate date) {
        return attendanceStore.values().stream()
                .anyMatch(a -> a.getEmployeeId().equals(employeeId) && a.getDate().equals(date));
    }
}