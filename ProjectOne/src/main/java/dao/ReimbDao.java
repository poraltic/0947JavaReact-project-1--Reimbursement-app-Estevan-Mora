package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import beans.Reimbursement;
import beans.Reimbursement.ReimbursementType;
import beans.Reimbursement.StatusType;
import utilities.Connections;

public class ReimbDao {
	List<Reimbursement> rList;
	private String sql;
	private Connections conn = Connections.getConnections();

	public void addReimb(Reimbursement r) {
		sql = "INSERT INTO reimb(amount, date_submitted, description, author, statusid, type_id) VALUES(?,CURRENT_TIMESTAMP,?,?,?,?)";
		String sql2 = "SELECT * FROM reimb WHERE date_submitted >= '"+ r.getTimestamp().toString() +"' AND author = ?";
		try {
			PreparedStatement ps = conn.getConnection().prepareStatement(sql);
			ps.setDouble(1,r.getAmount());
			ps.setString(2, r.getDescription());
			ps.setInt(3, r.getAuthorId());
			ps.setInt(4, r.getStatusTypeId());
			ps.setInt(5, r.getReimbTypeId());
			ps.execute();
			PreparedStatement ps2 = conn.getConnection().prepareStatement(sql2);
			ps2.setInt(1, r.getAuthorId());
			ResultSet rs = ps2.executeQuery();
			while(rs.next()) {
				r.setReimbId(rs.getInt(1));
			}
			rs.close();
			ps2.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Reimbursement getReimb(int id) {
		sql = "select s.status, s.resolved, s.resolver, s.reimb_type, s.date_submitted, s.amount, s.description, ux.email, ux.firstname, ux.lastname, ux.username, ux.user_role, s.reimb_id, ux.user_id from \r\n"
				+ "		(select * from reimb_status rs inner join (select * from reimb r inner join reimb_type rt on r.type_id=rt.reimb_type_id) as rx on rx.statusid=rs.status_id) as s \r\n"
				+ "		inner join (select * from users u inner join user_roles ur on u.role_id=ur.user_role_id) as ux on ux.user_id=s.author where s.reimb_id = ?";
		Reimbursement r = null;
		try {
			PreparedStatement ps = conn.getConnection().prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs =  ps.executeQuery();
			while(rs.next()) {
				if (rs.getString(1).equals("PENDING")) {
					r = new Reimbursement(rs.getDouble(6), rs.getTimestamp(5).toLocalDateTime(), null,
					rs.getString(7), rs.getString(11), StatusType.valueOf(rs.getString(1)),
					ReimbursementType.valueOf(rs.getString(4)));
					r.setReimbId(rs.getInt(13));
					return r;
				} else {
					r = new Reimbursement(rs.getDouble(6), rs.getTimestamp(5).toLocalDateTime(), 
					rs.getTimestamp(2).toLocalDateTime(),
					rs.getString(7), rs.getString(11), StatusType.valueOf(rs.getString(1)),
					ReimbursementType.valueOf(rs.getString(4)));
					r.setResolver(rs.getInt(3));
					r.setReimbId(rs.getInt(13));
					return r;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return r;
	}
	
	public List<Reimbursement> getReimbByAuthor(int id){
		rList = new ArrayList<Reimbursement>();
		sql = "select s.status, s.resolved, s.resolver, s.reimb_type, s.date_submitted, s.amount, s.description, ux.email, ux.firstname, ux.lastname, ux.username, ux.user_role, s.reimb_id, ux.user_id from \r\n"
				+ "		(select * from reimb_status rs inner join (select * from reimb r inner join reimb_type rt on r.type_id=rt.reimb_type_id) as rx on rx.statusid=rs.status_id) as s \r\n"
				+ "		inner join (select * from users u inner join user_roles ur on u.role_id=ur.user_role_id) as ux on ux.user_id=s.author where ux.user_id = ?";
		try {
			PreparedStatement ps = conn.getConnection().prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				if (rs.getString(1).equals("PENDING")) {
					Reimbursement r = new Reimbursement(rs.getDouble(6), rs.getTimestamp(5).toLocalDateTime(), null,
					rs.getString(7), rs.getString(11), StatusType.valueOf(rs.getString(1)),
					ReimbursementType.valueOf(rs.getString(4)));
					r.setReimbId(rs.getInt(13));
					rList.add(r);
				} else {
					Reimbursement r = new Reimbursement(rs.getDouble(6), rs.getTimestamp(5).toLocalDateTime(), 
					rs.getTimestamp(2).toLocalDateTime(),
					rs.getString(7), rs.getString(11), StatusType.valueOf(rs.getString(1)),
					ReimbursementType.valueOf(rs.getString(4)));
					r.setResolver(rs.getInt(3));
					r.setReimbId(rs.getInt(13));
					rList.add(r);
				}
			}
			ps.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rList;
	}

	public List<Reimbursement> getAllReimbs() {
		rList = new ArrayList<Reimbursement>();
		String sql = "select s.status, s.resolved, s.resolver, s.reimb_type, s.date_submitted, s.amount, s.description, ux.email, ux.firstname, ux.lastname, ux.username, ux.user_role, s.reimb_id, ux.user_id from \r\n"
				+ "		(select * from reimb_status rs inner join (select * from reimb r inner join reimb_type rt on r.type_id=rt.reimb_type_id) as rx on rx.statusid=rs.status_id) as s \r\n"
				+ "		inner join (select * from users u inner join user_roles ur on u.role_id=ur.user_role_id) as ux on ux.user_id=s.author";
		try {
			Statement s = conn.getConnection().createStatement();
			ResultSet rs = s.executeQuery(sql);
			while (rs.next()) {
				if (rs.getString(1).equals("PENDING")) {
					Reimbursement r = new Reimbursement(rs.getDouble(6), rs.getTimestamp(5).toLocalDateTime(), null,
					rs.getString(7), rs.getString(11), StatusType.valueOf(rs.getString(1)),
					ReimbursementType.valueOf(rs.getString(4)));
					r.setReimbId(rs.getInt(13));
					rList.add(r);
				} else {
					Reimbursement r = new Reimbursement(rs.getDouble(6), rs.getTimestamp(5).toLocalDateTime(), 
					rs.getTimestamp(2).toLocalDateTime(),
					rs.getString(7), rs.getString(11), StatusType.valueOf(rs.getString(1)),
					ReimbursementType.valueOf(rs.getString(4)));
					r.setReimbId(rs.getInt(13));
					r.setResolver(rs.getInt(3));
					rList.add(r);
				}
			}
			s.close();
			rs.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rList;
	}

	public List<Reimbursement> getReimbByStatus(int status) {
		rList = new ArrayList<Reimbursement>();
		try {
//			conn.getConnection().setAutoCommit(false);
			String sql = "{? = call get_reimbs(?)}";
			Connection c = conn.getConnection();
			CallableStatement cs = c.prepareCall(sql);
			c.setAutoCommit(false);
			cs.registerOutParameter(1, Types.OTHER);
			cs.setInt(2, status);
			cs.execute();
			ResultSet rs = (ResultSet) cs.getObject(1);
			while (rs.next()) {
				if (rs.getString(1).equals("PENDING")) {
					Reimbursement r = new Reimbursement(rs.getDouble(4), rs.getTimestamp(3).toLocalDateTime(), null,
					rs.getString(5), rs.getString(9), StatusType.valueOf(rs.getString(1)),
					ReimbursementType.valueOf(rs.getString(2)));
					r.setReimbId(rs.getInt(12));
					System.out.println(r.toString());
					rList.add(r);
				} else {
					Reimbursement r = new Reimbursement(rs.getDouble(6), rs.getTimestamp(5).toLocalDateTime(), 
					rs.getTimestamp(2).toLocalDateTime(),
					rs.getString(7), rs.getString(11), StatusType.valueOf(rs.getString(1)),
					ReimbursementType.valueOf(rs.getString(4)));
					r.setResolver(rs.getInt(3));
					r.setReimbId(rs.getInt(14));
					System.out.println(r.toString());
					rList.add(r);
				}
			}
			cs.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rList;
	}

	public void updateReimbursement(Reimbursement r) {
		sql = "UPDATE reimb set resolved = CURRENT_TIMESTAMP, status_id = ?, resolver = ? WHERE reimbid = ?";
		try {
			PreparedStatement ps = conn.getConnection().prepareStatement(sql);
			ps.setInt(1, r.getStatusTypeId());
			ps.setInt(2, r.getResolver());
			ps.setInt(3, r.getReimbId());
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
