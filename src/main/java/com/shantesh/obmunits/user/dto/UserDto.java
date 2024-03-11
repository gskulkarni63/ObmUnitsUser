package com.shantesh.obmunits.user.dto;

import java.time.LocalDate;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {

	private String personId;

	private String firstName;

	private String email;

	private LocalDate dateOfBirth;
	
	private TeamDto team;

}
