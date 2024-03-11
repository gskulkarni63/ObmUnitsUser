package com.shantesh.obmunits.user.service;

import java.util.Optional;

import com.shantesh.obmunits.user.dto.TeamDto;

public interface TeamService {
	public void addTeam(TeamDto team);
	public Optional<TeamDto> findTeamByTeamId(String teamId);	
	public void updateTeamByTeamId(String teamId,TeamDto team);
//	public void patchTeamByTeamId(String teamId,TeamDto team);
	public void deleteTeamByTeamId(String teamId);
	public void findAllTeams();
}
