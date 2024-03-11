package com.shantesh.obmunits.user.service;

import java.io.File;
import java.util.List;

import com.shantesh.obmunits.user.dto.TeamCsv;
import com.shantesh.obmunits.user.dto.UserCsv;

public interface ITeamCsv {
	List<TeamCsv> uploadUserCsv(File file);
}
