package com.ibm.den.controller;

import com.ibm.den.entities.Mentor;
import com.ibm.den.services.MentorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/mentor")
public class MentorControler {

    @Autowired
    private MentorService mentorService;


}
