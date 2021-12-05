package com.tempo.teams.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tbl_user")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class User {
    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid2")
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
    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<UserTeam> userTeams = new ArrayList<>();



    public User(String id, String firstName, String lastName, String displayName, String avatarUrl, String location) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.displayName = displayName;
        this.avatarUrl = avatarUrl;
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) && Objects.equals(displayName, user.displayName) && Objects.equals(avatarUrl, user.avatarUrl) && Objects.equals(location, user.location) && Objects.equals(userTeams, user.userTeams);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, displayName, avatarUrl, location, userTeams);
    }
}
