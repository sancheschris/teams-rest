package com.tempo.teams.repository;

import com.tempo.teams.entity.User;
import com.tempo.teams.entity.UserTeam;
import com.tempo.teams.enums.EnumRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserTeamRepository extends JpaRepository<UserTeam, String> {

    UserTeam findTopByEnumRoles(EnumRoles enumRoles);

//    UserTeam findByTeamIdAndUserId(String teamId, String userId);

//    @Transactional
//    @Query(value = "SELECT ut.enumRoles FROM UserTeam ut where ut.team_id = :teamId and ut.user_id = :userId", nativeQuery = true)
//    List<UserTeam> getRoleByTeamIdAndUserId(@Param("teamId") String teamId, @Param("userId") String userId);


    @Query("SELECT ut FROM UserTeam ut where ut.team.id = :teamId and ut.user.id = :userId")
    List<UserTeam> getRoleByTeamIdAndUserId(@Param("teamId") String teamId, @Param("userId") String userId);
}
