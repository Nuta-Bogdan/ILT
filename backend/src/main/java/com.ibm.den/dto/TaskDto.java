package com.ibm.den.dto;

import com.ibm.den.entities.Student;
import com.ibm.den.entities.Task;

import java.util.Objects;

public class TaskDto {
private String name;
private String description;


    public TaskDto() {
    }

    public TaskDto(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public TaskDto(Task task) {
        this.name = task.getName();
        this.description = task.getDescription();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
