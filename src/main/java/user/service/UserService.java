package user.service;

import user.dao.UserDAO;
import user.vo.User;

public class UserService {

	public User signInUser(User user) {
		
		try {
			UserDAO dao = new UserDAO();
			user = dao.select(user);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return user;
	}
	
	public int signUpUser(User user) {
	
		int result = 0;
		try {
			UserDAO dao = new UserDAO();
			result = dao.insert(user);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}
}
