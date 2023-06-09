package com.ibm.den.services;

import com.ibm.den.dto.StudentDto;
import com.ibm.den.dto.TeamDto;
import com.ibm.den.entities.Student;
import com.ibm.den.entities.Team;
import com.ibm.den.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ibm.den.repository.TeamRepository;
import java.util.ArrayList;

@Service
public class TeamService {

    @Autowired
    private final TeamRepository teamRepository;
    @Autowired
    private final StudentRepository studentRepository;

    public TeamService(TeamRepository teamRepository,StudentRepository studentRepository){
        this.teamRepository = teamRepository;
        this.studentRepository = studentRepository;
    }

    public ArrayList<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    public Team getTeamById(Long id)
    {
        return teamRepository.findById(id).orElse(null);
    }

    public Team addTeam(Team team) {
        return teamRepository.save(team);
    }

    public Team updateTeam(Long id, Team team) {
        Team currentTeam = teamRepository.findById(id).orElse(null);
        teamRepository.save(currentTeam);
        return currentTeam;
    }

    public void deleteTeam(Long id) {
        teamRepository.deleteById(id);
    }


    public TeamDto getTeam(String email) {
        Student student = studentRepository.findByEmail(email);
        Team team = student.getTeam();
        ArrayList<Student> students = studentRepository.findByTeam(team);
        TeamDto teamDto = new TeamDto();
        teamDto.setActivityName(team.getActivity().getName());
        for (Student student1 : students) {
            teamDto.getStudents().add(new StudentDto(student1));
        }
        return teamDto;

    }

    public ArrayList<TeamDto> getUnconfirmedTeams() {
        ArrayList<TeamDto> teamDtos = new ArrayList<>();
        ArrayList<Team> teams = teamRepository.findByConfirmed(false);
        for (Team team : teams) {
            ArrayList <Student> students = studentRepository.findByTeam(team);
            TeamDto teamDto = new TeamDto();
            teamDto.setActivityName(team.getActivity().getName());
            teamDto.setStudents(new ArrayList<>());
            for (Student student : students) {
                teamDto.getStudents().add(new StudentDto(student));
            }
        teamDtos.add(teamDto);
        }
        return teamDtos;
        }

    public void registerTeam(String leaderEmail) {
        Student student = studentRepository.findByEmail(leaderEmail);
        Team team = student.getTeam();
        team.setConfirmed(true);
        teamRepository.save(team);
    }

    public void unregisterTeam(String leaderEmail) {
        Student student = studentRepository.findByEmail(leaderEmail);
        Team team = student.getTeam();
        team.setConfirmed(false);
        teamRepository.delete(team);
    }
}
