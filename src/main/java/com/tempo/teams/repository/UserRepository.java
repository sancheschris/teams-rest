package com.tempo.teams.repository;

import com.tempo.teams.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
