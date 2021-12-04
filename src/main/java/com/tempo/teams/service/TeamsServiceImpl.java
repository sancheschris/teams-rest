package com.tempo.teams.service;

import com.tempo.teams.consumers.TeamClient;
import com.tempo.teams.consumers.UserClient;
import com.tempo.teams.presenter.ResponseTeam;
import com.tempo.teams.presenter.ResponseTeams;
import com.tempo.teams.presenter.ResponseUser;
import com.tempo.teams.presenter.ResponseUsers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamsServiceImpl {

    @Autowired
    private UserClient userClient;

    @Autowired
    private TeamClient teamClient;

    public ResponseEntity<Object> retornaUsers() {

        List<ResponseUsers> users = userClient.getUsers();

        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    public ResponseEntity<Object> retornaUser(String id) {

        ResponseUser user = userClient.getUserById(id);

        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    public ResponseEntity<Object> getAllTeams() {

        List<ResponseTeams> teams = teamClient.getTeams();

        return ResponseEntity.status(HttpStatus.OK).body(teams);
    }

    public ResponseEntity<Object> getTeamById(String id) {

        ResponseTeam team = teamClient.getTeamById(id);

        return ResponseEntity.status(HttpStatus.OK).body(team);
    }
}
