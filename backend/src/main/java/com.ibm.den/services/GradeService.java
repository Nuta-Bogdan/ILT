package com.ibm.den.services;

import com.ibm.den.dto.GradeDto;
import com.ibm.den.entities.*;
import com.ibm.den.repository.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public class GradeService {

    private final GradeRepository gradeRepository;
    private final StudentRepository studentRepository;
    private final MentorRepository mentorRepository;
    private final TaskRepository taskRepository;
    private final AttendanceRepository attendanceRepository;
    public GradeService(AttendanceRepository attendanceRepository,GradeRepository gradeRepository, StudentRepository studentRepository, MentorRepository mentorRepository, TaskRepository taskRepository)
    {
        this.gradeRepository=gradeRepository;
        this.studentRepository=studentRepository;
        this.mentorRepository=mentorRepository;
        this.taskRepository=taskRepository;
        this.attendanceRepository=attendanceRepository;

    }


    public ArrayList<Grade> getAllGrades()
    {
        return gradeRepository.findAll();
    }

    public Grade findByIdMentorAndIdStudentAndIdTask(Long mentorId, Long studentId, Long taskId)
    {
        return gradeRepository.findAllByMentor_idAndStudent_idAndTask_Id(mentorId, studentId, taskId);
    }

    public GradeDto createGrade(GradeDto gradeDto) {
        Grade grade = new Grade();
        grade.setComment(gradeDto.getComment());
        Task task = taskRepository.findByName(gradeDto.getTask());
        Student student = studentRepository.findByEmail(gradeDto.getEmail());
        Mentor mentor = mentorRepository.findByName(gradeDto.getMentor());
        grade.setTask(task);
        grade.setStudent(student);
        grade.setMentor(mentor);
        grade.setValue(gradeDto.getGrade());
        grade.setComment(gradeDto.getComment());
        gradeRepository.save(grade);
        return new GradeDto(grade);
    }

    public Grade updateGrade(Long mentorId, Long studentId, Long taskId, Grade grade) {
        Grade currentGrade = gradeRepository.findAllByMentor_idAndStudent_idAndTask_Id(mentorId, studentId, taskId);
        currentGrade.setComment(grade.getComment());
        currentGrade.setTask(grade.getTask());
        currentGrade.setMentor(grade.getMentor());
        currentGrade.setValue(grade.getValue());
        currentGrade.setStudent(grade.getStudent());
        return gradeRepository.save(currentGrade);
    }

    public void deleteGrade(Long id) {
        gradeRepository.deleteById(id);
    }

    public ArrayList<GradeDto> getGrades(String email) {
        Student student = studentRepository.findByEmail(email);
        ArrayList<Grade> grades = gradeRepository.findByStudent(student);
        ArrayList<GradeDto> gradeDtos = new ArrayList<>();
        for (Grade grade : grades) {
            gradeDtos.add(new GradeDto(grade));
        }
        return gradeDtos;
    }

    public ArrayList<ArrayList<GradeDto>> getGradesList(String email) {
        Student student = studentRepository.findByEmail(email);
        ArrayList<Student> students = studentRepository.findByTeam(student.getTeam());
        ArrayList<ArrayList<GradeDto>> gradeDtos = new ArrayList<>();
        for (Student student1 : students) {
            gradeDtos.add(getGrades(student1.getEmail()));
        }
        return gradeDtos;
    }

    public ArrayList<Long> getGradesFrequency(String email) {
        Student leader = studentRepository.findByEmail(email);
        ArrayList<Student> students = studentRepository.findByTeam(leader.getTeam());
        ArrayList<Long> allGrades = new ArrayList<>();
        for (Student student : students) {
            ArrayList<Grade> studentGrade = gradeRepository.findByStudent(student);
            for (Grade grade : studentGrade) {
                allGrades.add(grade.getValue());
            }
        }
        return allGrades;
    }

    public ArrayList<String> getUngraded(String email) {
        Student student = studentRepository.findByEmail(email);
        ArrayList<Attendance> attendances = attendanceRepository.findByStudent(student);
        ArrayList<Grade> grades = gradeRepository.findByStudent(student);

        ArrayList<String> ungraded = new ArrayList<>();
        for (Attendance attendance : attendances) {
            boolean isGraded = false;
            for (Grade grade : grades) {
                if (grade.getTask().getName().equals(attendance.getTask().getName())) {
                    isGraded = true;
                }
            }
            if (!isGraded) {
                ungraded.add(attendance.getTask().getName());
            }
        }
        return ungraded;
    }
}
