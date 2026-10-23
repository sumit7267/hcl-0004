package org.example.controller;

import org.example.model.Attendance;
import org.example.service.AttendanceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/attendance")
public class AttendanceController {

    private final AttendanceService attendanceService;

    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    @PostMapping("/check-in")
    public ResponseEntity<?> checkIn(@RequestBody Map<String, String> request) {
        try {
            String employeeId = request.get("employeeId");
            Attendance attendance = attendanceService.checkIn(employeeId);
            return ResponseEntity.status(HttpStatus.CREATED).body(attendance);
        } catch (RuntimeException e) {
            if (e.getMessage().contains("not found")) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
            }
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @PatchMapping("/{id}/check-out")
    public ResponseEntity<?> checkOut(@PathVariable String id) {
        try {
            Attendance attendance = attendanceService.checkOut(id);
            return ResponseEntity.ok(attendance);
        } catch (RuntimeException e) {
            if (e.getMessage().contains("not found")) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
            }
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<Attendance>> getByEmployee(@PathVariable String employeeId) {
        return ResponseEntity.ok(attendanceService.getAttendanceByEmployee(employeeId));
    }

    @GetMapping
    public ResponseEntity<List<Attendance>> filterAttendance(
            @RequestParam(required = false) String employeeId,
            @RequestParam(required = false) LocalDate date) {
        return ResponseEntity.ok(attendanceService.filterAttendance(employeeId, date));
    }
}