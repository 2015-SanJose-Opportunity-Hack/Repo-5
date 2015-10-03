package com.hackathon.common;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.bouncycastle.util.encoders.Base64;
import org.json.JSONException;
import org.json.JSONObject;


public class ApplicationUtil {

	private static Logger logger = Logger.getLogger(ApplicationUtil.class);
	
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	private static String MD5_HASH_SALT = "NVfVHaeu4APhMsSq";
	public static List<String> prepareElements(String value) {
		List<String> list = new ArrayList<String>();
		if(StringUtils.isBlank(value)) {
			return list;
		}
		StringTokenizer token = new StringTokenizer(value, ",");
		while(token.hasMoreTokens()) {
			String str = token.nextToken();
			if(StringUtils.isBlank(str)) {
				continue;
			}
			list.add(str.trim());
		}
		return list;
	}
	
	public static JSONObject getSFTPCredentials(String value, String key) throws JSONException {
		
		JSONObject json = new JSONObject (value);
		return json.getJSONObject(key);			 
	}
	
	public static String getExceptionTrace(Exception e) {
		if(e == null) {
			return "";
		}
		StringWriter sw = new StringWriter();
  	  	PrintWriter p = new PrintWriter(sw);
  	  	e.printStackTrace(p);  	  	
  	  	return sw.toString();
	}
	
	public static String getMD5Hex(String plainText) throws NoSuchAlgorithmException {
		if(StringUtils.isBlank(plainText)) {
			return null;
		}
		MessageDigest digest = MessageDigest.getInstance("MD5");
		digest.update((plainText.toUpperCase() + MD5_HASH_SALT).getBytes());
		byte[] hash = digest.digest();
		String md5HashAsHex = new BigInteger(1,hash).toString(16);
		return md5HashAsHex;
	}	
	
	public static String formatDate(String dateStr) {
		
		try {
			//parse data in format 1956-03-06T00:00:00.000Z
			Date date = dateFormat.parse(dateStr);
			return dateFormat.format(date) + " (yyyy-mm-dd)";
		} catch (ParseException e) {
			logger.error(getExceptionTrace(e));
		}
		return "";
	}
	
	public static Date getDate(String dateStr) {
		if(StringUtils.isBlank(dateStr)) {
			return new Date();
		}
		try {
			//parse data in format 1956-03-06T00:00:00.000Z
			Date date = dateFormat.parse(dateStr);
			return date;
		} catch (ParseException e) {
			logger.error(getExceptionTrace(e));
		}
		return new Date();
	}
	
	public static String encode(String s){
		byte[] bytes = s.getBytes();
		byte[] encodedBytes = Base64.encode(bytes);

		String encodedString;
		try {
			encodedString = URLEncoder.encode(new String(encodedBytes), "UTF-8");
			return encodedString;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	public static String decode(String encodedString){
		
		String decodedString;
		try {
			decodedString = URLDecoder.decode(encodedString, "UTF-8");
			byte[] bytes = decodedString.getBytes();
			byte[] decodedBytes = Base64.decode(bytes);
			decodedString = new String(decodedBytes);
			return decodedString;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
