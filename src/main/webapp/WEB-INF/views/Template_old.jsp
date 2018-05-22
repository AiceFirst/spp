<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>${title}</title>
    <meta name="keywords" content=""/>
    <meta name="description" content=""/>
    <link href="http://fonts.googleapis.com/css?family=Open+Sans+Condensed:300" rel="stylesheet" type="text/css" />
    <link href="<c:url value="/resources/css/style.css"/>" rel="stylesheet">
    <script src="<c:url value="/resources/js/jquery-3.3.1.min.js"/>"></script>
</head>

<body>

<div class="wrapper">

    <header class="header">
        <a href="<c:url value="/index"/>"><spring:message code="Main"/></a>
        <a href="<c:url value="/login"/>"><spring:message code="Login"/></a>
        <a href="<c:url value="/register"/>"><spring:message code="Register"/></a>
        <a href="<c:url value="/about"/>"><spring:message code="About"/></a>
        <a href="<c:url value="/logout"/>">logout</a>

        <spring:message code="admin"/>

        <form>
            <select name="lang" onchange="this.form.submit()">
                <c:forEach items="Rus,Eng" var="i">
                    <option value="${i}" ${pageContext.request.getAttribute('lang') == i?'selected="selected"':''}>${i.toUpperCase()}</option>
                </c:forEach>
            </select>
        </form>

    </header><!-- .header-->

    <div class="middle">

        <div class="container">
            <main class="content">
                <jsp:include page="${menu}"/>
                <jsp:include page="${view}"/>
            </main><!-- .content -->
        </div><!-- .container-->

        <aside class="left-sidebar">
            <strong>Left Sidebar:</strong> Integer velit. Vestibulum nisi nunc, accumsan ut, vehicula sit amet, porta a,
            mi. Nam nisl tellus, placerat eget, posuere eget, egestas eget, dui. Pellentesque habitant morbi tristique
            senectus et netus et malesuada fames ac turpis egestas. In elementum urna a eros. Integer iaculis. Maecenas
            vel elit.
        </aside><!-- .left-sidebar -->

        <aside class="right-sidebar">
            <strong>Right Sidebar:</strong> Integer velit. Vestibulum nisi nunc, accumsan ut, vehicula sit amet, porta
            a, mi. Nam nisl tellus, placerat eget, posuere eget, egestas eget, dui. Pellentesque habitant morbi
            tristique senectus et netus et malesuada fames ac turpis egestas. In elementum urna a eros. Integer iaculis.
            Maecenas vel elit.
        </aside><!-- .right-sidebar -->

    </div><!-- .middle-->

</div><!-- .wrapper -->

<footer class="footer">
    <strong>Footer:</strong> Mus elit Morbi mus enim lacus at quis Nam eget morbi. Et semper urna urna non at cursus
    dolor vestibulum neque enim. Tellus interdum at laoreet laoreet lacinia lacinia sed Quisque justo quis. Hendrerit
    scelerisque lorem elit orci tempor tincidunt enim Phasellus dignissim tincidunt. Nunc vel et Sed nisl Vestibulum
    odio montes Aliquam volutpat pellentesque. Ut pede sagittis et quis nunc gravida porttitor ligula.
</footer><!-- .footer -->

</body>
</html>