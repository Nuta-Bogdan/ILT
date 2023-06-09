package com.ibm.den.repository;
import com.ibm.den.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    public ArrayList<Student> findAll();

    Student findByEmailAndPassword(String email,String password);

    Student findByEmail(String studentEmail);

    ArrayList<Student> findByTeam(Team team);

    ArrayList<Student> findByLeader(boolean b);

    void deleteByEmail(String email);
}
