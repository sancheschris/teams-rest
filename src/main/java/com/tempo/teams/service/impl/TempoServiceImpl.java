package com.tempo.teams.service.impl;

import com.tempo.teams.consumers.TeamClient;
import com.tempo.teams.consumers.UserClient;
import com.tempo.teams.entity.Team;
import com.tempo.teams.entity.User;
import com.tempo.teams.entity.UserTeam;
import com.tempo.teams.enums.EnumRoles;
import com.tempo.teams.exceptions.BadRequestException;
import com.tempo.teams.exceptions.InternalServerErrorException;
import com.tempo.teams.presenter.*;
import com.tempo.teams.repository.TeamRepository;
import com.tempo.teams.repository.UserRepository;
import com.tempo.teams.repository.UserTeamRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class TempoServiceImpl {

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


    public ResponseEntity<Object> getRoleByUserIdAndTeamId(String teamId, String userId) {
        return ResponseEntity.status(HttpStatus.OK).body(userTeamRepository.getRoleByTeamIdAndUserId(teamId, userId));
    }

    public ResponseEntity<Object> createNewRole(EnumRoles enumRoles) {

        UserTeam userTeam = new UserTeam();
        userTeam.setEnumRoles(enumRoles);

        ResponseUserTeam responseUserTeam = new ResponseUserTeam();

        try {
            UserTeam save = userTeamRepository.save(userTeam);
            responseUserTeam.setId(save.getId());
            responseUserTeam.setEnumRoles(save.getEnumRoles());
            return ResponseEntity.status(HttpStatus.CREATED).body(responseUserTeam);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new InternalServerErrorException(e.getMessage(), null);
        }
    }

    public ResponseEntity<Object> getMembershipsByRole(EnumRoles enumRoles) {

        UserTeam byEnumRoles = userTeamRepository.findTopByEnumRoles(enumRoles);
        return ResponseEntity.status(HttpStatus.OK).body(byEnumRoles);
    }

    public ResponseEntity<Object> assignRoleMember(String id, EnumRoles enumRoles) {

        UserTeam byEnumRoles = userTeamRepository.findTopByEnumRoles(enumRoles);
        Optional<User> userById = userRepository.findById(id);

        UserTeam userTeam = new UserTeam();
        userTeam.setUser(new User());
        userTeam.getUser().setId(userById.get().getId());
        userTeam.setEnumRoles(byEnumRoles.getEnumRoles());

        var saved = userTeamRepository.save(userTeam);

        ResponseUserTeam responseUserTeam = new ResponseUserTeam();
        responseUserTeam.setId(saved.getId());
        responseUserTeam.setEnumRoles(saved.getEnumRoles());

        return ResponseEntity.status(HttpStatus.OK).body(responseUserTeam);
    }

    public void saveUserAndTeamFromAPIIntoDataBase() {
        List<ResponseUsers> allUsers = userClient.getUsers();

        List<ResponseTeams> allTeams = teamClient.getTeams();

        List<User> listUsers = new ArrayList<>();

        allUsers.forEach(u -> {
            ResponseUser userById = userClient.getUserById(u.getId());
            User user = new User(userById.getId(), userById.getFirstName(), userById.getLastName(), userById.getDisplayName(), userById.getAvatarUrl(), userById.getLocation());
            listUsers.add(user);
        });

        List<Team> teamList = new ArrayList<>();
        List<UserTeam> userTeams = new ArrayList<>();

        allTeams.forEach(t -> {
            ResponseTeam teamById = teamClient.getTeamById(t.getId());
            Team team = new Team(teamById.getId(), teamById.getName(), teamById.getTeamLeadId());

            teamById.getTeamMemberIds().forEach(members -> {
                UserTeam userTeam = new UserTeam();
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

        userRepository.saveAll(listUsers);
        teamRepository.saveAll(teamList);
        userTeamRepository.saveAll(userTeams);
    }

}
