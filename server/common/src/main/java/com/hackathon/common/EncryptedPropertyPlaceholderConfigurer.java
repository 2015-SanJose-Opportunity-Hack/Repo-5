package com.hackathon.common;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.stereotype.Component;

import com.paypal.pse.crypto.CryptoFactory;
import com.paypal.pse.crypto.CryptoFactoryImpl;

@Component("cryptoLibrary")
public class EncryptedPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {
	
	private CryptoFactory cryptoFactory = new CryptoFactoryImpl();
	private static final Logger log = Logger.getLogger(EncryptedPropertyPlaceholderConfigurer.class);
	
	@Override
	public String convertPropertyValue(String originalValue) 
	{
		return originalValue;
	}
	
	public String encryptValue(String originalValue) {
		Object data = this.getEncrypedValue(originalValue);		
		 if (data instanceof String[]) {
			String[] str = (String[]) data;
			return (String) (str[0] + "\\|" + str[1]);
		}
		return (String) data;
	}
	public Object getEncrypedValue(String originalValue) {
		return originalValue;
	}	
	
}
