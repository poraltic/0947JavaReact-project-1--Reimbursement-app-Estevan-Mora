package com.servlets;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.controllers.DisplayTicketController;
import com.controllers.LoginController;
import com.controllers.LogoutController;
import com.controllers.NewReimbController;
import com.controllers.RegisterController;
import com.fasterxml.jackson.core.JsonProcessingException;

public class RequestHelper {

	public static String process(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException{
		switch(req.getRequestURI()) {
		case "/ProjectOne/login.rs":
			return LoginController.login(req);
		case "/ProjectOne/register.rs":
			return RegisterController.register(req);
		case "/ProjectOne/newreimb.rs":
			return NewReimbController.newReimb(req);
		case "/ProjectOne/reimb.rs":
			return "/resources/html/newreimbursement.html";
		case "/ProjectOne/home.rs":
			return "/resources/html/home.html";
		case "/ProjectOne/managerhome.rs":
			return "/resources/html/managerlogin.html";
		case "/ProjectOne/incorrectlogin.rs":
			return "/resources/html/index.html";
		case "/ProjectOne/registerpage.rs":
			return "/resources/html/register.html";
		case "/ProjectOne/logout.rs":
			return LogoutController.logout(req);
		case "/ProjectOne/index.rs":
			return "/resources/html/index.html";
		default:
			return "error.html";
		}
	}
}
