<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: alex
  Date: 11.06.2016
  Time: 20:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="by.gsu.epamlab.controllers.ConstantsJSP" %>
<%@ page import="by.gsu.epamlab.controllers.Constants" %>
<c:set var="title" value="Registration"/>

<%@include file="header.jsp"%>

<c:if test="${not empty user and (user.role eq 'USER' or user.role eq 'COURIER')}">
    <jsp:forward page="/main" />
</c:if>
registration<br>

<form name="registrationForm" method="POST" action="<c:url value='<%=Constants.JUMP_REGISTRATION%>'/>">
    Login: <input type="text" name=<%= ConstantsJSP.KEY_LOGIN %> value="">
    <br>
    Password: <input type="password" name=<%= ConstantsJSP.KEY_PASSWORD %> value="">
    <br>
    Repeat Password: <input type="password" name=<%= ConstantsJSP.KEY_PASSWORD_2 %> value="">
    <br>
    Phone: <input type="text" name=<%= ConstantsJSP.KEY_PHONE %> value="">
    <input type="submit" name=<%=ConstantsJSP.KEY_SUBMIT_REGISTR%> value="Enter">
</form>

<%@include file="footer.jsp"%>
