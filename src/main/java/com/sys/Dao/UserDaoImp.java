package com.sys.Dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sys.entity.BankUSer;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
@Repository
public class UserDaoImp implements IuserDao {
	
	@Autowired
	EntityManager ent;
	@Transactional
	public void saveUsers(BankUSer usr) {
		
		ent.persist(usr);
	}
	
	@Override
	public BankUSer findByUserName(String theUserName) {

		// retrieve/read from database using username
		TypedQuery<BankUSer> theQuery = ent.createQuery("from BankUSer where userName=:uName", BankUSer.class);
		theQuery.setParameter("uName", theUserName);

		BankUSer theUser = null;
		try {
			theUser = theQuery.getSingleResult();
		} catch (Exception e) {
			theUser = null;
		}

		return theUser;
	}

}
