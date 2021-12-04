package com.tempo.teams.entity;

import com.google.gson.Gson;
import com.tempo.teams.enums.EnumRoles;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tbl_team")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String teamLeadId;
    @OneToMany(mappedBy = "team")
    private List<User> teamMembers;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EnumRoles roles = EnumRoles.DEVELOPER;

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
