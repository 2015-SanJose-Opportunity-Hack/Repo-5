package com.hackathon.dao;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.hackathon.entities.Business;
import com.hackathon.entities.User;

@Repository
public class UserDAO extends ParentDAO<String, Business> 
{
	private static final Logger logger = Logger.getLogger(BusinessDAO.class);

	
	
	public User find(int id) {
		User user = getEntityManager().find(User.class, id);
		return user;
	}

	@SuppressWarnings("unchecked")
	public List<Integer> findBusinessByUserId(int userId) { 
		List<Integer> businessList = null;
		businessList = getEntityManager().createQuery("select u.id from User u where u.business_lent_id = :business_lent_id")
				.setParameter("business_lent_id", userId).getResultList();
		if(businessList == null || businessList.isEmpty()){
			logger.error("No business found");
			return null;
		}
		return businessList;
	}

}
