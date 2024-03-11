package com.shantesh.obmunits.user.contoller;

import java.util.Optional;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shantesh.obmunits.user.dto.ResponseDto;
import com.shantesh.obmunits.user.dto.TeamDto;
import com.shantesh.obmunits.user.dto.UpdateUserDto;
import com.shantesh.obmunits.user.dto.UserDto;
import com.shantesh.obmunits.user.entity.User;
import com.shantesh.obmunits.user.service.UserServiceImpl;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {
	private final UserServiceImpl userService;

	@PostMapping("/save/user")
	public ResponseEntity<ResponseDto> saveTeam(@RequestBody UserDto user) {
		userService.addUser(user);
		return new ResponseEntity<ResponseDto>(ResponseDto.builder().message("The user is created").build(),
				HttpStatusCode.valueOf(201));
	}
	
	@GetMapping("/get/user/{userId}")
	public ResponseEntity<?> getTeamByTeamId(@PathVariable("userId") String userId) {
		Optional<UserDto> userDto= userService.findUserByPersonId(userId);
		if(userDto.isPresent()) {
			return new ResponseEntity<UserDto>(userDto.get(), HttpStatusCode.valueOf(201));
		}
		return new ResponseEntity(ResponseDto.builder().message("UserId does not exist").build(), HttpStatusCode.valueOf(201));
	}
	
	@PutMapping("/put/user/{userId}")
	public ResponseEntity<?> putTeamByTeamId(@PathVariable("userId") String userId,@RequestBody UpdateUserDto user) {
		 userService.updateUserByPersonId(userId,user);
		return new ResponseEntity(ResponseDto.builder().message("The user is updated").build(), HttpStatusCode.valueOf(201));
	}
	
	@PatchMapping("/patch/user/{userId}")
	public ResponseEntity<?> patchTeamByTeamId(@PathVariable("userId") String userId,@RequestBody UpdateUserDto user) {
		userService.patchUserByPersonId(userId,user);
		return new ResponseEntity(ResponseDto.builder().message("The user is updated").build(), HttpStatusCode.valueOf(201));
	}

	@DeleteMapping("/delete/user/{userId}")
	public ResponseEntity<?> deleteTeamByTeamId(@PathVariable("userId") String userId) {
		userService.deleteUserByPersonId(userId);
		return new ResponseEntity(ResponseDto.builder().message("The user is updated").build(), HttpStatusCode.valueOf(201));
	}
}
