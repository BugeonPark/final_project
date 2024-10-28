package com.sds.weatherstory.model.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sds.weatherstory.domain.Role;
@Service
public class RoleServiceImpl implements RoleService{
	
	@Autowired
	private RoleDAO roleDAO;
	
	@Override
	public Role selectByName(String role_name) {
		return roleDAO.selectByName(role_name);
	}
}
