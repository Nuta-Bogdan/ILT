package com.ibm.den.services;

import com.ibm.den.dto.AttendanceDto;
import com.ibm.den.dto.TaskDto;
import com.ibm.den.entities.Attendance;
import com.ibm.den.entities.Grade;
import com.ibm.den.entities.Student;
import com.ibm.den.entities.Task;
import com.ibm.den.repository.AttendanceRepository;
import com.ibm.den.repository.StudentRepository;
import com.ibm.den.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AttendanceService {
    @Autowired
    private final StudentRepository studentRepository;
    @Autowired
    private final AttendanceRepository attendanceRepository;
    @Autowired
    private final TaskRepository taskRepository;

    public AttendanceService(AttendanceRepository attendanceRepository, StudentRepository studentRepository, TaskRepository taskRepository) {
        this.attendanceRepository = attendanceRepository;
        this.studentRepository = studentRepository;
        this.taskRepository = taskRepository;
    }

    public ArrayList<AttendanceDto> getAttendance(String studentEmail) {
        Student student = studentRepository.findByEmail(studentEmail);                          //find the student
        ArrayList<AttendanceDto> attendanceDtos = new ArrayList<>();
        ArrayList<Attendance> attendances = attendanceRepository.findByStudent(student);        //find the attendance of the student
        for (Attendance attendance : attendances) {                                             //convert the attendance to attendanceDto
            attendanceDtos.add(new AttendanceDto(attendance));
        }
        return attendanceDtos;
    }

    public ArrayList<ArrayList<AttendanceDto>> getAttendanceList(String studentEmail) {
        Student student = studentRepository.findByEmail(studentEmail);                          //find the student
        ArrayList<ArrayList<AttendanceDto>> attendanceDtos = new ArrayList<>();
        ArrayList<Student> students = studentRepository.findByTeam(student.getTeam());          //find the students in the same team
        for (Student student1 : students) {                                                     //find the attendance of the students
            attendanceDtos.add(getAttendance(student1.getEmail()));
        }
        return attendanceDtos;
    }

    public AttendanceDto createAttendance(String studentEmail, String taskName) {
        Student student = studentRepository.findByEmail(studentEmail);                          //find the student
        Task task = taskRepository.findByName(taskName);                                        //find the task
        Attendance attendance = new Attendance();                                               //create the attendance
        attendance.setStudent(student);
        attendance.setTask(task);
        attendance.setPresent(false);
        attendanceRepository.save(attendance);

        return new AttendanceDto(attendance);
    }

    public void updateAttendance(String studentEmail, String taskName,int status) {
        Student student = studentRepository.findByEmail(studentEmail);                          //find the student
        Task task = taskRepository.findByName(taskName);                                        //find the task
        Attendance attendance = attendanceRepository.findByStudentAndTask(student, task);       //find the attendance
        if (status == 1){
            attendance.setPresent(true);}
        else{
            attendance.setPresent(false);
        }
        attendanceRepository.save(attendance);
    }
}
