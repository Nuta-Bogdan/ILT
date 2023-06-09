package com.ibm.den.repository;
import com.ibm.den.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    public ArrayList<Task> findByActivity(Activity activity);

    public ArrayList<Task> findAll();

    Task findByName(String taskName);

}
