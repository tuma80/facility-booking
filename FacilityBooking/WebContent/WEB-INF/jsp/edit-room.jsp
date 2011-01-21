<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isELIgnored="false" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit Facility</title>
<jsp:include page="css_include.jsp"></jsp:include>
</head>
<body>
<jsp:include page="menu.jsp"></jsp:include>
<div id="content">
<div id="error">${error}</div>
<form:form modelAttribute="room" action="edit-room.do" method="POST">
<table width="67%" border="0" align="left">
  <tr>
    <td width="30%">Room Name</td>
    <td width="286"><label for="textfield"></label>
    <form:hidden path="roomId"/>
     <form:input path="roomName" size="45%" /><form:errors path="roomName"/></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td align="right"><input type="submit" name="button" id="button" value=" Save " />
      <input type="submit" value="Delete" onclick="this.form.action='delete-room.do';this.form.method='POST';this.form.submit();"/>
      <input type="submit" name="button2" id="button2" value="Cancel" onclick="this.form.action='list-room.do';this.form.method='GET';this.form.submit();"/></td>
  </tr>
</table>
</form:form>
</div>
</body>
</html>