package com.paypal.psc.rs.controllers;

import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.WebUtils;

import com.paypal.psc.ebaysso.SsoAuthenticatedUserDetail;
import com.paypal.psc.ebaysso.SsoAuthenticationToken;
import com.paypal.psc.rs.common.crypto.EncryptedPropertyPlaceholderConfigurer;
import com.paypal.psc.rs.common.form.AgentUserForm;
import com.paypal.psc.rs.common.form.ItemForm;
import com.paypal.psc.rs.dao.UserDAO;
import com.paypal.psc.rs.service.AppConfigService;
import com.paypal.psc.rs.service.ItemService;
import com.paypal.psc.rs.service.ShoppingCartService;
import com.paypal.psc.rs.service.UserService;

@Controller
public class UserHomeController {
	private static final Logger LOGGER = Logger
			.getLogger(UserHomeController.class);

	@Autowired
	private AppConfigService appConfigService;

	@Autowired
	private UserService userService;

	@Autowired
	private ShoppingCartService shoppingCartService;

	@Autowired
	private ItemService itemService;

	@Autowired
	private EncryptedPropertyPlaceholderConfigurer cryptoLibrary;

	@RequestMapping(method = RequestMethod.GET, value = "/home")
	public String externalHome(Model model, Principal principal,
			HttpServletRequest httpServletRequest) {
		return processHome(model, principal, httpServletRequest);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/home")
	public String externalPostHome(Model model, Principal principal,
			HttpServletRequest httpServletRequest) {
		return processHome(model, principal, httpServletRequest);
	}

	private String processHome(Model model, Principal principal,
			HttpServletRequest httpServletRequest) {
		LOGGER.info("*****user home page " + httpServletRequest.getRequestURI());
		SsoAuthenticationToken user = (SsoAuthenticationToken) principal;
		SsoAuthenticatedUserDetail userDetails = ((SsoAuthenticatedUserDetail) user
				.getPrincipal());
		AgentUserForm agentUserForm = new AgentUserForm();
//		if (StringUtils.isBlank(userDetails.getFirstName())) {
//			agentUserForm.setFirstName(userDetails.getAddnlAttrib("first"));
//		} else {
//			agentUserForm.setFirstName(userDetails.getFirstName());
//		}
//		if (StringUtils.isBlank(userDetails.getLastName())) {
//			agentUserForm.setLastName(userDetails.getAddnlAttrib("last"));
//		} else {
//			agentUserForm.setLastName(userDetails.getLastName());
//		}
		agentUserForm.setDomain(userDetails.getAddnlAttrib("Domain"));
		agentUserForm.setManagerName(userDetails.getManagerName());
		agentUserForm.setEmailAddress(userDetails.getEmail());
		String loginUid = userDetails.getUsername();
		agentUserForm.setUserName(loginUid);
		LOGGER.info("*****user id:  " + loginUid);
		// Long userId = userService.getUserId(loginUid);
		Long userId = userService.getIdByLoginId(loginUid);
		if (userId == 0) {
			model.addAttribute("toolAccessDl",
					appConfigService.getDeniedProperty());
			return "denied";
		}

		if (userService.findUserById(userId).getActive() == 0) {
			model.addAttribute("toolAccessDl",
					appConfigService.getDeniedProperty());
			return "denied";
		}
		String name;
		if (StringUtils.isBlank(cryptoLibrary.convertPropertyValue(userService
				.findUserById(userId).getName()))) {
			if (StringUtils.isBlank(userDetails.getFirstName())) {
				agentUserForm.setFirstName(userDetails.getAddnlAttrib("first"));
				name = userDetails.getAddnlAttrib("first");
			} else {
				agentUserForm.setFirstName(userDetails.getFirstName());
				name = userDetails.getFirstName();
			}
			if (StringUtils.isBlank(userDetails.getLastName())) {
				agentUserForm.setLastName(userDetails.getAddnlAttrib("last"));
				name = name + " " + userDetails.getAddnlAttrib("last");
			} else {
				agentUserForm.setLastName(userDetails.getLastName());
				name = name + " " + userDetails.getLastName();
			}
			userService.updateName(userId, name);
		} else {
			name = cryptoLibrary.convertPropertyValue(userService.findUserById(
					userId).getName());
		}
		agentUserForm.setName(name);
		agentUserForm.setUserId(userId);
		agentUserForm.setRoleId(userService.getUserRole(userId));
		agentUserForm.setCreditBalance(userService.getCreditBalance(userId));
		agentUserForm.setCartCount((int) (long) shoppingCartService
				.getCartCount(userId));

		HttpSession httpSession = httpServletRequest.getSession(true);
		httpSession.setAttribute("agentUserForm", agentUserForm);
		LOGGER.info(appConfigService.getDeniedProperty());

		// List<String> itemCodes = itemService.getUniqueItemCodes();
		String defaultParameter = "name";
		String defaultOrder = "asc";
		List<String> itemCodes = itemService.getItemCodesInOrder(
				defaultParameter, defaultOrder);
		httpSession.setAttribute("itemCodes", itemCodes);
		model.addAttribute("itemCodes", itemCodes);

		Map<String, ItemForm> itemFormMap = itemService.getStoreItems(userId);
		httpSession.setAttribute("itemFormMap", itemFormMap);
		model.addAttribute("itemFormMap", itemFormMap);
		model.addAttribute("view", "listView");

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

	@RequestMapping
	public String sortItems(
			@RequestParam(value = "sortBy", required = true) String sortBy,
			@RequestParam(value = "view", required = true) String view,
			Model model, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		HttpSession httpSession = httpServletRequest.getSession(false);
		if (httpSession.getAttribute("agentUserForm") == null) {
			return "redirect:home";
		}
		List<String> itemCodes = null;
		StringTokenizer stringTokenizer = new StringTokenizer(sortBy, "-");
		if (stringTokenizer != null) {
			String parameter = stringTokenizer.nextToken();
			String order = stringTokenizer.nextToken();
			itemCodes = itemService.getItemCodesInOrder(parameter, order);
		}

		httpSession.setAttribute("itemCodes", itemCodes);
		model.addAttribute("itemCodes", itemCodes);
		model.addAttribute("sortBy", sortBy);
		model.addAttribute("view", view);
		return "home";
	}

	private void removeCookie(HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		Cookie cookie = WebUtils.getCookie(httpServletRequest, "JSESSIONID");

		if (cookie != null) {
			cookie.setMaxAge(0);
			httpServletResponse.addCookie(cookie);
		}
	}
}
