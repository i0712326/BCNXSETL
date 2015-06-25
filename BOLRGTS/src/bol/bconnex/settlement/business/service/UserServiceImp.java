package bol.bconnex.settlement.business.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bol.bconnex.settlement.data.dao.UserDao;
import bol.bconnex.settlement.data.entity.User;
@Service("userService")
public class UserServiceImp implements UserService {
	@Autowired
	private UserDao userDao;
	public void setUserDao(UserDao userDao){
		this.userDao = userDao;
	}
	@Override
	public boolean checkUser(User usr) {
		return userDao.authen(usr);
	}
}
