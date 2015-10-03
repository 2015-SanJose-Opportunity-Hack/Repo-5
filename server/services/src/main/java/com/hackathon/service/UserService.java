package com.paypal.psc.rs.service;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paypal.psc.rs.common.ApplicationUtil;
import com.paypal.psc.rs.common.crypto.EncryptedPropertyPlaceholderConfigurer;
import com.paypal.psc.rs.common.form.UserForm;
import com.paypal.psc.rs.dao.RoleDAO;
import com.paypal.psc.rs.dao.UserDAO;
import com.paypal.psc.rs.entities.Role;
import com.paypal.psc.rs.entities.User;

@Service
public class UserService {
	private Logger logger = Logger.getLogger(getClass());

	@Autowired
	private UserDAO userDao;

	@Autowired
	private ShoppingCartService shoppingCartService;

	@Autowired
	private RoleDAO roleDAO;

	@Autowired
	private EncryptedPropertyPlaceholderConfigurer cryptoLibrary;

	@Transactional
	public Long getUserId(String loginId) {

		User user = userDao.findByLoginId(loginId);
		if (user == null) {
			return null;
		} else {
			return user.getId();
		}

	}

	@Transactional
	public Integer getUserRole(Long id) {

		// User user = userDao.findByLoginId(loginId);
		User user = userDao.findById(id);
		if (user == null) {
			return null;
		} else {
			return user.getRole().getId();
		}

	}

	@Transactional
	public Double getCreditBalance(Long id) {
		// User user = userDao.findByLoginId(loginId);
		User user = userDao.findById(id);
		if (user == null) {
			return null;
		} else {
			return user.getCredit();
		}

	}

	@Transactional
	public void setCreditBalance(Long id, Double credit) {
		userDao.setCreditBalance(id, credit);
	}

	public UserForm getUsers() {
		UserForm users = new UserForm();
		List<User> userList = userDao.listUsers();
		if (userList == null || userList.isEmpty()) {
			logger.error("User list is empty");
			return null;
		}
		prepareUserForm(users, userList);
		return users;

	}

	private void prepareUserForm(UserForm userForm, List<User> userList) {

		int userCount = userList.size();

		long[] id = new long[userCount];
		String[] loginId = new String[userCount];
		String[] name = new String[userCount];
		String[] location = new String[userCount];
		String[] role = new String[userCount];
		long[] managerId = new long[userCount];
		String[] managerLoginId = new String[userCount];
		String[] managerName = new String[userCount];
		int[] active = new int[userCount];
		double[] credit = new double[userCount];
		int i = 0;

		for (User user : userList) {
			id[i] = user.getId();
			loginId[i] = cryptoLibrary.convertPropertyValue(user.getLoginId());
			name[i] = cryptoLibrary.convertPropertyValue(user.getName());
			if (user.getLocation() != null) {
				location[i] = user.getLocation();
			} else {
				location[i] = "";
			}
			role[i] = user.getRole().getName();
			User managerUser = userDao.findById(user.getManagerId());
			if (managerUser != null) {
				managerId[i] = managerUser.getId();
				managerLoginId[i] = cryptoLibrary.convertPropertyValue(managerUser.getLoginId());
				managerName[i] = cryptoLibrary.convertPropertyValue(managerUser.getName());
			} else {
				managerId[i] = 0;
				managerLoginId[i] = "";
				managerName[i] = "";
			}
			credit[i] = user.getCredit();
			active[i] = user.getActive();
			i++;
		}

		userForm.setId(id);
		userForm.setLoginId(loginId);
		userForm.setName(name);
		userForm.setLocation(location);
		userForm.setRole(role);
		userForm.setManagerId(managerId);
		userForm.setManagerLoginId(managerLoginId);
		userForm.setManagerName(managerName);
		userForm.setCredit(credit);
		userForm.setActive(active);
	}

	public UserForm getUser(long id) {
		UserForm userForm = new UserForm();
		
		User user = userDao.findById(id);
		if (user == null) {
			logger.error("User list is empty");
			return null;
		}
		populateUser(user, userForm);
		return userForm;

	}

	private void populateUser(User user, UserForm userForm) {
		int userCount = 1;
		long[] id = new long[userCount];
		String[] loginId = new String[userCount];
		String[] name = new String[userCount];
		String[] location = new String[userCount];
		String[] role = new String[userCount];
		long[] managerId = new long[userCount];
		String[] managerLoginId = new String[userCount];
		String[] managerName = new String[userCount];
		int[] active = new int[userCount];
		double[] credit = new double[userCount];
		int i = 0;
		id[i] = user.getId();
		loginId[i] = cryptoLibrary.convertPropertyValue(user.getLoginId());
		name[i] = cryptoLibrary.convertPropertyValue(user.getName());
		if (user.getLocation() != null) {
			location[i] = user.getLocation();
		} else {
			location[i] = "";
		}
		role[i] = user.getRole().getName();
		User managerUser = userDao.findById(user.getManagerId());
		if (managerUser != null) {
			managerId[i] = managerUser.getId();
			managerLoginId[i] = cryptoLibrary.convertPropertyValue(managerUser.getLoginId());
			managerName[i] = cryptoLibrary.convertPropertyValue(managerUser.getName());
		} else {
			managerId[i] = 0;
			managerLoginId[i] = "";
			managerName[i] = "";
		}
		credit[i] = user.getCredit();
		active[i] = user.getActive();
		userForm.setId(id);
		userForm.setLoginId(loginId);
		userForm.setName(name);
		userForm.setLocation(location);
		userForm.setRole(role);
		userForm.setManagerId(managerId);
		userForm.setManagerLoginId(managerLoginId);
		userForm.setManagerName(managerName);
		userForm.setActive(active);
		userForm.setCredit(credit);
	}

	public UserForm getUsersByManager(long managerId) {
		UserForm users = new UserForm();
		List<User> userList = userDao.listUsersByManagerId(managerId);
		if (userList == null || userList.isEmpty()) {
			logger.error("User list is empty");
			return null;
		}
		prepareUserForm(users, userList);
		return users;
	}

	@Transactional
	public boolean removeUser(long id) {
		return userDao.removeUser(id);
	}

	@Transactional
	public boolean enableUser(long id) {
		return userDao.enableUser(id);
	}

	@Transactional
	public User findUserById(long id) {
		// User user = userDao.findByLoginId(loginId);
		User user = userDao.findById(id);
		return user;
	}

	@Transactional
	public boolean updateUserBalance(long userId, double credit) {
		return userDao.updateBalance(userId, credit);
	}

	@Transactional
	public List<User> findAllAdmins() {
		Role role = roleDAO.find(10);
		List<User> admins = null;
		admins = userDao.findAllAdmins(role);
		return admins;
	}

	@Transactional
	public boolean addNewUser(long id, String loginId, String name, String location, String role, long manager, int status) {
		
		User user; 
		if(id == 0){
			logger.info("Adding a new user");
			user = new User();
		}else{
			user = userDao.findById(id);
			if (user == null) {
				logger.info("Couldn't find in database. Making a new entry..");
				user = new User();
			}
		}

		User managerUser = userDao.findById(manager);
		if (managerUser == null) {
			logger.error("Couldn't find manager with username: " + manager);
		} else {
			manager = managerUser.getId();
		}

		user.setLoginId(cryptoLibrary.encryptValue(loginId));
		try {
			user.setLoginIdHash(ApplicationUtil.getMD5Hex(loginId));
		} catch (NoSuchAlgorithmException nsae) {
			nsae.printStackTrace();
			logger.error("Could not hash login id");
			return false;
		}
		user.setName(cryptoLibrary.encryptValue(name));
		user.setLocation(location);
		user.setActive(status);
		user.setManagerId(manager);
		Role roleObject = userDao.getRoleByRoleName(role);
		if (roleObject == null) {
			logger.error("Role not found in database");
			return false;
		}
		user.setRole(roleObject);

		try {
			userDao.persist(user);
		} catch (Exception e) {
			logger.info("Error persisting user object");
			return false;
		}

		return true;

	}

	@Transactional
	public long getIdByLoginId(String loginId) {
		long id = userDao.getIdByLoginId(loginId);
		return id;
	}

	@Transactional
	public boolean findUserByLoginId(String loginId) {
		String loginIdHash;
		try {
			loginIdHash = ApplicationUtil.getMD5Hex(loginId);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return false;
		}
		User user = userDao.findByLoginIdHash(loginIdHash);
		return (user!=null);
	}
	
	@Transactional
	public boolean updateName(long id, String name){
		User user = userDao.findById(id);
			if(user == null){
				return false;
			}
			user.setName(cryptoLibrary.encryptValue(name));
			return (userDao.persist(user) != null);
	
	}

}
