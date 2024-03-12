package com.shantesh.obmunits.user.service;

import java.util.Optional;

import org.springframework.data.domain.Page;

import com.shantesh.obmunits.user.dto.UpdateUserDto;
import com.shantesh.obmunits.user.dto.UserDto;

public interface UserService {
	public void addUser(UserDto user);
	public Optional<UserDto> findUserByPersonId(String personId);	
	public void updateUserByPersonId(String personId,UpdateUserDto user);
	public void patchUserByPersonId(String personId,UpdateUserDto user);
	public void deleteUserByPersonId(String personId);
	public Page<UserDto> findAllUsers(String teamName,String firstName,Integer pageNumber,Integer pageSize);
}
