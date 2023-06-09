package com.ibm.den.controller;

import com.ibm.den.dto.ActivityDto;
import com.ibm.den.entities.Activity;
import com.ibm.den.services.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/activity")
public class ActivityController {

    @Autowired
    private final ActivityService activityService;

    public ActivityController(ActivityService activityService){
        this.activityService = activityService;
    }
    @GetMapping("/getNames")        //returns list of all activity names
    public ArrayList<String> getNames(){
        return activityService.getNames();
    }

    @GetMapping("/getActivity/{name}")
    public ActivityDto getActivity(@PathVariable String name){
        return activityService.getActivity(name);
    }

    @PostMapping("")                //creates new activity
    public ActivityDto createActivity(@RequestBody ActivityDto activity){
        return activityService.createActivity(activity);
    }

}
