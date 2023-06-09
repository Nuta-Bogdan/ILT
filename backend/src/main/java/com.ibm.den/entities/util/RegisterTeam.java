package com.ibm.den.entities.util;

import com.ibm.den.entities.Activity;

import java.util.ArrayList;

public class RegisterTeam {

    private String activityName;

    private String emailLeader;

    private String nameLeader;

    private String passwordLeader;

    RegisterTeam() {
    }

    public RegisterTeam(String activityName, String emailLeader, String nameLeader, String passwordLeader) {
        this.activityName = activityName;
        this.emailLeader = emailLeader;
        this.nameLeader = nameLeader;
        this.passwordLeader = passwordLeader;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getEmailLeader() {
        return emailLeader;
    }

    public void setEmailLeader(String emailLeader) {
        this.emailLeader = emailLeader;
    }

    public String getNameLeader() {
        return nameLeader;
    }

    public void setNameLeader(String nameLeader) {
        this.nameLeader = nameLeader;
    }

    public String getPasswordLeader() {
        return passwordLeader;
    }

    public void setPasswordLeader(String passwordLeader) {
        this.passwordLeader = passwordLeader;
    }


}
