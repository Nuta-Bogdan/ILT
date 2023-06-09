package com.ibm.den.dto;

import com.ibm.den.entities.Student;
import com.ibm.den.entities.Task;
import com.ibm.den.entities.Team;

import java.util.ArrayList;
import java.util.Objects;

public class  StudentDto {
    private String name;
    private String email;
    private Boolean leader;

    public StudentDto() {
    }

    public StudentDto(String name, String email, Boolean leader) {
        this.name = name;
        this.email = email;
        this.leader = leader;
    }

    public StudentDto(Student student) {
        this.name = student.getName();
        this.email = student.getEmail();
        this.leader = student.getLeader();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getLeader() {
        return leader;
    }

    public void setLeader(Boolean leader) {
        this.leader = leader;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StudentDto that)) return false;
        return Objects.equals(getName(), that.getName()) && Objects.equals(getEmail(), that.getEmail()) && Objects.equals(getLeader(), that.getLeader());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getEmail(), getLeader());
    }

    @Override
    public String toString() {
        return "StudentDto{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", leader=" + leader +
                '}';
    }
}
