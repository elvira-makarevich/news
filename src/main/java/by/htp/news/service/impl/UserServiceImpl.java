package by.htp.news.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.htp.news.dao.UserDAO;
import by.htp.news.entity.User;
import by.htp.news.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;

	@Override
	@Transactional
	public void register(User theUser) {
		userDAO.register(theUser);
	}

}
