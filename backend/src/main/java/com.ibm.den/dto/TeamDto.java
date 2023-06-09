package com.ibm.den.dto;

import com.ibm.den.entities.Student;
import com.ibm.den.entities.Team;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TeamDto {
    private String activityName;
    private List<StudentDto> students = new ArrayList<>();

    public TeamDto() {
    }

    public TeamDto(String activityName, List<StudentDto> students) {
        this.activityName = activityName;
        this.students = students;
    }

    public TeamDto(Team team, ArrayList<Student> students) {
        this.activityName = team.getActivity().getName();
        for (Student student : students) {
            this.students.add(new StudentDto(student));
        }
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public List<StudentDto> getStudents() {
        return students;
    }

    public void setStudents(List<StudentDto> students) {
        this.students = students;
    }
}
