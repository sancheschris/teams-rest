package com.tempo.teams.repository;

import com.tempo.teams.entity.UserTeam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserTeamRepository extends JpaRepository<UserTeam, String> {

    UserTeam findTopByRoles(String roles);

    @Modifying(flushAutomatically = true)
    @Transactional
    @Query(value = "INSERT INTO user_team" +
            "  (id, roles, team_id, user_id)" +
            " VALUES (:id, :role, :teamId, :userId)",
            nativeQuery = true)
    void insertNewRole(@Param("id") String id,
                       @Param("role") String role,
                       @Param("teamId") String teamId,
                       @Param("userId") String userId);

    @Query("SELECT ut FROM user_team ut where ut.team.id = :teamId and ut.user.id = :userId")
    List<UserTeam> getRoleByTeamIdAndUserId(@Param("teamId") String teamId, @Param("userId") String userId);

    @Modifying
    @Transactional
    @Query(value = "update user_team set enum_roles = :role where team_id =:teamId and user_id =:userId", nativeQuery = true)
    void updateRole(@Param("role") String role, @Param("teamId") String teamId, @Param("userId") String userId);
}
