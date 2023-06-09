package com.ibm.den.controller;

import com.ibm.den.dto.AttendanceDto;
import com.ibm.den.services.AttendanceService;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {

    private final AttendanceService attendanceService;

    public AttendanceController(AttendanceService attendanceService){
        this.attendanceService = attendanceService;
    }

    @GetMapping("/{studentEmail}")
    //returns list of all attendance records for a student searched by email
    public ArrayList<AttendanceDto> getAttendance(@PathVariable String studentEmail){
        return attendanceService.getAttendance(studentEmail);
    }

    @GetMapping("/list/{studentEmail}")
    //retunrs list of list of attendances. it goes list[student[student attendances]]
    public ArrayList<ArrayList<AttendanceDto>> getAttendanceList(@PathVariable String studentEmail){
        return attendanceService.getAttendanceList(studentEmail);
    }

    @PostMapping("/{studentEmail}/{taskName}")
    //creates an attendance for a specific student. use TaskService.addTask()
    public AttendanceDto createAttendance(@PathVariable String studentEmail, @PathVariable String taskName){
        return attendanceService.createAttendance(studentEmail, taskName);
    }

    @PatchMapping("/{studentEmail}/{taskName}/{status}")
    public HttpStatusCode updateAttendance(@PathVariable String studentEmail, @PathVariable String taskName, @PathVariable int status){
        attendanceService.updateAttendance(studentEmail, taskName, status);
        return HttpStatusCode.valueOf(200);
    }
}
