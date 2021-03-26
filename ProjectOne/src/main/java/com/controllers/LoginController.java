package com.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import beans.User;
import dao.ReimbDao;
import dao.UserDao;
import exceptions.InvalidCredentialsException;
import services.UserServices;

public class LoginController {
	static UserServices uServ = new UserServices(new UserDao(), new ReimbDao());
	public final static Logger logger = Logger.getLogger(LoginController.class);

	public static String login(HttpServletRequest req) {
		if (!req.getMethod().equals("POST")) {
			return "resources/html/index.html";
		}
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		System.out.println("reached the login controller");
		try {
			User u = uServ.login(username, password);
			HttpSession session = req.getSession();
			session.setAttribute("username", u.getUsername());
			session.setAttribute("id", u.getId());
			session.setAttribute("role", u.getUserRole().toString());
			logger.info("successfully logged in" + u.getUsername());
			if(u.getUserRole().toString().equals("FINANCEMANAGER")) {
				return  "managerhome.rs";
			} else {
				return "home.rs";
			}
		} catch (InvalidCredentialsException e) {
			return "incorrectlogin.rs";
		}
	}

}
