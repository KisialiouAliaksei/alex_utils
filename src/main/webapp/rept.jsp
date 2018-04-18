<%@ page import="by.gsu.epamlab.controllers.Constants" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="title" value="report"/>

<%@include file="header.jsp"%>

<div align="center">
    <table class="features-table" align="center">
    <c:if test="${not empty user and (user.role != 'COURIER')}">
        <jsp:forward page="/main" />
    </c:if>
    <form name="formDayReport" action="<c:url value='/report'/>" method="post">
        <caption><h1> DATA : ${date}</h1><br>
            <a class="button7" href="javascript:makeOrder('${date}')" >make a report</a>&nbsp;&nbsp;&nbsp;
            <input class="button15" type="submit" name="<%=ConstantsJSP.KEY_PAID%>" value="paid">

        </caption>
        <tr>
            <th>Id ticket</th>
            <th>Client</th>
            <th>Seat</th>
            <th>Premiere</th>
            <th>Price(category)</th>
            <th>Status</th>
            <th>Select</th>
        </tr>

    <c:forEach var="ticket" items="${ticketsRept}">
        <tr>
            <td class="grey">${ticket.id}</td>
            <td class="green">${ticket.name}</td>
            <td class="grey">${ticket.number}</td>
            <td class="green">${ticket.premiere}</td>
            <td class="grey">${ticket.price}(${ticket.category})</td>
            <td class="green">${ticket.paid}</td>
            <td class="grey">
                <c:if test="${ticket.paid == 'NOT_PAID'}">
                    <input type="checkbox" name="<%=ConstantsJSP.KEY_TICKETS_PAID%>" value="${ticket.id}">
                </c:if>
                </td>
        </tr>
    </c:forEach>
        <input type="hidden" name="<%=Constants.KEY_DATE_FOR_REPT%>" value=""><BR>
    </table>
</div>
<%@include file="footer.jsp"%>
