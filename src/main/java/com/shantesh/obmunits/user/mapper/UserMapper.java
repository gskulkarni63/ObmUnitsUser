package com.shantesh.obmunits.user.mapper;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.shantesh.obmunits.user.dto.TeamDto;
import com.shantesh.obmunits.user.dto.UserDto;
import com.shantesh.obmunits.user.entity.Team;
import com.shantesh.obmunits.user.entity.User;

@Service
public class UserMapper {
	public UserDto userToUserDto(User user) {
		return UserDto.builder().personId(user.getPersonId()).firstName(user.getFirstName()).email(user.getEmail())
				.dateOfBirth(user.getDateOfBirth()).team(teamToTeamDto(user.getTeam())).build();
	}

	public TeamDto teamToTeamDto(Team team) {
		return TeamDto.builder().teamId(team.getTeamId()).teamName(team.getTeamName()).build();
	}
	
	public Team teamDtoToTeam(TeamDto teamDto) {
		return Team.builder().teamId(teamDto.getTeamId()).teamName(teamDto.getTeamName()).build();
	}

	public User userDtoToUser(UserDto userDto) {
		return User.builder().personId(userDto.getPersonId()).firstName(userDto.getFirstName()).email(userDto.getEmail())
				.dateOfBirth(userDto.getDateOfBirth()).team(teamDtoToTeam(userDto.getTeam())).build();
	}
}
