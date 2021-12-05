package com.tempo.teams.controller;


import com.tempo.teams.enums.EnumRoles;
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
    @PostMapping("/role/{enumRoles}")
    public ResponseEntity<Object> createRole(@PathVariable("enumRoles") EnumRoles enumRoles) {
        return tempoServiceImpl.createNewRole(enumRoles);
    }

    @ApiOperation(value = "Look up memberships for a role")
    @GetMapping("/role/{enumRoles}")
    public ResponseEntity<Object> getMembershipByRole(@PathVariable("enumRoles") EnumRoles enumRoles) {
        return tempoServiceImpl.getMembershipsByRole(enumRoles);
    }

    @ApiOperation(value = "Assign a role to a member")
    @PutMapping("/role/{id}/")
    public ResponseEntity<Object> assignRoleMember(@ApiParam(value = "Id do user", required = true) @PathVariable("id") String id, EnumRoles enumRoles) {
        return tempoServiceImpl.assignRoleMember(id, enumRoles);
    }
}
