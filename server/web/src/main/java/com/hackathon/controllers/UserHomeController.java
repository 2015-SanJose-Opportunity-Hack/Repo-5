package com.hackathon.controllers;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.WebUtils;

import com.hackathon.common.form.BusinessForm;
import com.hackathon.common.form.UserForm;
import com.hackathon.service.BusinessService;
import com.hackathon.service.UserService;

@Controller
public class UserHomeController {
	private static final Logger LOGGER = Logger
			.getLogger(UserHomeController.class);

	@Autowired
	private UserService userService;
	
	@Autowired
	private BusinessService businessService;
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/home")
	public String externalHome( 
			HttpServletRequest httpServletRequest) {
		return processHome(  httpServletRequest);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/home")
	public String externalPostHome( 
			HttpServletRequest httpServletRequest) {
		return processHome( httpServletRequest);
	}

	private String processHome( 
			HttpServletRequest httpServletRequest) {
		UserForm userForm = userService.getUserById(1); // Hard coded for Chandra.
		BusinessForm businessForm = businessService.getAllBusinesses();
		httpServletRequest.getSession().setAttribute("userForm", userForm);
		httpServletRequest.getSession().setAttribute("businessForm", businessForm);
		return "home";
	}

	@RequestMapping(method = RequestMethod.GET, value = "logout")
	public String logoutUser(Model model,
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		HttpSession session = httpServletRequest.getSession(false);
		if (session != null) {
			session.invalidate();
			this.removeCookie(httpServletRequest, httpServletResponse);

		}
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("User logged out and session invalidated");
		}
		return "logout";
	}

	private void removeCookie(HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		Cookie cookie = WebUtils.getCookie(httpServletRequest, "JSESSIONID");

		if (cookie != null) {
			cookie.setMaxAge(0);
			httpServletResponse.addCookie(cookie);
		}
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/search")
	public String externalPostHome( String key,
			HttpServletRequest httpServletRequest) {
//		businessService.getBusinessInOrder(parameter, order)
		return "home";
		
	}
}
