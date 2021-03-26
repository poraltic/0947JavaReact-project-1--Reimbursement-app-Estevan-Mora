package com.controllers;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import beans.Reimbursement;
import beans.Reimbursement.ReimbursementType;
import beans.Reimbursement.StatusType;
import beans.User;
import dao.ReimbDao;
import dao.UserDao;

public class NewReimbController {
	
	public final static Logger logger = Logger.getLogger(NewReimbController.class);
	public static String newReimb(HttpServletRequest req) {
		HttpSession session = req.getSession();
		if(session.getAttribute("username") == null) {
			return "error.rs";
		}
		
		System.out.println("made it here");
		ReimbDao rd = new ReimbDao();
		UserDao ud = new UserDao();
		System.out.println(session.getAttribute("username in the reimb controller"));
		Reimbursement r = new Reimbursement((double)Double.parseDouble(req.getParameter("amount")), LocalDateTime.now(), null, req.getParameter("description"),
				String.valueOf(session.getAttribute("username")), StatusType.PENDING, ReimbursementType.valueOf(req.getParameter("reimbtype")));
		r.setAuthorId(Integer.parseInt(String.valueOf(session.getAttribute("id"))));
		r.setReimbType(r.getReimbType());
		rd.addReimb(r);
		logger.info("successfully added reimbursement");
		System.out.println(r);
		return "home.rs";
	}
}
