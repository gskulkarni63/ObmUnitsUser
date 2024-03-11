package com.shantesh.obmunits.user.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.shantesh.obmunits.user.dto.TeamDto;
import com.shantesh.obmunits.user.dto.UpdateUserDto;
import com.shantesh.obmunits.user.dto.UserDto;
import com.shantesh.obmunits.user.entity.Team;
import com.shantesh.obmunits.user.entity.User;
import com.shantesh.obmunits.user.exception.ObjectNotFound;
import com.shantesh.obmunits.user.mapper.UserMapper;
import com.shantesh.obmunits.user.repository.UserJpaRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final TeamService teamService;
	private final UserJpaRepository userRepository;
	private final UserMapper userMapper;
	
	@Transactional
	@Override
	public void addUser(UserDto user) {
		// TODO Auto-generated method stub
		teamService.findTeamByTeamId(user.getTeam().getTeamId()).ifPresentOrElse((team)->{
			User convertedUser = userMapper.userDtoToUser(user);
			userRepository.save(convertedUser);
		},()->{
			throw new ObjectNotFound("The passed team id is null");
			});
	}

	@Override
	public Optional<UserDto> findUserByPersonId(String personId) {
		// TODO Auto-generated method stub
		 Optional<User> user = userRepository.findUserByPersonId(personId);
		 return Optional.ofNullable(userMapper.userToUserDto(user.get()));
	}
	@Transactional
	@Override
	public void updateUserByPersonId(String personId, UpdateUserDto user) {
		// TODO Auto-generated method stub
		userRepository.findUserByPersonId(personId).ifPresentOrElse((usr)->{
			usr.setFirstName(user.getFirstName());
			usr.setEmail(user.getEmail());
			usr.setDateOfBirth(user.getDateOfBirth());
			userRepository.save(usr);
		},()->{
			throw new ObjectNotFound("The passed user id is null");
		});
	}

	@Override
	@Transactional
	public void patchUserByPersonId(String personId, UpdateUserDto user) {
		// TODO Auto-generated method stub
		userRepository.findUserByPersonId(personId).ifPresentOrElse((usr)->{
			if(StringUtils.hasText(user.getFirstName())) {
				usr.setFirstName(user.getFirstName());				
			}
			if(StringUtils.hasText(user.getEmail())) {
				usr.setEmail(user.getEmail());				
			}
			if(user.getDateOfBirth()!=null) {
				usr.setDateOfBirth(user.getDateOfBirth());				
			}
			userRepository.save(usr);
		},()->{
			throw new ObjectNotFound("The passed user id is null");
		});
	}

	@Override
	@Transactional
	public void deleteUserByPersonId(String personId) {
		// TODO Auto-generated method stub
		userRepository.findUserByPersonId(personId).ifPresentOrElse((usr)->{
			userRepository.delete(usr);
		},()->{
			throw new ObjectNotFound("The passed user id is null");
		});
	}

	@Override
	public void findAllUsers() {
		// TODO Auto-generated method stub
//		userRepository.findUsersByTeamName()
	}

}
