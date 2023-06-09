package com.ibm.den.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "attendance")
public class Attendance extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @Column
    private Boolean present;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private Student student;
    @OneToOne
    @JoinColumn
    private Task task;

    public Attendance() {
    }

    public Attendance(Long id, Boolean present, Student student, Task task) {
        this.id = id;
        this.present = present;
        this.student = student;
        this.task = task;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getPresent() {
        return present;
    }

    public void setPresent(Boolean present) {
        this.present = present;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    @Override
    public String toString() {
        return "Attendance{" +
                "id=" + id +
                ", present=" + present +
                ", student=" + student +
                ", task=" + task +
                '}';
    }
}
