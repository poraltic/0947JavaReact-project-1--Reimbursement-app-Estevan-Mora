package com.controllers;

import javax.servlet.http.HttpServletRequest;

import beans.User;
import dao.ReimbDao;
import dao.UserDao;
import exceptions.UserAlreadyExistsException;
import services.UserServices;

public class RegisterController {
	static UserServices uServ = new UserServices(new UserDao(), new ReimbDao());

	public static String register(HttpServletRequest req) {
		if (!req.getMethod().equals("POST")) {
			return "resources/html/index.html";
		}
		User u = new User();
		u.setUsername(req.getParameter("username"));
		u.setPassword(req.getParameter("password"));
		u.setEmail(req.getParameter("email"));
		u.setFirstname(req.getParameter("firstname"));
		u.setLastname(req.getParameter("lastname"));
			try {
				uServ.register(u);
				return "login.rs";
			} catch (UserAlreadyExistsException e) {
				return "invalidregistration.rs";
			}	
	}
}
