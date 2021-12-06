package com.tempo.teams.service.impl;

import com.tempo.teams.entity.UserTeam;
import com.tempo.teams.exceptions.BadRequestException;
import com.tempo.teams.exceptions.InternalServerErrorException;
import com.tempo.teams.presenter.ResponseUserTeam;
import com.tempo.teams.repository.TeamRepository;
import com.tempo.teams.repository.UserRepository;
import com.tempo.teams.repository.UserTeamRepository;
import com.tempo.teams.service.TempoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class TempoServiceImpl implements TempoService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private UserTeamRepository userTeamRepository;


    public ResponseEntity<Object> getRoleByUserIdAndTeamId(String teamId, String userId) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userTeamRepository.getRoleByTeamIdAndUserId(teamId, userId));
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new BadRequestException(e.getMessage());
        }

    }

    public ResponseEntity<Object> createNewRole(String id, String role, String teamId, String userId) {

        var responseUserTeam = new ResponseUserTeam();
        try {
            userTeamRepository.insertNewRole(id, role, teamId, userId);
            List<UserTeam> roleByTeamIdAndUserId = userTeamRepository.getRoleByTeamIdAndUserId(teamId, userId);
            roleByTeamIdAndUserId.forEach(r -> {
                responseUserTeam.setId(r.getId());
                responseUserTeam.setRoles(r.getRoles());
            });
            return ResponseEntity.status(HttpStatus.CREATED).body(responseUserTeam);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new InternalServerErrorException(e.getMessage(), null);
        }
    }

    public ResponseEntity<Object> getMembershipsByRole(String roles) {

        try {
            var byEnumRoles = userTeamRepository.findTopByRoles(roles);
            return ResponseEntity.status(HttpStatus.OK).body(byEnumRoles);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new BadRequestException(e.getMessage());
        }

    }

    public ResponseEntity<Object> assignRoleMember(String role, String teamId, String userId) {

        try {
            var idTeam = teamRepository.findById(teamId);
            var idUser = userRepository.findById(userId);

            if (idTeam.isPresent() && idUser.isPresent()) {
                userTeamRepository.updateRole(role, idTeam.get().getId(), idUser.get().getId());
            }
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("UPDATED");
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new BadRequestException(e.getMessage());
        }
    }
}
