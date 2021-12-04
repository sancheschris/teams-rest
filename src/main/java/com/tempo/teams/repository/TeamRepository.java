package com.tempo.teams.repository;

import com.tempo.teams.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, String> {
}
