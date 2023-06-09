package com.ibm.den.repository;

import com.ibm.den.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Long> {
    public ArrayList<Grade> findAll();
    public Grade findAllByMentor_idAndStudent_idAndTask_Id(Long mentorId, Long studentId, Long taskId);

    ArrayList<Grade> findByStudent(Student student);
}
