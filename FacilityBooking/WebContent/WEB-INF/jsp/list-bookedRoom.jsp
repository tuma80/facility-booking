<%@page import="org.springframework.security.core.userdetails.UserDetails"%>
<%@page import="org.springframework.security.core.context.SecurityContextHolder"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>Room Listing</title>
<link rel="stylesheet"  type="text/css" href="<%=request.getContextPath()%>/css/displaytag.css"/>
<jsp:include page="css_include.jsp"></jsp:include>
</head>
<body>
<script>
function reloadPage(){
	var form = document.forms[0];
	form.method="POST";
	form.action="list-bookedRoom.do?id="+form.roomId.value+"&selDate="+form.selDate.value;
	form.submit();
}
function undoBooking(bookingId){
	var form = document.forms[0];
	form.method="POST";
	form.action= "undo-bookedRoom.do?id="+bookingId+"&roomId="+form.roomId.value+"&selDate="+form.selDate.value;
	form.submit();	
}
</script>
<div id="content">
<form:form modelAttribute="bookedRoomForm" action="book-room.do">
<jsp:include page="menu.jsp"></jsp:include>
		<div id="add">${bookedRoomForm.roomName}</div>
		<form:hidden path="roomId" />
		<form:select path="selDate" items="${bookedRoomForm.dateAvails}" onchange="reloadPage()"/>
     	 <display:table name="bookedRoomList" requestURI="/list-bookedRoom.do" 
      				id="bookedRoom" uid="bookedRoom"  excludedParams="*"    
      				class="displaytag" cellpadding="0" style="width:800px" pagesize="20">
           		<display:column title="From" property="fromTime" />
           		<display:column title="To" property="toTime" />
				<display:column title="Select to Book">
				<c:choose>
				<c:when test='${bookedRoom.booked == false}'>
					<input type = "checkbox" name = "trIds" value = "${bookedRoom.trId}"></input>
				</c:when>
				<c:otherwise>
					Not Availabled
				</c:otherwise>
				</c:choose>
				</display:column>           		
           		<display:column title="Booked By" property="userName" />
           		<display:column>
           		<c:if test="${bookedRoom.owner == true}">
           			<input type="button" value="Undo" onclick="undoBooking(${bookedRoom.bookingId})">
           		</c:if>
           		</display:column>
           </display:table>
           <input type="submit" value="Book" />
</div>
</form:form>
</body>
</html>