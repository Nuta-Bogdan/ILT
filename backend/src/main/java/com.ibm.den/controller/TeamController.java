package com.ibm.den.controller;

import com.ibm.den.dto.TeamDto;
import com.ibm.den.entities.Team;
import org.springframework.beans.factory.annotation.Autowired;
import com.ibm.den.services.TeamService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/team")
public class TeamController {

    @Autowired
    TeamService teamService;
    @GetMapping("/{email}")
    //returns the team of the leader with the given email
    public TeamDto getTeam(@PathVariable String email)
    {
        return teamService.getTeam(email);
    }

    @GetMapping("/unconfirmedTeams")
    //returns all the unconfirmed teams
    public ArrayList<TeamDto> getUnconfirmedTeams()
    {
        return teamService.getUnconfirmedTeams();
    }

    @PutMapping("/registerTeam/{leaderEmail}")
    //registers the team of the leader with the given email
    public void registerTeam( @PathVariable String leaderEmail)
    {
        teamService.registerTeam(leaderEmail);
    }

    @DeleteMapping("/unregisterTeam{leaderEmail}")
    //deletes the team of the leader with the given email
    public void unregisterTeam( @PathVariable String leaderEmail)
    {
        teamService.unregisterTeam(leaderEmail);
    }

}
