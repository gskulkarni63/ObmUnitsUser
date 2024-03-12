package com.shantesh.obmunits.user.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

	private static final Integer DEFAULT_PAGENUMBER = 0;
	private static final Integer DEFAULT_PAGE_SIZE = 100;
	private final TeamService teamService;
	private final UserJpaRepository userRepository;
	private final UserMapper userMapper;

	public PageRequest buildPageRequest(Integer pageNumber, Integer pageSize) {
		Integer pageSizeLength;
		Integer pageNumberLength;
		if (pageNumber != null && pageNumber > 0) {
			pageNumberLength = pageNumber - 1;
		} else {
			pageNumberLength = DEFAULT_PAGENUMBER;
		}
		if (pageSize != null && pageSize > 0) {
			if (pageSize > 100) {
				pageSizeLength = 100;
			} else {
				pageSizeLength = pageSize;
			}
		} else {
			pageSizeLength = DEFAULT_PAGE_SIZE;
		}
		Sort sort = Sort.by(Sort.Order.asc("firstName"));
		return PageRequest.of(pageNumberLength, pageSizeLength, sort);
	}

	@Transactional
	@Override
	public void addUser(UserDto user) {
		// TODO Auto-generated method stub
		teamService.findTeamByTeamId(user.getTeam().getTeamId()).ifPresentOrElse((team) -> {
			User convertedUser = userMapper.userDtoToUser(user);
			userRepository.save(convertedUser);
		}, () -> {
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
		userRepository.findUserByPersonId(personId).ifPresentOrElse((usr) -> {
			usr.setFirstName(user.getFirstName());
			usr.setEmail(user.getEmail());
			usr.setDateOfBirth(user.getDateOfBirth());
			userRepository.save(usr);
		}, () -> {
			throw new ObjectNotFound("The passed user id is null");
		});
	}

	@Override
	@Transactional
	public void patchUserByPersonId(String personId, UpdateUserDto user) {
		// TODO Auto-generated method stub
		userRepository.findUserByPersonId(personId).ifPresentOrElse((usr) -> {
			if (StringUtils.hasText(user.getFirstName())) {
				usr.setFirstName(user.getFirstName());
			}
			if (StringUtils.hasText(user.getEmail())) {
				usr.setEmail(user.getEmail());
			}
			if (user.getDateOfBirth() != null) {
				usr.setDateOfBirth(user.getDateOfBirth());
			}
			userRepository.save(usr);
		}, () -> {
			throw new ObjectNotFound("The passed user id is null");
		});
	}

	@Override
	@Transactional
	public void deleteUserByPersonId(String personId) {
		// TODO Auto-generated method stub
		userRepository.findUserByPersonId(personId).ifPresentOrElse((usr) -> {
			userRepository.delete(usr);
		}, () -> {
			throw new ObjectNotFound("The passed user id is null");
		});
	}

	@Override
	public Page<UserDto> findAllUsers(String teamName,String firstName,Integer pageNumber,Integer pageSize) {
		// TODO Auto-generated method stub

		Page<User> filteredUsers;
		PageRequest pageRequest=buildPageRequest(pageNumber,pageSize);
		if(StringUtils.hasText(firstName)) {
			filteredUsers = findAllByFirstName(teamName,pageRequest);
		}
		else if(StringUtils.hasText(teamName)) {
			filteredUsers = findUsersByTeamName(teamName,pageRequest);
		}
		else {
			filteredUsers = userRepository.findAll(pageRequest);
		}
		
		return filteredUsers.map(userMapper::userToUserDto);
		
	}

	public Page<User> findUsersByTeamName(String teamName, Pageable pageable) {
		return userRepository.findAllUsersByTeamName(teamName, pageable);
	}

	public Page<User> findAllByFirstName(String firstName, Pageable pageable) {
		return userRepository.findAllByFirstNameIsLikeIgnoreCase("%" + firstName + "%", pageable);
	}


}
