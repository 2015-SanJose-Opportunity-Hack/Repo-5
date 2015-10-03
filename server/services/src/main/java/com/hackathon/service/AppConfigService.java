package com.paypal.psc.rs.service;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paypal.psc.rs.common.crypto.EncryptedPropertyPlaceholderConfigurer;
import com.paypal.psc.rs.dao.AppConfigDAO;
import com.paypal.psc.rs.entities.AppConfig;

@Service
public class AppConfigService {

	
	private Logger logger = Logger.getLogger(getClass());
	

	
	@Autowired
	private EncryptedPropertyPlaceholderConfigurer cryptoLibrary;	
	
	@Autowired
	private AppConfigDAO appConfigDAO;
	
	
	
	@Transactional
	public String getDeniedProperty() {
		AppConfig config = appConfigDAO.getApplicationProperty("tool_access_request_dl");
		if(config == null || StringUtils.isBlank(config.getValue())) {
			config = new AppConfig();
			config.setName("tool_access_request_dl");
			config.setValue("DL-PP-PSC-PDC@ebay.com");
			appConfigDAO.persist(config);			
		}
		return config.getValue();
	}
	
}
