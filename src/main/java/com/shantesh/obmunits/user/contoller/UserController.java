package com.shantesh.obmunits.user.contoller;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
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
import org.springframework.web.bind.annotation.RequestParam;

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
	private final String API_URL = "/user";
	private final String API_URL_ID = API_URL + "/{userId}";

	@PostMapping(API_URL)
	public ResponseEntity<String> savePerson(@RequestBody UserDto user) {
		userService.addUser(user);
		return new ResponseEntity<String>("The user is created", HttpStatusCode.valueOf(201));
	}

	@GetMapping(API_URL_ID)
	public ResponseEntity<UserDto> getPersonByPersonId(@PathVariable("userId") String userId) {
		Optional<UserDto> userDto = userService.findUserByPersonId(userId);
		if (userDto.isPresent()) {
			return new ResponseEntity<UserDto>(userDto.get(), HttpStatusCode.valueOf(201));
		}
		return new ResponseEntity<UserDto>(new UserDto(), HttpStatus.BAD_REQUEST);
	}

	@PutMapping(API_URL_ID)
	public ResponseEntity<String> putPersonByPersonId(@PathVariable("userId") String userId,
			@RequestBody UpdateUserDto user) {
		userService.updateUserByPersonId(userId, user);
		return new ResponseEntity<String>("The user is updated", HttpStatusCode.valueOf(201));
	}

	@PatchMapping(API_URL_ID)
	public ResponseEntity<String> patchPersonByPersonId(@PathVariable("userId") String userId,
			@RequestBody UpdateUserDto user) {
		userService.patchUserByPersonId(userId, user);
		return new ResponseEntity<String>("The user is updated", HttpStatusCode.valueOf(201));
	}

	@DeleteMapping(API_URL_ID)
	public ResponseEntity<String> deletePersonByPersonId(@PathVariable("userId") String userId) {
		userService.deleteUserByPersonId(userId);
		return new ResponseEntity<String>("The user is updated", HttpStatusCode.valueOf(201));
	}

	@GetMapping(API_URL)
	public ResponseEntity<Page<UserDto>> findAllPerson(@RequestParam(required = false) String teamName,
			@RequestParam(required = false) String firstName, @RequestParam(required = false) Integer pageNumber,
			@RequestParam(required = false) Integer pageSize) {
//		return ResponseEntity.ok(userService.findAllUsers(teamName, firstName, pageNumber, pageSize));
		return new ResponseEntity<Page<UserDto>>(userService.findAllUsers(teamName, firstName, pageNumber, pageSize),HttpStatus.ACCEPTED);
	}
}
