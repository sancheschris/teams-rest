package com.tempo.teams.service;

import org.springframework.http.ResponseEntity;

public interface TempoService {

    ResponseEntity<Object> getRoleByUserIdAndTeamId(String teamId, String userId);
    ResponseEntity<Object> createNewRole(String id, String role, String teamId, String userId);
    ResponseEntity<Object> getMembershipsByRole(String roles);
    ResponseEntity<Object> assignRoleMember(String role, String teamId, String userId);
}
