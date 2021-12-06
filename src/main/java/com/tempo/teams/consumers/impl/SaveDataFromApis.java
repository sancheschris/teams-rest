package com.tempo.teams.consumers.impl;

import com.tempo.teams.consumers.TeamClient;
import com.tempo.teams.consumers.UserClient;
import com.tempo.teams.entity.Team;
import com.tempo.teams.entity.User;
import com.tempo.teams.entity.UserTeam;
import com.tempo.teams.exceptions.BadRequestException;
import com.tempo.teams.presenter.ResponseTeam;
import com.tempo.teams.presenter.ResponseTeams;
import com.tempo.teams.presenter.ResponseUser;
import com.tempo.teams.presenter.ResponseUsers;
import com.tempo.teams.repository.TeamRepository;
import com.tempo.teams.repository.UserRepository;
import com.tempo.teams.repository.UserTeamRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class SaveDataFromApis {

    @Autowired
    private UserClient userClient;

    @Autowired
    private TeamClient teamClient;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private UserTeamRepository userTeamRepository;

    public void saveUserAndTeamFromAPIToDatabase() {
        List<ResponseUsers> allUsers = userClient.getUsers();

        List<ResponseTeams> allTeams = teamClient.getTeams();

        List<User> listUsers = new ArrayList<>();

        getUsersById(allUsers, listUsers);

        List<Team> teamList = new ArrayList<>();
        List<UserTeam> userTeams = new ArrayList<>();

        getTeamsByIdAndVerifyMembers(allTeams, listUsers, teamList, userTeams);

        try {
            userRepository.saveAll(listUsers);
            teamRepository.saveAll(teamList);
            userTeamRepository.saveAll(userTeams);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new BadRequestException(e.getMessage());
        }
    }

    private void getTeamsByIdAndVerifyMembers(List<ResponseTeams> allTeams, List<User> listUsers, List<Team> teamList, List<UserTeam> userTeams) {
        allTeams.forEach(t -> {
            ResponseTeam teamById = teamClient.getTeamById(t.getId());
            var team = new Team(teamById.getId(), teamById.getName(), teamById.getTeamLeadId());

            teamById.getTeamMemberIds().forEach(members -> {
                var userTeam = new UserTeam();
                userTeam.setTeam(team);
                listUsers.forEach(x -> {
                    if (x.getId().equals(members.getId())) {
                        userTeam.setUser(x);
                        x.getUserTeams().add(userTeam);
                    }
                    team.getUserTeams().add(userTeam);
                    userTeams.add(userTeam);
                });
            });
            teamList.add(team);
        });
    }

    private void getUsersById(List<ResponseUsers> allUsers, List<User> listUsers) {
        allUsers.forEach(u -> {
            ResponseUser userById = userClient.getUserById(u.getId());
            var user = new User(userById.getId(), userById.getFirstName(), userById.getLastName(), userById.getDisplayName(), userById.getAvatarUrl(), userById.getLocation());
            listUsers.add(user);
        });
    }
}
