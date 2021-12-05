package com.tempo.teams.presenter;

import com.tempo.teams.enums.EnumRoles;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseUserTeam {

    private String id;
    private EnumRoles enumRoles;
}
