package com.posidex.service;

import java.util.List;

import com.posidex.entity.UserOps;

public interface UserOpsServiceI {

	void addUserOps(UserOps userOps);

	List<UserOps> getUserOpsByUsername(String username);

}