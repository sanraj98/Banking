package com.sys.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sys.Dao.IuserDao;
import com.sys.entity.BankUSer;
import com.sys.entity.Role;

import jakarta.mail.MessagingException;

@Service
public class UserServiceImp implements IuserService {
	@Autowired
	private IuserDao userdoa;
    @Autowired
    private MailService mailService;
	@Transactional
	public void saveusers(BankUSer usr) {
		
		userdoa.saveUsers(usr);
		 try {
	            mailService.sendEmail(usr.getEmail(), "Welcome to SRB Netbanking",
	            		" Hello Mr/Mrs"+usr.getUserName()+".you sucessfully registered our"
	            				+ "SR Bank. Have a great day! and  Thank you for join us!"
	            				+"Note: Don't reply for this mail");
	        } catch (MessagingException e) {
	            e.printStackTrace();
	        }
	}
	
	@Override
	public BankUSer findByUserName(String userName) {
		// check the database if the user already exists
		return userdoa.findByUserName(userName);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		BankUSer usr = userdoa.findByUserName(username);

		if (usr == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}

		Collection<SimpleGrantedAuthority> authorities = mapRolesToAuthorities(usr.getRoles());

		return new org.springframework.security.core.userdetails.User(usr.getUserName(), usr.getPasword(), authorities
				);
	}
	
	private Collection<SimpleGrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();

		for (Role tempRole : roles) {
			SimpleGrantedAuthority tempAuthority = new SimpleGrantedAuthority(tempRole.getName());
			authorities.add(tempAuthority);
		}

		return authorities;
	}
}
