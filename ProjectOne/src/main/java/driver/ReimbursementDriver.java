package driver;

import java.time.LocalDateTime;

import beans.Reimbursement;
import beans.Reimbursement.ReimbursementType;
import beans.Reimbursement.StatusType;
import dao.ReimbDao;
import dao.UserDao;
import services.UserServices;

public class ReimbursementDriver {
	
	public static void main(String[] args) {
		ReimbDao rd = new ReimbDao();
		Reimbursement r = new Reimbursement(250, LocalDateTime.now(), null, "I'm just here to test", "Poraltic", StatusType.PENDING, ReimbursementType.LODGING);
		r.setAuthorId(10);
		r.setReimbType(r.getReimbType());
		//rd.addReimb(r);
		//rd.getAllReimbs();
		//System.out.println(rd.getReimb(3).toString());
		//rd.getReimbByAuthor(10);
		//rd.getReimbByStatus(2);
		
	}

}
