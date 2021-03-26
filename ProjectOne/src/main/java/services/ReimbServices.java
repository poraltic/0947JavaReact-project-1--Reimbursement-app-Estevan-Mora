package services;

import beans.Reimbursement;
import beans.User;
import dao.ReimbDao;

public class ReimbServices {
	
	ReimbDao reimbDao;

	public ReimbServices(ReimbDao reimbDao) {
		super();
		this.reimbDao = reimbDao;
	}
	
	public void newReimb(User u) {
		//Reimbursement r = new Reimbursement();
	}
	
	public void approveOrDenyReimb(Reimbursement r, int statusId) {
		
	}

}
