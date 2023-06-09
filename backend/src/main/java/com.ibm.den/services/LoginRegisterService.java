package com.ibm.den.services;

import com.ibm.den.entities.Activity;
import com.ibm.den.entities.Mentor;
import com.ibm.den.entities.Student;
import com.ibm.den.entities.Team;
import com.ibm.den.entities.util.LoginRequest;
import com.ibm.den.entities.util.LoginResponse;
import com.ibm.den.entities.util.RegisterTeam;
import com.ibm.den.repository.ActivityRepository;
import com.ibm.den.repository.MentorRepository;
import com.ibm.den.repository.StudentRepository;
import com.ibm.den.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class LoginRegisterService {

    @Autowired
    private final TeamRepository teamRepository;
    @Autowired
    private final StudentRepository studentRepository;
    @Autowired
    private final ActivityRepository activityRepository;
    @Autowired
    private final MentorRepository mentorRepository;

    public LoginRegisterService(TeamRepository teamRepository, StudentRepository studentRepository, ActivityRepository activityRepository, MentorRepository mentorRepository) {
        this.teamRepository = teamRepository;
        this.studentRepository = studentRepository;
        this.activityRepository = activityRepository;
        this.mentorRepository = mentorRepository;
    }

    public ArrayList<Activity> activities() {
        System.out.println("hola");
        return activityRepository.findAll();

    }

    public LoginResponse login(LoginRequest a) {
        LoginResponse response = new LoginResponse();
        Mentor m = mentorRepository.findByNameAndPassword(a.getEmail(), a.getPassword());
        Student s = studentRepository.findByEmailAndPassword(a.getEmail(), a.getPassword());
        if (m != null) {
            response.setUserType(LoginResponse.UserType.MENTOR);
            response.setEmail(m.getName());
            return response;
        } else if (s != null) {
            response.setEmail(s.getEmail());
            if (s.getLeader()) {
                response.setUserType(LoginResponse.UserType.LEADER);
            } else {
                response.setUserType(LoginResponse.UserType.STUDENT);
            }
            return response;
        } else {
            response.setEmail("Not found");
            response.setUserType(LoginResponse.UserType.NOT_FOUND);
            return response;
        }
    }

    public void register(RegisterTeam a) {
        Team t = new Team();

        t.setActivity(activityRepository.findByName(a.getActivityName()));
        t.setConfirmed(false);
        teamRepository.save(t);

        Student s = new Student();
        s.setName(a.getNameLeader());
        s.setEmail(a.getEmailLeader());
        s.setPassword(a.getPasswordLeader());
        s.setLeader(true);
    }
}
