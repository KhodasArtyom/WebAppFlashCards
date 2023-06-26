<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 24.06.2023
  Time: 7:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="themes" scope="request"
             type="java.util.List<by.tms.courseProject2.flashcards.models.FlashCardsThemes>"/>

<html>
<head>
    <meta name="author" content="Artyom Khodas">
    <meta name="description" content="The main page of themes">
    <title>The main:Themes</title>
</head>
<body>
<jsp:include page="/WEB-INF/header.jsp"/>
<main>

    <h1>Main page:THEMES</h1>
    <b>choose the theme:</b>
    <c:if test="${themes.isEmpty()}">
        <p>There is no any theme</p>
    </c:if>
    <c:if test="${!themes.isEmpty()}">
        <ol>
            <c:forEach var="themes" items="${themes}">
                <li>
                    <a href="<c:url value="/flashcards?themeId=${themes.id}"/> ">
                        <c:out value="${themes.id}"/>
                    </a>
                </li>
                <li>
                    <a href="http://localhost:8081/flashcards?themeId=2" target="_self"><b title="Цвета">
                        <strong>English:COLORS</strong></b></a> <b>0/0</b><br>
                </li>
            </c:forEach>
        </ol>
    </c:if>

</main>
</body>
</html>
