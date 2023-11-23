package com.sys.Dao;

import org.springframework.stereotype.Component;

import com.sys.entity.BankUSer;

public interface IuserDao {

	public void saveUsers(BankUSer usr);
	
	public BankUSer findByUserName(String theUserName);
}
