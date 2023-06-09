package com.ibm.den.dto;

import com.ibm.den.entities.Attendance;

public class AttendanceDto {
    private String email;
    private String task;
    private Boolean present;

    public AttendanceDto() {
    }

    public AttendanceDto(String email, String task, Boolean present) {
        this.email = email;
        this.task = task;
        this.present = present;
    }

    public AttendanceDto(Attendance attendance) {
        this.email = attendance.getStudent().getEmail();
        this.task = attendance.getTask().getName();
        this.present = attendance.getPresent();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getPresent() {
        return present;
    }

    public void setPresent(Boolean present) {
        this.present = present;
    }

    public String getTask() {
        return task;
    }

    public void setActivity(String task) {
        this.task = task;
    }
}
