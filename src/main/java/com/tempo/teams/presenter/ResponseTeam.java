package com.tempo.teams.presenter;

import com.tempo.teams.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseTeam {

    private String id;
    private String name;
    private String teamLeadId;
    private List<UserDto> teamMemberIds;
}
