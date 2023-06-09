package com.ibm.den.dto;

import com.ibm.den.entities.Mentor;

import java.util.Objects;

public class MentorDto {
    private String name;

    public MentorDto() {
    }

    public MentorDto(String name) {
        this.name = name;
    }

    public MentorDto(Mentor mentor) {
        this.name = mentor.getName();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
