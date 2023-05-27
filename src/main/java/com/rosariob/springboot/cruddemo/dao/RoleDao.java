package com.rosariob.springboot.cruddemo.dao;

import com.rosariob.springboot.cruddemo.entity.Role;

public interface RoleDao {

	public Role findRoleByName(String theRoleName);
	
}
