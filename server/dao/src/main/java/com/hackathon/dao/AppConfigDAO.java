package com.hackathon.dao;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.hackathon.entities.AppConfig;

@Repository
public class AppConfigDAO extends ParentDAO<String, AppConfig> 
{
	private static final Logger logger = Logger.getLogger(AppConfigDAO.class);

	
	
	public com.hackathon.entities.AppConfig find(int id) {
		AppConfig config = getEntityManager().find(AppConfig.class, id);
		return config;
	}
	
	@SuppressWarnings("unchecked")	
	public AppConfig getApplicationProperty(String name) {
		List<AppConfig> applicationList = getEntityManager()
				.createQuery("select a from AppConfig a where a.name = :name")
				.setParameter("name", name).getResultList();
		if (applicationList == null || applicationList.isEmpty()) {
			logger.error("No application value has been found for " + name);
			return null;
		} else if (applicationList.size() != 1) {
			logger.error("More than 1 AppConfig property has been found with the name "
					+ name);
		}
		return applicationList.get(0);
	}	

	@SuppressWarnings("unchecked")
	
	public List<AppConfig> getApplicationProperties(String name) {
		List<AppConfig> applicationList = null;
		if(StringUtils.isBlank(name)) {
			applicationList = getEntityManager()
					.createQuery("select a from AppConfig a order by name").getResultList();
		} else {
			applicationList = getEntityManager()
				.createQuery("select a from AppConfig a where a.name = :name")
				.setParameter("name", name).getResultList();
		}
		if (applicationList == null || applicationList.isEmpty()) {
			logger.error("No application value has been found for " + name);
			return null;
		}
		return applicationList;
	}	
	
	@SuppressWarnings("unchecked")	
	public List<AppConfig> getProperties(String searchTermValue) {
//		if("name".equalsIgnoreCase(searchTerm)) {
//			return this.getApplicationProperties(searchTermValue);
//		}
		List<AppConfig> applicationList = null;		
			applicationList = getEntityManager()
				.createQuery("select a from AppConfig a where a.name LIKE :name")
			.setParameter("name", "%" + searchTermValue + "%").getResultList();		
		if (applicationList == null || applicationList.isEmpty()) {
			logger.error("No application value has been found for " + searchTermValue);
			return null;
		}
		return applicationList;
	}

}
