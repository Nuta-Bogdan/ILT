package com.ibm.den.repository;
import com.ibm.den.entities.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {
    public Activity findByName(String name);
    public ArrayList<Activity> findAll();
}
