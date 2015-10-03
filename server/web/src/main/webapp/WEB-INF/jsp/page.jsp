<!DOCTYPE html>


<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<spring:htmlEscape defaultHtmlEscape="true" />
<spring:escapeBody htmlEscape="true" />

<%
	response.setHeader("X-FRAME-OPTIONS", "DENY");
%>
<html lang="en">
	
	<head>
		
		<meta charset="utf-8">
		<meta name="description" content="Incertive Merchandise store web portal for paypal customer service">
		<meta name="keywords" content="PayPal RewardStore" />		
		<title>Incentive Merchandise Store </title>
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
	  	<meta http-equiv="X-Frame-Options" content="deny" />
		<!-- bootstrap -->
		<link href="/resources/css/main.css" rel="stylesheet" media="screen">
		<script src="/resources/js/jquery-2.1.1.min.js"></script>
		<script type="text/javascript" src="/resources/js/jquery.tablesorter.min.js"></script>
  		<script src="/resources/js/main.js"></script>
  		<script src="/resources/js/store-util.js"></script>
		<link rel="shortcut icon" sizes="196x196" href="https://www.paypalobjects.com/webstatic/icon/pp196.png" />
		<link rel="shortcut icon" type="image/x-icon" href="https://www.paypalobjects.com/webstatic/icon/favicon.ico" />
		<link rel="icon" type="image/x-icon" href="https://www.paypalobjects.com/webstatic/icon/pp32.png" />
		
	</head>
<body >
		<tiles:insertAttribute name="header" />
		<tiles:insertAttribute name="content" />
		<tiles:insertAttribute name="footer" />
</body>
</html>