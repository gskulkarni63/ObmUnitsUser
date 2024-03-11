package com.shantesh.obmunits.user.service;

import java.util.Optional;

import com.shantesh.obmunits.user.dto.UpdateUserDto;
import com.shantesh.obmunits.user.dto.UserDto;

public interface UserService {
	public void addUser(UserDto user);
	public Optional<UserDto> findUserByPersonId(String personId);	
	public void updateUserByPersonId(String personId,UpdateUserDto user);
	public void patchUserByPersonId(String personId,UpdateUserDto user);
	public void deleteUserByPersonId(String personId);
	public void findAllUsers();
}
