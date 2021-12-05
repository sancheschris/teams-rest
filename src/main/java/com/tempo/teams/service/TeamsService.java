package com.tempo.teams.service;

import com.tempo.teams.presenter.ResponseTeam;
import com.tempo.teams.presenter.ResponseTeams;

import java.util.List;

public interface TeamsService {

    List<ResponseTeams> getAllTeams();
    ResponseTeam getTeamById(String id);
}
