package com.shantesh.obmunits.user.dto;

import java.time.LocalDate;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserCsv {
	@CsvBindByName
	private String personId;
	@CsvBindByName
	private String firstName;
	@CsvBindByName
	private String email;
    @CsvDate(value = "yyyy-MM-dd")
	@CsvBindByName
	private LocalDate dateOfBirth;
	
	@CsvBindByName(column = "team_teamNo")
	private String teamId;
	@CsvBindByName(column = "team_teamName")
	private String teamName;
	
	
}
