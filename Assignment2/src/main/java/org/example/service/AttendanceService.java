package org.example.service;

import org.example.model.Attendance;
import org.example.repository.AttendanceRepository;
import org.example.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Service
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final EmployeeRepository employeeRepository;

    public AttendanceService(AttendanceRepository attendanceRepository, EmployeeRepository employeeRepository) {
        this.attendanceRepository = attendanceRepository;
        this.employeeRepository = employeeRepository;
    }

    public Attendance checkIn(String employeeId) {
        if (!employeeRepository.existsById(employeeId)) {
            throw new RuntimeException("Employee not found");
        }
        LocalDate today = LocalDate.now();
        if (attendanceRepository.existsByEmployeeIdAndDate(employeeId, today)) {
            throw new RuntimeException("Duplicate check-in for today");
        }

        Attendance attendance = new Attendance(
                UUID.randomUUID().toString(),
                employeeId,
                today,
                LocalTime.now(),
                null,
                "PRESENT"
        );
        return attendanceRepository.save(attendance);
    }

    public Attendance checkOut(String attendanceId) {
        Attendance attendance = attendanceRepository.findById(attendanceId)
                .orElseThrow(() -> new RuntimeException("Attendance record not found"));

        if (attendance.getCheckOut() != null) {
            throw new RuntimeException("Already checked out");
        }

        attendance.setCheckOut(LocalTime.now());
        return attendanceRepository.save(attendance);
    }

    public List<Attendance> getAttendanceByEmployee(String employeeId) {
        return attendanceRepository.findByEmployeeId(employeeId);
    }

    public List<Attendance> filterAttendance(String employeeId, LocalDate date) {
        return attendanceRepository.findByEmployeeIdAndDate(employeeId, date);
    }
}