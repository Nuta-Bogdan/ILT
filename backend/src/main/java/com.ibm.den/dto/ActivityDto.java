package com.ibm.den.dto;

import com.ibm.den.entities.Activity;
import com.ibm.den.entities.Student;
import com.ibm.den.entities.Task;

import java.util.ArrayList;
import java.util.Objects;

public class ActivityDto {
private String name;
private ArrayList<TaskDto> tasks = new ArrayList<>();

    public ActivityDto() {
    }

    public ActivityDto(String name, ArrayList<TaskDto> tasks) {
        this.name = name;
        this.tasks = tasks;
    }

    public ActivityDto(Activity activity) {
        this.name = activity.getName();

        if (!Objects.isNull(activity.getTasks())) {
            for (Task task : activity.getTasks()) {
                this.tasks.add(new TaskDto(task));
            }
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<TaskDto> getTasks() {
        return tasks;
    }

    public void setTasks(ArrayList<TaskDto> tasks) {
        this.tasks = tasks;
    }
}

