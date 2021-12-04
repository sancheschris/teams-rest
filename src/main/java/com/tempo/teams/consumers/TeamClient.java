package com.tempo.teams.consumers;

import com.tempo.teams.presenter.ResponseTeam;
import com.tempo.teams.presenter.ResponseTeams;

import java.util.List;

public interface TeamClient {

    List<ResponseTeams> getTeams();

    ResponseTeam getTeamById(String id);
}
