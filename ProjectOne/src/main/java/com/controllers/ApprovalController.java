package com.controllers;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import beans.Reimbursement;
import beans.Reimbursement.StatusType;
import dao.ReimbDao;

public class ApprovalController {

	public static final String Approval(HttpServletRequest req) {
		ReimbDao rDao = new ReimbDao();
		HttpSession session = req.getSession();
		if(session.isNew()) {
			return "index.rs";
		}
		
		String app = req.getParameter("status");
		int i = Integer.parseInt(req.getParameter("reimbid"));
		Reimbursement r = rDao.getReimb(i);
		if(app.equalsIgnoreCase("Approved")) {
			r.setStatusType(StatusType.APPOVED);
		} else {
			r.setStatusType(StatusType.DENIED);
		}
		r.setResolver();
		
		rDao.updateReimbursement(r);
		return "managerhome.rs";
	}
}
