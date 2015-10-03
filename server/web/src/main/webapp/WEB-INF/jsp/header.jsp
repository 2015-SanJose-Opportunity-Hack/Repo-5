<%@page import="com.hackathon.common.form.AgentUserForm"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>


<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	AgentUserForm agentUserForm = (AgentUserForm) session.getAttribute("agentUserForm");	
%>
<c:url var="actionUrl" value='/home' />
<c:url var="creditUrl" value='/credit' />
<c:url var="shoppingCartUrl" value='/shoppingCart' />
<c:url var="logoutUrl" value='/logout' />
<c:url var="userOrderHistoryUrl" value='/userOrderHistory' />
<c:url var="adminOrderHistoryUrl" value='/adminOrderHistory' />
<!-- header -->
<header>
	<form method="GET" action="${actionUrl}" id="form01">
		<div class="header-logo" onclick="pageSubmit('form01')"></div>
	</form>
	<a href="home"> </a>
	<div>
		<a href="${shoppingCartUrl}">
			<div class="header-cart-count" id="cartCount">
				
			</div>
			<div class="header-cart">
				<img class="header-cart-img"
					src="/resources/image/icons/cart-icon.png">
			</div>
			<div class="header-cart-label"></div>
		</a>
	</div>

	<div class="header-signin">
		<div style="float: left">
			<div class="name-display">
				Hi,
				<%=agentUserForm.getName()%></div>
			<div class="balance-display">
				Balance: <span id="userBalance"><fmt:setLocale value="en_US" />
					<fmt:formatNumber value="0.0"
						type="currency" /> </span>
			</div>
		</div>
		&nbsp;&nbsp;
		<div style="float: right;">
			<a href="${logoutUrl}" style="font-size: 12px">Logout</a>
		</div>
	</div>
</header>
 
<div id="menu">
	<ul>
\
	</ul>
	<div class="search-box">
		<img class="search-icon" src="/resources/image/icons/search-icon.png"
			width="35" height="35" alt="search icon" style="width: 35px;height: 35px;" /> <input id ="searchInput" name="search"
			type="text" placeholder="Search" /> <img class="search-close"
			src="/resources/image/icons/search-close-icon.png" width="35"
			height="35" alt="search icon" style="width: 35px;height: 35px;"/>
	</div>
</div>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->


<!-- include all compiled plugins (below), or include individual files as needed -->
<!-- <script src="/resources/js/main.js"></script>
<script src="/resources/js/store-util.js"></script> -->


<link href="/resources/css/main.css" rel="stylesheet" media="screen">


