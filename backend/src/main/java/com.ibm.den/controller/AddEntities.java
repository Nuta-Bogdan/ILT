package com.ibm.den.controller;

import com.ibm.den.dto.AttendanceDto;
import com.ibm.den.services.AttendanceService;
import com.ibm.den.services.PopulateDb;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/makeDb")
public class AddEntities {

    private final PopulateDb populateDb;

    public AddEntities(PopulateDb populateDb){
        this.populateDb = populateDb;
    }

    @PostMapping("")
    public void makeDb(){
        populateDb.populateDb();
    }
    @PostMapping("/addGrades")
    public void addGrades(){
        populateDb.addGrades();
    }
}
