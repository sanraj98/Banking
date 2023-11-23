package com.sys.Dao;

import com.sys.entity.Role;

public interface IroleDao {
	
	public Role findRoleByName(String theRoleName);

}
