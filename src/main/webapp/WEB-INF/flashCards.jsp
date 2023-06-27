<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 27.06.2023
  Time: 19:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="flashCards" scope="request" type="java.util.List<by.tms.courseProject2.flashcards.models.FlashCards>"/>
<jsp:useBean id="themeId" scope="request" type="java.lang.Long"/>;
<html>
<head>
    <title>flashcards</title>
</head>
<body>
<jsp:include page="/WEB-INF/header.jsp"/>
<main>
    <section>
        <h2>FLashCards</h2>
        <c:if test="${flashCards.isEmpty()}">
            <p><b>There is no flashcards in this theme</b></p>
        </c:if>
        <c:if test="${!flashCards.isEmpty()}">
            <ol>
                <c:forEach var="flashCards" items="${flashCards}">
                    <c:out value="${flashCards.question}"/>
                    <c:out value="${flashCards.answer}"/>
                    </c:forEach></h3>
                </c:if>
            </ol>


    </section>
</main>
</body>
</html>
