package com.ibm.den.controller;

import com.ibm.den.dto.GradeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ibm.den.entities.Grade;
import com.ibm.den.services.GradeService;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/grade")
public class GradeController {
    @Autowired
    private GradeService gradeService;

    @GetMapping("/{email}")
    public ArrayList<GradeDto> getGrades(@PathVariable String email) {
        return gradeService.getGrades(email);
    }

    @GetMapping("/list/{email}")
    public ArrayList<ArrayList<GradeDto>> getGradesList(@PathVariable String email) {
        return gradeService.getGradesList(email);
    }
    @GetMapping("/frequency/{email}")
    public ArrayList<Long> getGradesFrequency(@PathVariable String email) {
        return gradeService.getGradesFrequency(email);
    }
    @GetMapping("/ungraded/{email}")
    public ArrayList<String> getUngraded(@PathVariable String email) {
        return gradeService.getUngraded(email);
    }

    @PostMapping("")
    //creates new grade
    public GradeDto createGrade(@RequestBody GradeDto grade) {
        return gradeService.createGrade(grade);
    }


}
