package com.tempo.teams.model;

import com.google.gson.Gson;

import javax.persistence.*;
import java.util.List;

@Entity
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String name;
    private String teamLeadId;
    @OneToMany(mappedBy = "team")
    private List<User> users;

    public Team() {
    }

    public Team(String id, String name, String teamLeadId) {
        this.id = id;
        this.name = name;
        this.teamLeadId = teamLeadId;
    }

    public Team(String id, String name, String teamLeadId, List<User> users) {
        this.id = id;
        this.name = name;
        this.teamLeadId = teamLeadId;
        this.users = users;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeamLeadId() {
        return teamLeadId;
    }

    public void setTeamLeadId(String teamLeadId) {
        this.teamLeadId = teamLeadId;
    }

    public List<User> getUsers() {
        return users;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
