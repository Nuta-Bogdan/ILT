package com.ibm.den.services;

import com.ibm.den.dto.TaskDto;
import com.ibm.den.entities.Activity;
import com.ibm.den.entities.Task;
import com.ibm.den.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ibm.den.repository.TaskRepository;
import java.util.ArrayList;

@Service
public class TaskService {
    @Autowired
    private final TaskRepository taskRepository;
    @Autowired
    private final ActivityRepository activityRepository;
    @Autowired
    private final AttendanceService attendanceService;

    public TaskService(TaskRepository taskRepository, ActivityRepository activityRepository, AttendanceService attendanceService) {
        this.taskRepository = taskRepository;
        this.activityRepository = activityRepository;
        this.attendanceService = attendanceService;
    }

    public ArrayList<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task getTaskById(Long id) {
        return taskRepository.findById(id).orElse(null);
    }

    public TaskDto createTask(TaskDto task, String ActivityName) {
        Task currentTask = new Task();
        currentTask.setName(task.getName());
        currentTask.setDescription(task.getDescription());
        Activity activity = activityRepository.findByName(ActivityName);
        activity.getTasks().add(currentTask);
        activityRepository.save(activity);
        taskRepository.save(currentTask);
        return new TaskDto(currentTask);
    }

    public void assignTask(ArrayList<String> studentsEmail, String taskName) {
        for (String student : studentsEmail) {
            attendanceService.createAttendance(student, taskName);
        }
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    public ArrayList<TaskDto> getTasks(String activityName) {
        ArrayList<Task> tasks = taskRepository.findByActivity(activityRepository.findByName(activityName));
        ArrayList<TaskDto> taskDtos = new ArrayList<>();
        for (Task task : tasks) {
            taskDtos.add(new TaskDto(task));
        }
        return taskDtos;
    }
}
