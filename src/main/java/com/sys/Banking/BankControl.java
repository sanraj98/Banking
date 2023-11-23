package com.sys.Banking;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.sys.Dao.IroleDao;
import com.sys.entity.BankUSer;
import com.sys.service.IsmsService;
import com.sys.service.IuserService;

@RestController
public class BankControl {
	@Autowired
	public IuserService usrserve;
	@Autowired
	IroleDao roledao;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	@Autowired
	private IsmsService smsServe;
	ModelAndView mv=new ModelAndView();
	
	@RequestMapping("/home")
	public ModelAndView home() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		//UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		BankUSer bu=usrserve.findByUserName(authentication.getName());
		String msg="Secutrity alert Hello MR/MRS "+bu.getUserName()+"  some hs logged in your account "
				+ "is that you ignore this message";
		
		smsServe.sendSms(bu.getMobile(), msg);
		mv.setViewName("Welcome");
		return mv;
	}
	@RequestMapping("/login")
	public ModelAndView hello() {
		mv.setViewName("Login");
		return mv;
	}
	
	@RequestMapping("/register")
	public ModelAndView reg() {
		mv.setViewName("Register");
		return mv;
	}
	@RequestMapping("/register/signup")
	public ModelAndView signup(@RequestParam("userName") String usr,@RequestParam("pasword") String psw,@RequestParam("email") String eml,@RequestParam("mobile") String mob,@RequestParam("dart")String dat) throws ParseException {
		BankUSer users=new BankUSer();
		users.setUserName(usr);
		users.setPasword(passwordEncoder.encode(psw));
		users.setEmail(eml);
		users.setMobile(mob);
		Date dst=new SimpleDateFormat("mm/dd/yyyy").parse(dat);
		users.setDob(dst);
		users.setRoles(Arrays.asList(roledao.findRoleByName("ROLE_EMPLOYEE")));
		usrserve.saveusers(users);
		mv.setViewName("/Login");
		return mv;
	}

}
