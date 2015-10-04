package com.hackathon.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hackathon.common.form.BusinessForm;
import com.hackathon.dao.BusinessDAO;
import com.hackathon.entities.Business;

@Service
public class BusinessService {

	@Autowired
	private BusinessDAO businessDAO;

	@Transactional
	public BusinessForm getAllBusinesses() {
		// TODO Auto-generated method stub

		List<Business> businessList = businessDAO.getAllBusinesses();
		BusinessForm businessForm = prepareBusinessForm(businessList);

		return businessForm;
	}
	
	@Transactional
	public BusinessForm getBusinessInOrder(String parameter, String order){
		List<Business> businessList = businessDAO.getInOrder(parameter, order);
		BusinessForm businessForm = prepareBusinessForm(businessList);
		return businessForm;
	}
	
	@Transactional
	public BusinessForm getBusinessLentTo(int[] businessId){
		if(businessId.length == 0){
			return null;
		}
		List<Business> businessList = new ArrayList<Business>();
		for(int i = 0; i< businessId.length; i++){
			Business b = businessDAO.find(businessId[i]);
			if(b!=null){
				businessList.add(b);
			}
		}
		
		if(businessList == null || businessList.isEmpty()){
			return null;
		}else{
			BusinessForm businessForm = prepareBusinessForm(businessList);
			return businessForm;
		}
	}
	
	@Transactional
	public List<String> getCategories(){
		return businessDAO.getAllCategories();
		
	}
	
	@Transactional
	public List<String> getCities(){
		return businessDAO.getAllCities();
		
	}
	
	@Transactional
	public BusinessForm getBusinessByCategory(List<String> categories){
		if(categories == null || categories.isEmpty()){
			return null;
		}
		List<Business> businessList = new ArrayList<Business>();
		for(String category: categories){
			List<Business> b = businessDAO.findByCategory(category);
			if(b!=null){
				businessList.addAll(b);
			}
		}
		
		if(businessList == null || businessList.isEmpty()){
			return null;
		}else{
			BusinessForm businessForm = prepareBusinessForm(businessList);
			return businessForm;
		}
	}
	
	@Transactional
	public BusinessForm getBusinessByCity(List<String> cities){
		if(cities == null || cities.isEmpty()){
			return null;
		}
		List<Business> businessList = new ArrayList<Business>();
		for(String city: cities){
			List<Business> b = businessDAO.findByCity(city);
			if(b!=null){
				businessList.addAll(b);
			}
		}
		
		if(businessList == null || businessList.isEmpty()){
			return null;
		}else{
			BusinessForm businessForm = prepareBusinessForm(businessList);
			return businessForm;
		}
	}
	


	@Transactional
	public BusinessForm getBusinessByName(String name){
		if(name == null){
			return null;
		}
		List<Business> businessList = new ArrayList<Business>();
	
			Business b = businessDAO.findByName(name);
			if(b!=null){
				businessList.add(b);
			}
		
		
		if(businessList == null || businessList.isEmpty()){
			return null;
		}else{
			BusinessForm businessForm = prepareBusinessForm(businessList);
			return businessForm;
		}
	}
	
	private BusinessForm prepareBusinessForm(List<Business> businessList) {
		if (businessList == null || businessList.isEmpty()) {
			return null;
		}
		BusinessForm businessForm = new BusinessForm();
		int size = businessList.size();

		int[] id = new int[size];
		String[] category = new String[size];
		String[] businessName = new String[size];
		String[] storeLink = new String[size];
		String[] ownerLink = new String[size];
		String[] ownerName = new String[size];
		int[] likes = new int[size];
		String[] yelpURL = new String[size];
		double[] latitude = new double[size];
		double[] longitude = new double[size];
		String[] city = new String[size];
		String[] state = new String[size];
		long[] zipcode = new long[size];

		int i = 0;
		for (Business b : businessList) {
			id[i] = b.getId();
			category[i] = b.getCategory();
			businessName[i] = b.getBusinessName();
			storeLink[i] = b.getStoreLink();
			ownerLink[i] = b.getOwnerLink();
			ownerName[i] = b.getOwnerName();
			likes[i] = b.getLikes();
			yelpURL[i] = b.getYelpURL();
			latitude[i] = b.getLatitude();
			longitude[i] = b.getLongitude();
			city[i] = b.getCity();
			state[i] = b.getState();
			zipcode[i] = b.getZipcode();
			i++;

		}
		businessForm.setBusinessName(businessName);
		businessForm.setCategory(category);
		businessForm.setId(id);
		businessForm.setStoreLink(storeLink);
		businessForm.setOwnerLink(ownerLink);
		businessForm.setOwnerName(ownerName);
		businessForm.setLikes(likes);
		businessForm.setYelpURL(yelpURL);
		businessForm.setLatitude(latitude);
		businessForm.setLongitude(longitude);
		businessForm.setCity(city);
		businessForm.setState(state);
		businessForm.setZipcode(zipcode);
		
		return businessForm;
	}

}
