package com.ibm.den.dto;

import com.ibm.den.entities.Grade;
import com.ibm.den.entities.Mentor;
import com.ibm.den.entities.Student;
import com.ibm.den.entities.Task;

import java.util.Objects;

public class GradeDto
{
    private String email;
    private String mentor;
    private String task;
    private Long grade;
    private String comment;

    public GradeDto() {
    }

    public GradeDto(String email, String mentor, String task, Long grade, String comment) {
        this.email = email;
        this.mentor = mentor;
        this.task = task;
        this.grade = grade;
        this.comment = comment;
    }

    public GradeDto(Grade grade) {
        this.email = grade.getStudent().getEmail();
        this.mentor = grade.getMentor().getName();
        this.task = grade.getTask().getName();
        this.grade = grade.getValue();
        this.comment = grade.getComment();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

     public String getMentor() {
        return mentor;
    }

    public void setMentor(String mentor) {
        this.mentor = mentor;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public Long getGrade() {
        return grade;
    }

    public void setGrade(Long grade) {
        this.grade = grade;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}

