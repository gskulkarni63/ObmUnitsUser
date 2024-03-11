package com.shantesh.obmunits.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.shantesh.obmunits.user.dto.TeamDto;
import com.shantesh.obmunits.user.entity.Team;


@Repository
public interface TeamJpaRepository extends JpaRepository<Team, String> {
	@Query(value="SELECT t FROM Team t WHERE t.teamId = :teamId")
	public Optional<Team> findByTeamId(String teamId);
	public Optional<Boolean> existsByTeamId(String teamId);  
	public void deleteByTeamId(String teamId);
}
