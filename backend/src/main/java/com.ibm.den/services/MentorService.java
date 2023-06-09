package com.ibm.den.services;

import java.util.ArrayList;

import com.ibm.den.entities.Mentor;
import com.ibm.den.repository.MentorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MentorService {
    @Autowired
    private final MentorRepository mentorRepository;

    public MentorService(MentorRepository mentorRepository){
        this.mentorRepository=mentorRepository;
    }

    public ArrayList<Mentor> getAllMentors(){
        return mentorRepository.findAll();
    }

    public Mentor findById(Long mentor_id){
        return mentorRepository.findById(mentor_id).orElse(null);
    }

    public Mentor addMentor(Mentor mentor){
        Mentor currentMentor = mentorRepository.save(mentor);
        return currentMentor;
    }

    public Mentor updateMentor(Long mentorId, Mentor mentor) {
        Mentor currentMentor = mentorRepository.findById(mentorId).orElse(null);
        currentMentor.setName(mentor.getName());
        return mentorRepository.save(currentMentor);
    }

    public void deleteMentor(Long mentorId) {
        mentorRepository.deleteById(mentorId);
    }
}
