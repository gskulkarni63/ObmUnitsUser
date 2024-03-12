package com.shantesh.obmunits.user.contoller;

import java.util.Optional;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shantesh.obmunits.user.dto.ResponseDto;
import com.shantesh.obmunits.user.dto.TeamDto;
import com.shantesh.obmunits.user.service.TeamService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/api")
@RequiredArgsConstructor
public class TeamController {

	private final TeamService teamService;

	
	
	@PostMapping("/save/team")
	public ResponseEntity<ResponseDto> saveTeam(@RequestBody TeamDto team) {
		teamService.addTeam(team);
		return new ResponseEntity<ResponseDto>(ResponseDto.builder().message("The team is created").build(),
				HttpStatusCode.valueOf(201));
	}

	@GetMapping("/get/team/{teamId}")
	public ResponseEntity<?> getTeamByTeamId(@PathVariable("teamId") String teamId) {
		Optional<TeamDto> teamDto = teamService.findTeamByTeamId(teamId);
		if(teamDto.isPresent()) {
			return new ResponseEntity<TeamDto>(teamDto.get(), HttpStatusCode.valueOf(201));
		}
		return new ResponseEntity<ResponseDto>(ResponseDto.builder().message("The teamId is null").build(), HttpStatusCode.valueOf(201));
	}

	@PutMapping("/update/team/{teamId}")
	public ResponseEntity<ResponseDto> updateTeam(@PathVariable("teamId") String teamId, @RequestBody TeamDto team) {
		teamService.updateTeamByTeamId(teamId, team);
		return new ResponseEntity<ResponseDto>(ResponseDto.builder().message("The team is updated").build(),
				HttpStatusCode.valueOf(200));
	}
	@DeleteMapping("/delete/team/{teamId}")
	public ResponseEntity<ResponseDto> deleteTeam(@PathVariable("teamId") String teamId){
		teamService.deleteTeamByTeamId(teamId);
		return new ResponseEntity<ResponseDto>(ResponseDto.builder().message("The team is deleted").build(),
				HttpStatusCode.valueOf(200));
	}

	
}
