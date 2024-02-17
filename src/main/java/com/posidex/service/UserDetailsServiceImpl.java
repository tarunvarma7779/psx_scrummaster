package com.posidex.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.posidex.entity.UserDetails;
import com.posidex.repository.UserDetailsRepository;

import jakarta.transaction.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsServiceI {

	@Autowired
	UserDetailsRepository userDetailsRepository;

	@Transactional
	@Override
	public void addUserDetails(UserDetails userDetails) {
		userDetailsRepository.save(userDetails);
	}

	@Override
	public UserDetails getUserDetailsByUsername(String username) {
		return userDetailsRepository.getUserDetailsByUsername(username);
	}

	@Override
	public List<UserDetails> getReportees(String username) {
		UserDetails currentUser = getUserDetailsByUsername(username);
		return userDetailsRepository.getReporteesByEmpID(currentUser.getEmpId());
	}

	@Override
	public Map<String, List<UserDetails>> getTeamMemberDetatils(String username) {
		Map<String, List<UserDetails>> retValue = new LinkedHashMap<>();
		UserDetails currentUser = getUserDetailsByUsername(username);
		List<UserDetails> teamMembers = userDetailsRepository
				.getTeamMembersByDepartment(currentUser.getDepartmentName());
		List<UserDetails> traineeList = new ArrayList<>();
		List<UserDetails> aSEList = new ArrayList<>();
		List<UserDetails> sEList = new ArrayList<>();
		List<UserDetails> sSEList = new ArrayList<>();
		List<UserDetails> pMList = new ArrayList<>();
		teamMembers.forEach(x -> {
			if (x.getDesignation().equals("Project Manager")) {
				pMList.add(x);
			} else if (x.getDesignation().equals("Senior Software Engineer")) {
				sSEList.add(x);
			} else if (x.getDesignation().equals("Software Engineer")) {
				sEList.add(x);
			} else if (x.getDesignation().equals("Associate Software Engineer")) {
				aSEList.add(x);
			} else {
				traineeList.add(x);
			}
		});
		retValue.put("pm", pMList);
		retValue.put("sse", sSEList);
		retValue.put("se", sEList);
		retValue.put("ase", aSEList);
		retValue.put("t", traineeList);
		return retValue;
	}

}
