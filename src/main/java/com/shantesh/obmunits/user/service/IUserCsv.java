package com.shantesh.obmunits.user.service;

import java.io.File;
import java.util.List;

import com.shantesh.obmunits.user.dto.UserCsv;

public interface IUserCsv {
	List<UserCsv> uploadUserCsv(File file);
}
