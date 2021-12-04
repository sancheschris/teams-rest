package com.tempo.teams.dto;

import com.google.gson.Gson;
import com.tempo.teams.model.Team;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private String id;
    private String firstName;
    private String lastName;
    private String displayName;
    private String avatarUrl;
    private String Location;
    private Team team;

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
