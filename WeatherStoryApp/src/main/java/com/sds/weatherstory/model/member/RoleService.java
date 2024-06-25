package com.sds.weatherstory.model.member;

import com.sds.weatherstory.domain.Role;

public interface RoleService {
	public Role selectByName(String role_name);
}
