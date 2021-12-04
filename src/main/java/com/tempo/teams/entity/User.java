package com.tempo.teams.entity;

import com.google.gson.Gson;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "tbl_user")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    private String displayName;
    @Column(nullable = false)
    private String avatarUrl;
    @Column(nullable = false)
    private String location;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
