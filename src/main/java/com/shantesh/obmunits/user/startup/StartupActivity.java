package com.shantesh.obmunits.user.startup;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import com.shantesh.obmunits.user.dto.TeamCsv;
import com.shantesh.obmunits.user.dto.UserCsv;
import com.shantesh.obmunits.user.entity.Team;
import com.shantesh.obmunits.user.entity.User;
import com.shantesh.obmunits.user.repository.TeamJpaRepository;
import com.shantesh.obmunits.user.repository.UserJpaRepository;
import com.shantesh.obmunits.user.service.TeamCsvImpl;
import com.shantesh.obmunits.user.service.UserCsvImpl;

import jakarta.transaction.Transactional;

@Component
public class StartupActivity implements CommandLineRunner {

	@Autowired
	TeamJpaRepository teamJpa;
	@Autowired
	UserJpaRepository userJpa;

	@Override
	@Transactional
	public void run(String... args) throws Exception  {
		// TODO Auto-generated method stub
		setupData();
	}
	public void setupData() throws Exception{
		if(userJpa.count()==0) {
			TeamCsvImpl teamCsv=new TeamCsvImpl();
			UserCsvImpl userCsv=new UserCsvImpl();
	        File file = ResourceUtils.getFile("classpath:csvfiles/team.csv");
	        File fileUser = ResourceUtils.getFile("classpath:csvfiles/User.csv");
	        
	        List<TeamCsv>teamCsvList= teamCsv.uploadUserCsv(file);
	        System.out.println(teamCsvList);
	        List<UserCsv>userCsvList= userCsv.uploadUserCsv(fileUser);
	        teamCsvList.forEach((team)->{
	        	teamJpa.save(Team.builder().teamId(team.getTeamId()).teamName(team.getTeamName()).build());
	        });
	        userCsvList.forEach((user)->{
	        	userJpa.save(User.builder()
	        			.personId(user.getPersonId())
	        			.firstName(user.getFirstName())
	        			.email(user.getEmail())
	        			.dateOfBirth(user.getDateOfBirth())
	        			.team(Team
	        					.builder()
	        					.teamId(user.getTeamId())
	        					.teamName(user.getTeamName())
	        					.build())
	        			.build());
	        });			
		}
	}
}
