
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="title" value="orders"/>

<%@include file="header.jsp"%>
<c:if test="${not empty user and (user.role != 'USER')}">
  <jsp:forward page="/main" />
</c:if>
<table class="features-table" align="center">

<form name="purchasesForm" method="post" action="<c:url value='/cart'/>" onsubmit="return validate_form ( );">
  <caption>    <input class="button7" type="submit" name="<%=ConstantsJSP.KEY_SUBMIT_ORDERS%>" value="BUY_NOW"> &nbsp;&nbsp;&nbsp;
    <a class="button15" href="javascript:clearCart()">clear</a>
    <br>

  </caption>

  <tr><th>Seat</th><th>Premiere</th><th>Date</th><th>Price</th><th>Select to confirm
  </th></tr>
  <c:forEach var="ticket" items="${tickets}">
      <tr>
        <td class="grey">${ticket.number}</td>
        <td class="green">${ticket.premiere}</td>
        <td class="grey">${ticket.date}</td>
        <td class="green">${ticket.price} BYR(${ticket.category})</td>
        <td class="grey">  <input type="checkbox" name="<%=ConstantsJSP.KEY_PURCHASES%>" value="${ticket}"></td>
      </tr>
  </c:forEach>
    </br>
  <input type="hidden" name="<%=ConstantsJSP.KEY_CLEAR%>" value="">
</form>
</table>


<%@include file="footer.jsp"%>
