package com.shantesh.obmunits.user.service;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.shantesh.obmunits.user.dto.TeamDto;
import com.shantesh.obmunits.user.entity.Team;
import com.shantesh.obmunits.user.exception.ObjectNotFound;
import com.shantesh.obmunits.user.mapper.UserMapper;
import com.shantesh.obmunits.user.repository.TeamJpaRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {

	private final TeamJpaRepository teamRepo;
	private final UserMapper userMapper;
	
	@Override
	@Transactional
	public void addTeam(TeamDto team) {
		// TODO Auto-generated method stub
		if(team.getTeamId() != null) {
			teamRepo.save(Team.builder().teamId(team.getTeamId()).teamName(team.getTeamName()).build());
		}
		else {
			throw new ObjectNotFound("The passed id is null");
		}
		
	}

	@Override
	public Optional<TeamDto> findTeamByTeamId(String teamId) {
		// TODO Auto-generated method stub
		Team team = teamRepo.findByTeamId(teamId).orElseThrow(()->new ObjectNotFound("the team id is not found"));
		return Optional.ofNullable(userMapper.teamToTeamDto(team));
		
	}

	@Override
	@Transactional
	public void updateTeamByTeamId(String teamId, TeamDto team) {
		// TODO Auto-generated method stub
		teamRepo.findByTeamId(teamId).ifPresentOrElse((t)->{
			teamRepo.save(t);
		},()->{throw new ObjectNotFound("Team id not found");});

	}


	@Override
	@Transactional
	public void deleteTeamByTeamId(String teamId) {
		teamRepo.findByTeamId(teamId).ifPresentOrElse((t)->{
			teamRepo.deleteByTeamId(teamId);
		},()->{throw new ObjectNotFound("Team id not found");});
	}

	@Override
	public void findAllTeams() {
		// TODO Auto-generated method stub

	}

}
