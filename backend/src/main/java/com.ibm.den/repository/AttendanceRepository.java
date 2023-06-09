package com.ibm.den.repository;

import com.ibm.den.entities.Attendance;
import com.ibm.den.entities.Student;
import com.ibm.den.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    ArrayList<Attendance> findByStudent(Student student);

    Attendance findByStudentAndTask(Student student, Task task);
}
