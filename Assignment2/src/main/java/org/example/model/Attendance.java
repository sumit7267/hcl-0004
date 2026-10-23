package org.example.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Attendance {
    private String attendanceId;
    private String employeeId;
    private LocalDate date;
    private LocalTime checkIn;
    private LocalTime checkOut;
    private String status; // e.g., "PRESENT"

    public Attendance() {}

    public Attendance(String attendanceId, String employeeId, LocalDate date, LocalTime checkIn, LocalTime checkOut, String status) {
        this.attendanceId = attendanceId;
        this.employeeId = employeeId;
        this.date = date;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.status = status;
    }

    public String getAttendanceId() { return attendanceId; }
    public void setAttendanceId(String attendanceId) { this.attendanceId = attendanceId; }
    public String getEmployeeId() { return employeeId; }
    public void setEmployeeId(String employeeId) { this.employeeId = employeeId; }
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
    public LocalTime getCheckIn() { return checkIn; }
    public void setCheckIn(LocalTime checkIn) { this.checkIn = checkIn; }
    public LocalTime getCheckOut() { return checkOut; }
    public void setCheckOut(LocalTime checkOut) { this.checkOut = checkOut; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
