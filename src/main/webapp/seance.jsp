<%@ page import="by.gsu.epamlab.controllers.Constants" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="header.jsp"%>
<div >
    <c:if test="${empty seance}">
        <c:redirect url='<%=Constants.JUMP_SEANCE%>' />
    </c:if>
<table class="features-table" align="center" cellpadding="10" cellspacing="0" border="4"  width="50">
    <caption><h1>Date : ${seance.date}</h1>
        <h2>Premiere : ${seance.premiere}</h2>
    </caption>

    <tr>
        <th class="grey">Category</th>
        <th class="grey">Price</th>
        <th class="green">The number of available seats</th>
    </tr>
    <c:forEach var="seats" items="${seance.seats}" varStatus="status">
        <tr>
            <td class="grey" align="center">${seats.category}</td>
            <td class="grey" align="center">${seats.price}</td>
            <td class="green" align="center">${seats.amount}</td>
        </tr>
    </c:forEach>
    <tfoot>
    </tfoot>
</table>
<br>

<c:choose>
<c:when test="${user.role == 'USER'}">
    <h1 align="center">BUY TICKETS</h1>
    <h2 align="center">choose seat</h2>
    <br>
   <form align="center" name="buyForm" action="<c:url value='/cart'/>" method="get">
       <c:forEach var="seat" items="${seance.seats}" varStatus="status">
           <fieldset id="${seat.category}" class="new1">
               <legend> ${seat.category}</legend>
            <c:forEach var="number" items="${seat.numberOfSeats}">
                <c:choose>
                    <c:when test="${number >= 1000}">
                        <a href="" class="button8">${number - 1000}</a>
                    </c:when>
                    <c:otherwise>
                        <a href="javascript:sendForm('${number}','${seat.category}', '${seat.price}')" class="button7">${number}</a>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
           </fieldset>
       </c:forEach>

       <input type="hidden" name="<%=ConstantsJSP.KEY_CATEGORY_SEAT%>" value="">
       <input type="hidden" name="<%=Constants.KEY_SEAT%>" value="">
       <input type="hidden" name="<%=Constants.KEY_PRICE%>" value="">
       <input type="hidden" name="<%=Constants.KEY_PREMIERE%>" value="${seance.premiere}">
       <input type="hidden" name="<%=Constants.KEY_DATE%>" value="${seance.date}">
   </form>
</c:when>
</c:choose>
</div>
<%@include file="footer.jsp"%>

