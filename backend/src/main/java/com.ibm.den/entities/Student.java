package com.ibm.den.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "student")
public class Student extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private String password;

    @ManyToOne(cascade=CascadeType.DETACH)
    @JsonBackReference
    private Team team;
    @Column(nullable = false)
    private boolean leader;
    @Column
    private String email;


    public Student() {
    }

    public Student(String name, Team team, Boolean leader){
        this.name = name;
        this.team = team;
        this.leader = leader;
    }

    public Student(Long id, String name, String password, boolean leader, String email) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.leader = leader;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Boolean getLeader() {
        return leader;
    }

    public void setLeader(Boolean leader) {
        this.leader = leader;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean isLeader() {
        return leader;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", team=" + team +
                ", role='" + leader + '\'' +
                '}';
    }


}