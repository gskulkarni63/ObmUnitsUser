package com.shantesh.obmunits.csv;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ResourceUtils;

import com.shantesh.obmunits.user.dto.TeamCsv;
import com.shantesh.obmunits.user.dto.UserCsv;
import com.shantesh.obmunits.user.service.TeamCsvImpl;
import com.shantesh.obmunits.user.service.UserCsvImpl;

import static org.assertj.core.api.Assertions.assertThat;


public class CsvTest {

	
	TeamCsvImpl teamCsv=new TeamCsvImpl();
	UserCsvImpl userCsv=new UserCsvImpl();
	
	@Test
	void loadTestCsvData() throws FileNotFoundException {
        File file = ResourceUtils.getFile("classpath:csvfiles/team.csv");
        File fileUser = ResourceUtils.getFile("classpath:csvfiles/User.csv");
        
        List<TeamCsv>teamCsvList= teamCsv.uploadUserCsv(file);
        List<UserCsv>userCsvList= userCsv.uploadUserCsv(fileUser);
        
        System.out.println(teamCsvList);
        System.out.println(userCsvList);
        assertThat(teamCsvList.size()).isGreaterThan(0);
        assertThat(userCsvList.size()).isGreaterThan(0);
        
	}
}
