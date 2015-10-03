<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:url var="actionUrl" value='/home' />
 <header>
            <form method="GET" action="/home" id="form01">

                
            </form><div class="header-logo" onclick="pageSubmit('form01')">
                    
                </div>
           
           <div style="float:right; margin-top:25px; margin-right:30px"><a href="${actionUrl}" style="font-size: 12px">Login</a></div>
 </header>