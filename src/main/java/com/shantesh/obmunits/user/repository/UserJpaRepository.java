package com.shantesh.obmunits.user.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.shantesh.obmunits.user.dto.UserDto;
import com.shantesh.obmunits.user.entity.Team;
import com.shantesh.obmunits.user.entity.User;

@Repository
public interface UserJpaRepository extends JpaRepository<User, String> {
	
	@Query("SELECT u FROM User u JOIN FETCH u.team WHERE u.personId = :personId")
	public Optional<User> findUserByPersonId(String personId);
	@Query("SELECT u FROM User u JOIN FETCH u.team t WHERE t.teamName = :teamName")
	public Optional<List<User>> findUsersByTeamName(String teamName);
}
