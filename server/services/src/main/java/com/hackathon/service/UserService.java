package com.hackathon.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hackathon.common.form.UserForm;
import com.hackathon.dao.UserDAO;
import com.hackathon.entities.User;

@Service
public class UserService {
	private Logger logger = Logger.getLogger(getClass());

	@Autowired 
	private UserDAO userDao;
	
	@Transactional
	public UserForm getUserById(int id) {

		UserForm userForm = new UserForm();
		User user  = userDao.find(id);
		if (user == null) {
			logger.error("User not found");
			return null;
		} else {
			userForm.setId(id);
			userForm.setBusinessId(user.getBusinessId());
			userForm.setEmail(user.getEmail());
			userForm.setName(user.getName());
		}
		
		return userForm;

	}

}
