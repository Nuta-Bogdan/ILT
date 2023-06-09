package com.ibm.den.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "grade")
public class Grade extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long id;
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn()
    private Mentor mentor;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn()
    private Student student;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn()
    private Task task;
    @Column
    private Long value;

    @Column
    private String comment;

    public Grade() {}

    public Grade(long id, Mentor mentor, Student student, Task task, Long value, String comment) {
        this.id = id;
        this.mentor = mentor;
        this.student = student;
        this.task = task;
        this.value = value;
        this.comment = comment;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Mentor getMentor() {
        return mentor;
    }

    public void setMentor(Mentor mentor) {
        this.mentor = mentor;
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

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
