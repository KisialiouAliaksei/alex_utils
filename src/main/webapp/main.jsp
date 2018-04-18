<%@ page import="by.gsu.epamlab.controllers.Constants" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="title" value="MainPage"/>

<%@include file="header.jsp"%>
<div class="f">


<style type="text/css">


    #wrap{
        display: none;
        opacity: 0.8;
        position: fixed;
        left: 0;
        right: 0;
        top: 0;
        bottom: 0;
        padding: 16px;
        background-color: rgba(1, 1, 1, 0.725);
        z-index: 100;
        overflow: auto;
    }

    #window{
        width: 400px;
        height: 400px;
        margin: 50px auto;
        display: none;
        background: #fff;
        z-index: 200;
        position: fixed;
        left: 0;
        right: 0;
        top: 0;
        bottom: 0;
        padding: 16px;
    }

    .close{
        margin-left: 364px;
        margin-top: 4px;
        cursor: pointer;
    }


</style>

<div onclick="show('none')" id="wrap"></div>
<div id="window">
    <img class="close" onclick="show('none')" src="http://sergey-oganesyan.ru/wp-content/uploads/2014/01/close.png">
    <h2 id="window-header"></h2>
    <h3 id="window-genre"></h3>
    <h3 id="window-author"></h3>
    <h4 id="window-description"></h4>

</div>



<div align="center">
<h1 >Premieres</h1>



<form  name="premiereForm" action="<c:url value='<%=Constants.JUMP_SEANCE%>'/>" method="get">
    <h3>

        <c:forEach var="premiere" items="${premieres}" varStatus="status">

              <a class="button9"
                 href="JavaScript:
                 showPremieresInfo('block','${premiere.name}', '${premiere.genre}','${premiere.author.name}','${premiere.description}')
                 ">
                  "${premiere.name}"
              </a>
              <br>
              Dates :&nbsp; &nbsp;
              <div class="s2">
                  <c:forEach var="date" items="${premiere.dates}" varStatus="status">
                        <a class="button28" href="JavaScript:sendFormR('${date}','${premiere.name}')"> ${date}</a>&nbsp; &nbsp;
                  </c:forEach>
              </div>
              <br>

        </c:forEach>

    </h3>
    <input type="hidden" name="<%=Constants.KEY_DATE%>" value="1"><BR>
    <input type="hidden"  name="<%=Constants.KEY_PREMIERE%>" value="1"><BR>
</form>


</div>

<%@include file="footer.jsp"%>



