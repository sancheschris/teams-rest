package com.tempo.teams.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tbl_team")
@Getter
@Setter
public class Team {

    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String teamLeadId;
    @JsonIgnore
    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<UserTeam> userTeams = new ArrayList<>();

    public Team() {
    }

    public Team(String id, String name, String teamLeadId) {
        this.id = id;
        this.name = name;
        this.teamLeadId = teamLeadId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return Objects.equals(id, team.id) && Objects.equals(name, team.name) && Objects.equals(teamLeadId, team.teamLeadId) && Objects.equals(userTeams, team.userTeams);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, teamLeadId, userTeams);
    }
}
