package com.shantesh.obmunits.user.dto;

import com.opencsv.bean.CsvBindByName;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class TeamCsv {
	@CsvBindByName(column = "teamNo")
	private String teamId;
	@CsvBindByName
	private String teamName;
}
