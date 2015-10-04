package com.hackathon.dao;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.hackathon.entities.Business;

@Repository
public class BusinessDAO extends ParentDAO<String, Business> 
{
	private static final Logger logger = Logger.getLogger(BusinessDAO.class);

	
	
	public Business find(int id) {
		Business business = getEntityManager().find(Business.class, id);
		return business;
	}
	
	@SuppressWarnings("unchecked")
	public List<Business> getAllBusinesses(){
		List<Business> businessList = null;
		businessList = getEntityManager().createQuery("select b from Business b").getResultList();
		if(businessList == null || businessList.isEmpty()){
			logger.error("No business found");
			return null;
		}
		return businessList;
	}

	@SuppressWarnings("unchecked")
	public List<String> getAllCities(){
		List<String> cityList = null;
		cityList = getEntityManager().createQuery("select distinct b.city from Business b").getResultList();
		if(cityList == null || cityList.isEmpty()){
			logger.error("No cities found");
			return null;
		}
		return cityList;
	}
	
	@SuppressWarnings("unchecked")
	public List<String> getAllCategories(){
		List<String> categoryList = null;
		categoryList = getEntityManager().createQuery("select distinct b.category from Business b").getResultList();
		if(categoryList == null || categoryList.isEmpty()){
			logger.error("No categories found");
			return null;
		}
		return categoryList;
	}
	
	@SuppressWarnings("unchecked")
	public List<Business> getInOrder(String parameter, String order) {
		List<Business> businessList = null;
		String query = "select b from Business b order by b." + parameter + " " + order;
		businessList = getEntityManager().createQuery(query).getResultList();
		return businessList;
	}
	
	@SuppressWarnings("unchecked")
	public List<Business> findByCategory(String category) { 
		List<Business> businessList = null;
		businessList = getEntityManager().createQuery("select b from Business b where b.category = :category")
				.setParameter("category", category).getResultList();
		if(businessList == null || businessList.isEmpty()){
			logger.error("No business found");
			return null;
		}
		return businessList;
	}
	
	@SuppressWarnings("unchecked")
	public List<Business> findByCity(String city) { 
		List<Business> businessList = null;
		businessList = getEntityManager().createQuery("select b from Business b where b.city = :city")
				.setParameter("city", city).getResultList();
		if(businessList == null || businessList.isEmpty()){
			logger.error("No business found");
			return null;
		}
		return businessList;
	}

	@SuppressWarnings("unchecked")
	public Business findByName(String name) { 
		Business b = null;
		b = (Business) getEntityManager().createQuery("select b from Business b where b.name = :name")
				.setParameter("name", name).getResultList().get(0);
		if(b == null){
			logger.error("No business found");
			return null;
		}
		return b;
	}
	
}
