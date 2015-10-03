package com.paypal.psc.rs.common.crypto;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.stereotype.Component;


import com.paypal.pse.crypto.Base64;
import com.paypal.pse.crypto.Crypto;
import com.paypal.pse.crypto.CryptoFactory;
import com.paypal.pse.crypto.CryptoFactoryImpl;
import com.paypal.pse.crypto.EncryptDO;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

@Component("cryptoLibrary")
public class EncryptedPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {
	
	private CryptoFactory cryptoFactory = new CryptoFactoryImpl();
	private static final Logger log = Logger.getLogger(EncryptedPropertyPlaceholderConfigurer.class);
	
	@Override
	public String convertPropertyValue(String originalValue) 
	{
		int index = originalValue.indexOf("\\|");
		if(index < 0) {
			return originalValue;
		}
		if(log.isDebugEnabled()) {
			log.debug("Decrypting " + originalValue);
		}
		Crypto instance = cryptoFactory.getInstance();
		
		byte[] dataBytes = Base64.decode(originalValue.substring(0, index));
		byte[] dataIvBytes = Base64.decode(originalValue.substring(index + 2, originalValue.length()));
		
		try {
			String passphrase = System.getenv(CryptoCommon.PASSPHRASE_LOCATOR);
			
			String uncData = instance.decrypt(dataBytes, dataIvBytes, passphrase);
			return uncData;
			
		} catch (Exception e) {
			String msg = "Original value: "+originalValue;
			log.error(msg, e);
			throw new RuntimeException(msg, e);
		}		
		
//		if (valAndIV.length == 2) 
//		{
//			if(log.isDebugEnabled()) {
//				log.debug("Decrypting " + originalValue);
//			}
//			Crypto instance = cryptoFactory.getInstance();
//			
//			byte[] dataBytes = Base64.decode(valAndIV[0]);
//			byte[] dataIvBytes = Base64.decode(valAndIV[1]);
//			
//			try {
//				String passphrase = System.getenv(CryptoCommon.PASSPHRASE_LOCATOR);
//				
//				String uncData = instance.decrypt(dataBytes, dataIvBytes, passphrase);
//				return uncData;
//				
//			} catch (Exception e) {
//				String msg = "Original value: "+originalValue;
//				log.error(msg, e);
//				throw new RuntimeException(msg, e);
//			}
//		}
//		return originalValue;
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
		if(originalValue == null || originalValue.trim().length() == 0) {
			return originalValue;
		}
		Crypto instance = cryptoFactory.getInstance();
		try {
			String passphrase = System.getenv(CryptoCommon.PASSPHRASE_LOCATOR);
			if(passphrase == null || passphrase.trim().length() == 0) {
				return originalValue;
			}
			EncryptDO uncData = instance.encrypt(originalValue, passphrase);
			String[] data = new String[2];
			data[0] = Base64.encode(uncData.cipherText, false);
			data[1] = Base64.encode(uncData.iv, false);
			return data;
			
		} catch (Exception e) {
			log.error("Error encrypting data.", e);
			throw new RuntimeException(e);
		}
	}	
	
	public byte[] decryptData(final byte[] data, String iv) {
		String passphrase = System.getenv(CryptoCommon.PASSPHRASE_LOCATOR);

		if(StringUtils.isBlank(passphrase) || data == null || data.length == 0 || StringUtils.isBlank(iv)) {
			return data;
		}
		Crypto instance = cryptoFactory.getInstance();		
		byte[] dataIvBytes = Base64.decode(iv);			
		try {
			byte[] uncData = instance.decryptData(data, dataIvBytes, passphrase);
			return uncData;
			
		} catch (Exception e) {
			log.error("Error decrypting file data.", e);
			throw new RuntimeException(e);
		}
	}
	
	public Object[] encryptData(final byte[] data) {
		String passphrase = System.getenv(CryptoCommon.PASSPHRASE_LOCATOR);
		Object[] object = new Object[2];
		if(StringUtils.isBlank(passphrase) || data == null || data.length == 0) {
			object[0] = data;
			object[1] = "";
			return object;
		}
		Crypto instance = cryptoFactory.getInstance();
		try {
			EncryptDO uncData = instance.encryptData(data, passphrase);
			
			object[0] = uncData.cipherText;
			object[1] = Base64.encode(uncData.iv, false);
			return object;
			
		} catch (Exception e) {
			log.error("Error encrypting file data.", e);
			throw new RuntimeException(e);
		}
	}	
}
