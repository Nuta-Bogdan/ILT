package com.ibm.den.services;

import com.ibm.den.dto.ActivityDto;
import com.ibm.den.entities.Activity;
import com.ibm.den.entities.Task;
import com.ibm.den.repository.ActivityRepository;
import com.ibm.den.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ActivityService {

    @Autowired
    private final ActivityRepository activityRepository;
    @Autowired
    private final TaskRepository taskRepository;

    public ActivityService(ActivityRepository activityRepository, TaskRepository TaskRepository) {
        this.activityRepository = activityRepository;
        this.taskRepository = TaskRepository;
    }

    public ArrayList<String> getNames() {
        ArrayList<String> activityNames = new ArrayList<>();
        ArrayList<Activity> a = activityRepository.findAll();
        for (Activity activity : a) {
            activityNames.add(activity.getName());
        }
        return activityNames;
    }

    public ActivityDto createActivity(ActivityDto activity) {
        Activity currentActivity = new Activity();
        currentActivity.setName(activity.getName());
        activityRepository.save(currentActivity);
        return new ActivityDto(currentActivity);
    }

    public ActivityDto getActivity(String name) {
        Activity activity = activityRepository.findByName(name);
        ArrayList<Task> tasks = taskRepository.findByActivity(activity);
        activity.setTasks(tasks);
        return new ActivityDto(activity);
    }
}
