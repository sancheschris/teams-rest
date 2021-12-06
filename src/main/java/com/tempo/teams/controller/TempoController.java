package com.tempo.teams.controller;


import com.tempo.teams.service.impl.TempoServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(tags = "Teams Rest")
@RestController
@RequestMapping("/v1")
public class TempoController {

    @Autowired
    private TempoServiceImpl tempoServiceImpl;

    @ApiOperation(value = "Look up a role for a membership, where membership is defined by a user id and a \n" +
            "team id")
    @GetMapping("/role/{teamId}/{userId}")
    public ResponseEntity<Object> getRoleByUserAndTeamId(@PathVariable("teamId") String teamId, @PathVariable("userId") String userId) {
        return tempoServiceImpl.getRoleByUserIdAndTeamId(teamId, userId);
    }

    @ApiOperation(value = "Create a new role")
    @PostMapping("/id/{role}")
    public ResponseEntity<Object> createRole(
                                             @PathVariable("role") String role,
                                             String id,
                                             @ApiParam(value = "TeamId", example = "0165537a-d71d-4b01-88f3-14f01c2615ad")
                                             String teamId,
                                             @ApiParam(value = "UserId", example = "0165537a-d71d-4b01-88f3-14f01c2615ad")
                                             String userId) {
        return tempoServiceImpl.createNewRole(id, role,teamId,userId);
    }

    @ApiOperation(value = "Look up memberships for a role")
    @GetMapping("/role/{enumRoles}")
    public ResponseEntity<Object> getMembershipByRole(@PathVariable("enumRoles") String roles) {
        return tempoServiceImpl.getMembershipsByRole(roles);
    }

    @ApiOperation(value = "Assign a role to a member")
    @PutMapping("/role/{roles}")
    public ResponseEntity<Object> assignRoleMember(String teamId, String userId, @PathVariable("roles")String roles) {
        return tempoServiceImpl.assignRoleMember(roles, teamId, userId);
    }
}
