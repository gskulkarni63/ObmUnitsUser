package com.shantesh.obmunits.user.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.opencsv.bean.CsvToBeanBuilder;
import com.shantesh.obmunits.user.dto.TeamCsv;
import com.shantesh.obmunits.user.dto.UserCsv;

public class UserCsvImpl implements IUserCsv {

	@Override
	public List<UserCsv> uploadUserCsv(File file) {
		// TODO Auto-generated method stub
		try {
			List<UserCsv> listOfUserCsv= new CsvToBeanBuilder<UserCsv>(new FileReader(file)).withType(UserCsv.class)
					.build().parse();

            // Manually parse the date strings in the parsed data
			return listOfUserCsv;	
		}
		catch(FileNotFoundException ex) {
			throw new RuntimeException(ex);
		}
		catch(IllegalStateException ex) {
			throw new RuntimeException(ex);
		}
	}

}
