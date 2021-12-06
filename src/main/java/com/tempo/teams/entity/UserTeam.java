package com.tempo.teams.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "user_team")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserTeam {

    @Id
    @GeneratedValue(generator="uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @ManyToOne
    @JoinColumn(name = "team_id")
    @NotFound(
            action = NotFoundAction.IGNORE)
    private Team team;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @NotFound(
            action = NotFoundAction.IGNORE)
    private User user;

    @Column(name = "roles", nullable = false)
    private String roles = "Developer";

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserTeam userTeam = (UserTeam) o;
        return Objects.equals(id, userTeam.id) && Objects.equals(team, userTeam.team) && Objects.equals(user, userTeam.user) && roles == userTeam.roles;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, team, user, roles);
    }
}
