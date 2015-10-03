<%@page import="com.paypal.psc.rs.common.form.AgentUserForm"%>
<%@page import="com.paypal.psc.rs.common.form.UserForm"%>
<%@page import="com.paypal.psc.rs.common.Status"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:url var="adminUrl" value='/admin' />
	<c:url var="creditUrl" value='/credit' />
	<c:url var="editUserUrl" value='/editUser' />
	
	<% 	
		UserForm userForm = (UserForm) session.getAttribute("userForm");
		AgentUserForm agentUserForm = (AgentUserForm) session.getAttribute("agentUserForm");
	%>
<script>
	document.getElementById("ad").className = "nav-select";
	document.getElementById("access").className = "selected-page";
	
	$(document).ready(function() {
		$('.search-box').css('display', 'inline')
		var table =  $('#accessManagementTable').DataTable( {
	         "bLengthChange": false,
	         "iDisplayLength": 15,
	         "info": false,
	         "aoColumnDefs": [
	                          { 'bSortable': false, 'aTargets': [ 2,5,6 ] }
	                       ]
	   } );
		
		drawTable("accessManagementTable", table);
	} );
</script>
	
<div class="page-content-wrapper">
	<div class="page-title">
		<div class="table-heading">Access Management</div>
		<div class="add-icon">
		<input type="button" class="btn-default-wide" style=" height: 30px; font-size: 12px; margin: 0px; margin-top: -2px;" value="Add a new user"
				onclick="window.location='${editUserUrl}?newUser=true'"> 
			
		</div>
	</div>

	<div id="errors" class="errors"><%=(userForm.getErrorMessage()!=null)? userForm.getErrorMessage() : "" %></div>
	
	<div id="success" class="success"><%=(userForm.getSuccessMessage()!=null)? userForm.getSuccessMessage() : "" %></div>
	
	<table id="accessManagementTable" class="table-body">

		<thead>
			<tr>
				<th>Login ID</th>
				<th>Name</th>
				<th>Location</th>
				<th>Role</th>
				<th>Manager</th>
				<th>Status</th>
				<th>Action</th>
			</tr>
		</thead>
		<tbody>
		<%
		if(userForm != null){
			int len = userForm.getLoginId().length;
			for(int i =0; i<len; i++){
				if(userForm.getLoginId()[i].equalsIgnoreCase(agentUserForm.getUserName())) {
					continue;
				}				
		%>
		
			<tr id="<%=i%>">
				<td><%=userForm.getLoginId()[i]%></td>
				<td><%=userForm.getName()[i]%></td>
				<td><%=userForm.getLocation()[i]%></td>
				<td><%=userForm.getRole()[i]%></td>
				<td><%=userForm.getManagerName()[i]%></td>
				<td id="status<%=i%>"><%= (userForm.getActive()[i] == 1) ? Status.Active.getKey() : Status.Inactive.getKey() %></td>
				<td><a href="${editUserUrl}?newUser=false&id=<%= userForm.getId()[i]%> "><img src="/resources/image/icons/update.png"
						title="Edit record" class="edit-icon"></a>
						|
				 <a href="#"
					onclick="deleteUser(<%=i%>, <%=userForm.getId()[i]%>)"><img
						src="/resources/image/icons/close_icon.png" title="Make inactive"
						class="trash-icon" id="disable-user<%=i%>" <%if(userForm.getActive()[i] == 1) {%>style="display:inline;" <%}else{ %>style="display:none;" <%} %>></a>
					<a href="#"
					onclick="enableUser(<%=i%>, <%=userForm.getId()[i]%>)"><img
						src="/resources/image/icons/check-icon.png" title="Make active"
						id="enable-user<%=i%>" <%if(userForm.getActive()[i] == 0) {%>style="display:inline;" <%}else{ %>style="display:none;" <%} %>></a>		
				</td>
						
			</tr>
		<%}
		} %>
		</tbody>
	</table>

</div>
