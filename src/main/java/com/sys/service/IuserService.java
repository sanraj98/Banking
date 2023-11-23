package com.sys.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.sys.entity.BankUSer;

public interface IuserService extends UserDetailsService {
	public void saveusers(BankUSer usr);
	public BankUSer findByUserName(String userName);
}
