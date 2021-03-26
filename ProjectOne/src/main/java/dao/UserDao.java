package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import beans.User;
import beans.User.UserRole;
import utilities.Connections;

public class UserDao {
	
	private  String sql;
	private Connections conn = Connections.getConnections();

	public void addUser(User u) {
		try {
			sql = "INSERT INTO users(username, pass, firstname, lastname, email, role_id) values (?,?,?,?,?,?)";
			PreparedStatement ps = conn.getConnection().prepareStatement(sql);
			ps.setString(1, u.getUsername());
			ps.setString(2, u.getPassword());
			ps.setString(3, u.getFirstname());
			ps.setString(4, u.getLastname());
			ps.setString(5, u.getEmail());
			ps.setInt(6, 0);
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public User getUser(String username, String password) {
		User u = new User();
		try {
			sql = "SELECT  * FROM users u, user_roles ur WHERE username = ? AND pass = ? AND ur.user_role_id=u.role_id";
			PreparedStatement ps = conn.getConnection().prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				u.setId(rs.getInt(1));
				u.setUsername(rs.getString(2));
				u.setPassword(rs.getString(3));
				u.setFirstname(rs.getString(4));
				u.setLastname(rs.getString(5));
				u.setEmail(rs.getString(6));
				u.setUserRole(UserRole.valueOf(rs.getString(9)));
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return u;
	}
	
	public User getUserById(int id) {
		User u = new User();
		try {
			sql = "SELECT  * FROM users u, user_roles ur WHERE user_id = ? AND ur.user_role_id=u.role_id";
			PreparedStatement ps = conn.getConnection().prepareStatement(sql);
			ps.setInt(1, 1);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				u.setId(rs.getInt(1));
				u.setUsername(rs.getString(2));
				u.setPassword(rs.getString(3));
				u.setFirstname(rs.getString(4));
				u.setLastname(rs.getString(5));
				u.setEmail(rs.getString(6));
				u.setUserRole(UserRole.valueOf(rs.getString(9)));
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return u;
	}
}
