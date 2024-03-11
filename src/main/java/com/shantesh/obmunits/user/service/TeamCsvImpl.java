package com.shantesh.obmunits.user.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import org.springframework.stereotype.Service;

import com.opencsv.bean.CsvToBeanBuilder;
import com.shantesh.obmunits.user.dto.TeamCsv;

@Service
public class TeamCsvImpl implements ITeamCsv {

	@Override
	public List<TeamCsv> uploadUserCsv(File file){
		// TODO Auto-generated method stub
		try {
			List<TeamCsv> listOfTeamCsv= new CsvToBeanBuilder<TeamCsv>(new FileReader(file)).withType(TeamCsv.class)
					.build().parse();
			return listOfTeamCsv;	
		}
		catch(FileNotFoundException ex) {
			throw new RuntimeException(ex);
		}
		catch(IllegalStateException ex) {
			throw new RuntimeException(ex);
		}
	}
	
}
