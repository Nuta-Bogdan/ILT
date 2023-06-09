package com.ibm.den.repository;

import com.ibm.den.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
        public ArrayList<Team> findAll();

    ArrayList<Team> findByConfirmed(boolean b);
}
