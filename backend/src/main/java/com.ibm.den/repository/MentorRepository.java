package com.ibm.den.repository;
import com.ibm.den.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface MentorRepository extends JpaRepository<Mentor, Long> {
    public ArrayList<Mentor> findAll();

    public Mentor findById(long id);

    public Mentor findByNameAndPassword(String email,String password);

    Mentor findByName(String mentor);
}
