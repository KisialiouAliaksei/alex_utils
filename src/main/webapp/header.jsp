<%--
  Created by IntelliJ IDEA.
  User: alex
  Date: 11.06.2016
  Time: 17:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="by.gsu.epamlab.controllers.ConstantsJSP" %>
<%@ page import="by.gsu.epamlab.controllers.Constants" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF8">

    <title><c:out value="${title}"/></title></head>

<style type="text/css">
    body{
        background-color: rgb(218, 218, 218)
    }
    a {
        color: #b81924;

    }

    .s1 a {
        -moz-transition: color 0.2s 0.02s ease;
        -o-transition: color 0.2s 0.02s ease;
        -webkit-transition: color 0.2s 0.02s ease;
        color: #13b82a;

    }

    .s1 a:hover {
        color: #ff1a38;
    }
    .s2 a {
        -moz-transition: color 0.2s 0.02s ease;
        -o-transition: color 0.2s 0.02s ease;
        -webkit-transition: color 0.2s 0.02s ease;
        color: #d28132;
    }

    .s2 a:hover {
        color: #1f31b8;
    }

    #header {
        position: static;
         top: px;
        padding: 10px;
        background: #39b54a;
        color: #fff;
        width: 100%;
    }
    .button7 {
        font-weight: 700;
        color: white;
        text-decoration: none;
        padding: .4em;
        border-radius: 3px;
        background: rgb(64,199,129);
        box-shadow: 0 -3px rgb(53,167,110) inset;
        transition: 0.2s;
    }
    .button7:hover { background: rgb(53, 167, 110); }
    .button7:active {
        background: rgb(33,147,90);
        box-shadow: 0 3px rgb(33,147,90) inset;
    }
    .button8 {
        font-weight: 700;
        color: white;
        text-decoration: none;
        padding: .4em;
        border-radius: 3px;
        background: rgb(199, 8, 72);
        box-shadow: 0 -3px rgb(167, 21, 38) inset;
        transition: 0.2s;
    }
    .button7:hover { background: rgb(53, 167, 110); }
    .button7:active {
        background: rgb(33,147,90);
        box-shadow: 0 3px rgb(33,147,90) inset;
    }

    a.button25:active {
        top: .1em;
        left: .1em;
        box-shadow: 0 0 0 60px rgba(0,0,0,.05) inset;
    }

    .features-table
    {
        width: 50%;
        margin: 0 auto;
        border-collapse: separate;
        border-spacing: 0;
        border: 0;
        text-shadow: 0 1px 0 #fff;
        color: #2a2a2a;
        background: #fafafa;
        background-image: -moz-linear-gradient(top, #fff, #eaeaea, #fff); /* Firefox 3.6 */
        background-image: -webkit-gradient(linear,center bottom,center top,from(#fff),color-stop(0.5, #eaeaea),to(#fff));
        margin-top:20px;
        margin-bottom:20px;
    }

    .features-table td
    {
        height: 50px;
        padding: 0 20px;
        border-bottom: 1px solid #cdcdcd;
        box-shadow: 0 1px 0 white;
        -moz-box-shadow: 0 1px 0 white;
        -webkit-box-shadow: 0 1px 0 white;
        text-align: center;
        vertical-align: middle;
        display: table-cell;
    }

    .features-table tbody td
    {
        text-align: center;
        width: 150px;
    }


    .features-table td.grey
    {
        background: #efefef;
        background: rgba(144,144,144,0.15);
        border-right: 1px solid white;
    }

    .features-table td.green
    {
        background: #e7f3d4;
        background: rgba(184,243,85,0.3);
    }

    .features-table td:nowrap
    {
        white-space: nowrap;
    }

    .features-table  th
    {
        font-size: 120%;
        font-weight: bold;
        -moz-border-radius-topright: 10px;
        -moz-border-radius-topleft: 10px;
        border-top-right-radius: 10px;
        border-top-left-radius: 10px;
        border-top: 1px solid #eaeaea;
    }

    .features-table tfoot td
    {
        font-size: 120%;
        font-weight: bold;
        -moz-border-radius-bottomright: 10px;
        -moz-border-radius-bottomleft: 10px;
        border-bottom-right-radius: 10px;
        border-bottom-left-radius: 10px;
        border-bottom: 1px solid #dadada;
    }
    .greatGr_btn {
        background: linear-gradient(to bottom, #0bc408 0%,#09a206 100%);
        color: #fff;
        font-size: 16px;
        text-shadow: 0 1px 0 #757575;
        padding: 4px 0 5px 0;
        margin: 0;
        cursor: pointer;
        border: 0;
        border-top: 1px solid #87c286;
        border-right: 1px solid #0e780c;
        border-left: 1px solid #0e780c;
        border-bottom: 1px solid #0e780c;
        box-shadow: 0 -1px 0 #0e780c, 0 1px 0 #fff;
        width: 150px;
        border-radius: 2px;
    }
    .button15 {
        font-weight: 700;
        color: white;
        text-decoration: none;
        padding: .4em;
        border-radius: 3px;
        background: rgb(199, 8, 72);
        box-shadow: 0 -3px rgb(167, 21, 38) inset;
        transition: 0.2s;
    }
    a.button11 {
        position: relative;
        z-index: 1;
        color: #ffffff;
        font-size: 135%;
        font-weight: 700;
        text-decoration: none;
        padding: .25em .5em;
    }
    a.button11:after {
        content: "Cart";
        position: absolute;
        z-index: -1;
        top: -2px;
        bottom: -2px;
        left: -2px;
        width: calc(100% + 6*(1em*90/135) - 2px*2*2);
        text-align: right;
        color: #fff;
        font-size: 90%;
        padding: .25em .5em;
        border-radius: 5px;
        border: 2px solid #a5b5b5;

        transform: skewX(-10deg);
        background: linear-gradient(#121212, #a5b5b5) no-repeat 100% 0;
        background-size: calc(6*(1em*90/135) + .5em) 100%;
        box-shadow: inset calc(-6*(1em*90/135) - .5em) 0 rgba(255,255,255,0);
        transition: .3s;
    }
    a.button11:hover:after {
        box-shadow: inset calc(-6*(1em*90/135) - .5em) 0 rgba(255,255,255,.2);
    }
    a.button11:active:after {
        background-image: linear-gradient(#c61e40, #d4536d);
    }
    a.knopka {
        color: #fff;
        text-decoration: none;
        user-select: none;
        background: rgb(212,75,56);
        padding: .2em 0.2em;
        outline: none;
    }
    a.knopka:hover { background: rgb(232,95,76); }
    a.knopka:active { background: rgb(152,15,0); }

    #cart{
        width:150px;
        height:50px;
        float:right;
        clear:right;
    }
    .button16 {
        display: inline-block;
        font-family: arial,sans-serif;
        font-size: 11px;
        font-weight: bold;
        color: rgb(68,68,68);
        text-decoration: none;
        user-select: none;
        padding: .2em 1.2em;
        outline: none;
        border: 1px solid rgba(0,0,0,.1);
        border-radius: 2px;
        background: rgb(245,245,245) linear-gradient(#f4f4f4, #f1f1f1);
        transition: all .218s ease 0s;
    }
    .button16:hover {
        color: rgb(24,24,24);
        border: 1px solid rgb(198,198,198);
        background: #f7f7f7 linear-gradient(#f7f7f7, #f1f1f1);
        box-shadow: 0 1px 2px rgba(0,0,0,.1);
    }
    .button16:active {
        color: rgb(51,51,51);
        border: 1px solid rgb(204,204,204);
        background: rgb(238,238,238) linear-gradient(rgb(238,238,238), rgb(224,224,224));
        box-shadow: 0 1px 2px rgba(0,0,0,.1) inset;
    }
    a.button9 {
        position: relative;
        display: inline-block;
        color: #121212;
        font-weight: bold;
        text-decoration: none;
        text-shadow: rgba(255,255,255,.5) 1px 1px, rgba(100,100,100,.3) 3px 7px 3px;
        user-select: none;
        padding: 1em 2em;
        outline: none;
        border-radius: 3px / 100%;
        background-image:
                linear-gradient(45deg, rgba(255,255,255,.0) 30%, rgba(255,255,255,.8), rgba(255,255,255,.0) 70%),
                linear-gradient(to right, rgba(255,255,255,1), rgba(255,255,255,0) 20%, rgba(255,255,255,0) 90%, rgba(255,255,255,.3)),
                linear-gradient(to right, rgba(125,125,125,1), rgba(255,255,255,.9) 45%, rgba(125,125,125,.5)),
                linear-gradient(to right, rgba(125,125,125,1), rgba(255,255,255,.9) 45%, rgba(125,125,125,.5)),
                linear-gradient(to right, rgba(223,190,170,1), rgba(255,255,255,.9) 45%, rgba(223,190,170,.5)),
                linear-gradient(to right, rgba(223,190,170,1), rgba(255,255,255,.9) 45%, rgba(223,190,170,.5));
        background-repeat: no-repeat;
        background-size: 200% 100%, auto, 100% 2px, 100% 2px, 100% 1px, 100% 1px;
        background-position: 200% 0, 0 0, 0 0, 0 100%, 0 4px, 0 calc(100% - 4px);
        box-shadow: rgba(0,0,0,.5) 3px 10px 10px -10px;
    }
    a.button9:hover {
        transition: .5s linear;
        background-position: -200% 0, 0 0, 0 0, 0 100%, 0 4px, 0 calc(100% - 4px);
    }
    a.button9:active {
        top: 1px;
    }
    .button10 {
        display: inline-block;
        color: black;
        font-size: 125%;
        font-weight: 700;
        text-decoration: none;
        user-select: none;
        padding: .25em .5em;
        outline: none;
        border: 1px solid rgba(128, 128, 128, 0.44);
        border-radius: 7px;
        background: rgba(128, 128, 128, 0.44) linear-gradient(rgba(128, 128, 128, 0.44), rgba(128, 128, 128, 0.44));
        box-shadow: inset 0 -2px 1px rgba(0,0,0,0), inset 0 1px 2px rgba(0,0,0,0), inset 0 0 0 60px rgba(255,255,0,0);
        transition: box-shadow .2s, border-color .2s;
    }
    .button10:hover {
        box-shadow: inset 0 -1px 1px rgba(0,0,0,0), inset 0 1px 2px rgba(0,0,0,0), inset 0 0 0 60px rgba(255,255,0,.5);
    }
    .button10:active {
        padding: calc(.25em + 1px) .5em calc(.25em - 1px);
        border-color: rgba(177,159,0,1);
        box-shadow: inset 0 -1px 1px rgba(0,0,0,.1), inset 0 1px 2px rgba(0,0,0,.3), inset 0 0 0 60px rgba(255,255,0,.45);
    }
    a.button28 {
        position: relative;
        display: inline-block;
        font-size: 90%;
        font-weight: 700;
        color: rgb(209,209,217);
        text-decoration: none;
        text-shadow: 0 -1px 2px rgba(0,0,0,.2);
        padding: .5em 1em;
        outline: none;
        border-radius: 3px;
        background: linear-gradient(rgb(110,112,120), rgb(81,81,86)) rgb(110,112,120);
        box-shadow:
                0 1px rgba(255,255,255,.2) inset,
                0 3px 5px rgba(0,1,6,.5),
                0 0 1px 1px rgba(0,1,6,.2);
        transition: .2s ease-in-out;
    }
    a.button28:hover:not(:active) {
        background: linear-gradient(rgb(126,126,134), rgb(70,71,76)) rgb(126,126,134);
    }
    a.button28:active {
        top: 1px;
        background: linear-gradient(rgb(76,77,82), rgb(56,57,62)) rgb(76,77,82);
        box-shadow:
                0 0 1px rgba(0,0,0,.5) inset,
                0 2px 3px rgba(0,0,0,.5) inset,
                0 1px 1px rgba(255,255,255,.1);

    }

</style>
<SCRIPT Language="JavaScript">
    function show(state){
        document.getElementById('window').style.display = state;
        document.getElementById('wrap').style.display = state;
    }
    function showPremieresInfo(state, header, genre, author, description){
        document.getElementById('window-header').innerHTML='Premiere : ' + header;
        document.getElementById('window-genre').innerHTML='Genre : ' + genre;
        document.getElementById('window-author').innerHTML='Author : ' + author;
        document.getElementById('window-description').innerHTML='Description : ' + description;
        document.getElementById('window').style.display = state;
        document.getElementById('wrap').style.display = state;
    }

    function makeOrder(date){
        document.formDayReport.dateRept.value=date;
        document.formDayReport.submit();
    }
    function sendFormR(date, name) {
        document.premiereForm.date.value=date;
        document.premiereForm.premiere.value=name;
        document.premiereForm.submit();
    }
    function sendForm(sRef, category, price) {
        document.buyForm.seat.value=sRef;
        document.buyForm.categorySeat.value=category;
        document.buyForm.price.value=price;
        document.buyForm.submit();
    }

    function sendFormSub() {
        document.reportForm.submit();
    }
    function clearCart(){
        document.purchasesForm.clear.value = 'clear'
        document.purchasesForm.submit();
    }

    function validate_form ( )
    {
        if ( document.purchasesForm.purchase.checked == false )
        {
            valid = true;
            alert ( "No tickets select" );
            valid = false;
        }
        return valid;
    }
</SCRIPT>

<body >
    <div id="header">
        <c:if test="${not empty errorMessage}">
            <c:out value="${errorMessage}"/>
        <hr>
        </c:if >
        <c:choose>
            <c:when test="${not empty user and user.role != 'GUEST'}">
                <h3>Hello, ${user.role} "${user.login}"</h3>
                <div id="logout">
                    </div>
                <br>
                <c:choose>
                    <c:when test="${user.role == 'ADMIN'}">
                        <hr>
                        <a class="button16" href="<c:url value='/registration'/>">registration courier</a>
                        <hr>
                    </c:when>
                </c:choose>
                <c:if test="${not empty user and user.role == 'USER'}">
                    <div id="cart">
                        <a id="" class="button11" href="<c:url value='/cart.jsp'/>">${fn:length(tickets)}</a><br>
                    </div>
                </c:if >
                <a class="button16" type="button" href="<c:url value='/logout'/>">logout</a>
            </c:when>
            <c:otherwise >

                <form name="loginForm" method="POST" action="<c:url value='/login'/>">
                    <h3>Hello, ${user.role} </h3>
                    <br>
                    Login: <input type="text" name='<%= ConstantsJSP.KEY_LOGIN %>' value="">
                    Password: <input type="password" name='<%= ConstantsJSP.KEY_PASSWORD %>' value="">&nbsp;
                    <input class="button16" type="submit" name='<%=ConstantsJSP.KEY_SUBMIT_PRESSED%>' value="Login">
                    <br>
                    <a class="button16" href="<c:url value='/registration'/>">Registration</a>
                </form>
            </c:otherwise>
        </c:choose>
        <br>
        <a class="button16" href="<c:url value='/main'/>">MainPage</a>&nbsp;&nbsp;&nbsp;
        <c:choose>
            <c:when test="${user.role == 'COURIER'}">
                <hr>
                <form name="reportForm" method="get" action="<c:url value='/report'/>">
                    choose data and go to courier room  :&nbsp;&nbsp;
                    <select name="<%=Constants.KEY_DATE_FOR_COURIER %>" size="1">
                        <c:forEach var="premiere" items="${premieres}" varStatus="status">
                            <c:forEach var="date" items="${premiere.dates}" varStatus="status">
                                <option  value="${date}">${date}</option>
                            </c:forEach>
                        </c:forEach>
                    </select>
                    <a class="button16" href="javascript:sendFormSub()" >courier_room</a>

                </form>
                <hr>
            </c:when>
        </c:choose>
    </div>






