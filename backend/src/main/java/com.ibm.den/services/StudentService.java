package com.ibm.den.services;

import com.ibm.den.dto.StudentDto;
import com.ibm.den.entities.Attendance;
import com.ibm.den.entities.Grade;
import com.ibm.den.entities.Student;
import com.ibm.den.repository.AttendanceRepository;
import com.ibm.den.repository.GradeRepository;
import com.ibm.den.repository.StudentRepository;
import com.ibm.den.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public class StudentService {

    @Autowired
    private final StudentRepository studentRepository;

    @Autowired
    private final GradeRepository gradeRepository;

    @Autowired
    private final AttendanceRepository attendanceRepository;

    public StudentService(StudentRepository studentRepository,GradeRepository gradeRepository,AttendanceRepository attendanceRepository) {
        this.studentRepository = studentRepository;
        this.gradeRepository = gradeRepository;
        this.attendanceRepository = attendanceRepository;
    }
    public void deleteStudent(String email) {
        Student s = studentRepository.findByEmail(email);
        ArrayList<Attendance> attendances = attendanceRepository.findByStudent(s);
        ArrayList<Grade> grades = gradeRepository.findByStudent(s);

        for(Attendance attendance:attendances) {
            attendanceRepository.delete(attendance);
        }

        for(Grade grade:grades) {
            gradeRepository.delete(grade);
        }
        studentRepository.delete(s);
    }

    public Student updateStudent(Long id, Student student) {
        Student currentStudent = studentRepository.findById(id).orElse(null);
        currentStudent.setName(student.getName());
        currentStudent.setEmail(student.getEmail());
        currentStudent.setLeader(student.getLeader());
        currentStudent.setTeam(student.getTeam());
        return studentRepository.save(currentStudent);
    }

    public StudentDto createStudent(StudentDto student,String email,String password) {
        Student leader = studentRepository.findByEmail(email);
        Student currentStudent = new Student();
        currentStudent.setName(student.getName());
        currentStudent.setEmail(student.getEmail());
        currentStudent.setLeader(student.getLeader());
        currentStudent.setTeam(leader.getTeam());
        currentStudent.setPassword(password);
        studentRepository.save(currentStudent);
        return new StudentDto(currentStudent);
    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    public ArrayList<Student> getAllStudents() {
        return (ArrayList<Student>) studentRepository.findAll();
    }

    public ArrayList<StudentDto> getLeaders() {
        ArrayList<Student> students = studentRepository.findByLeader(true);
        ArrayList<StudentDto> studentDtos = new ArrayList<>();
        for (Student student : students) {
            studentDtos.add(new StudentDto(student));
        }
        return studentDtos;
    }
}
