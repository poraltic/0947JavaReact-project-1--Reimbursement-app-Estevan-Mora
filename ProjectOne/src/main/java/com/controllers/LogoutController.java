package com.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LogoutController {

	public final static String logout (HttpServletRequest req) {
		HttpSession session = req.getSession();
		session.invalidate();
		return "index.rs";
	}
}
