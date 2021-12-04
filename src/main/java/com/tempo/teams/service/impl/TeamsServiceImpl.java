package com.tempo.teams.service.impl;

import com.tempo.teams.consumers.TeamClient;
import com.tempo.teams.consumers.UserClient;
import com.tempo.teams.exceptions.BadRequestException;
import com.tempo.teams.presenter.ResponseTeam;
import com.tempo.teams.presenter.ResponseTeams;
import com.tempo.teams.presenter.ResponseUser;
import com.tempo.teams.presenter.ResponseUsers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class TeamsServiceImpl {

    @Autowired
    private UserClient userClient;

    @Autowired
    private TeamClient teamClient;

    public List<ResponseUsers> getAllUsers() {

        try {
            return userClient.getUsers();
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new BadRequestException(e.getMessage(), null);
        }
    }

    public ResponseUser getUserById(String id) {

        try {
            return userClient.getUserById(id);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new BadRequestException(e.getMessage(), null);
        }
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
