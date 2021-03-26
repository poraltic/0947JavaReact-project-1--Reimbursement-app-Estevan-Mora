package services;

import beans.User;
import dao.ReimbDao;
import dao.UserDao;
import exceptions.InvalidCredentialsException;
import exceptions.UserAlreadyExistsException;

public class UserServices {

	UserDao userDao;
	ReimbDao reimbDao;
	
	public UserServices(UserDao userDao, ReimbDao reimbDao) {
		this.userDao = userDao;
		this.reimbDao = reimbDao;
	}
	
	public User login(String user, String pass) {
		User u = userDao.getUser(user, pass);
		if((u.getUsername() != null && u.getPassword() != null) && (u.getUsername().toString().equals(user) && u.getPassword().toString().equals(pass))) {
			return u;
		} else {
			throw new InvalidCredentialsException("Incorrect Username or Password! Please try again: ");
		}
	}
	
	public void register(User u) {
		User us = userDao.getUser(u.getUsername().toString(), u.getPassword().toString());
		if(us == null || us.getUsername() == null) {
		userDao.addUser(u);
		} else {
			throw new UserAlreadyExistsException("Username Already Exists!");
		}
	}
}
