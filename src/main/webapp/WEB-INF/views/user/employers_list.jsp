<%@ page import="by.bsuir.model.User" %>
<%@ page import="by.bsuir.model.employer" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="employersList" value="${employersList}"/>
<c:set var="user" value="${user}"/>
<%
    User user = (User) pageContext.getAttribute("user");
%>

<h2 class="title"><spring:message code="employers_list"/></h2>


<table style="width: 100%" id="myTable">
    <tr>
        <th><spring:message code="title"/></th>
        <th><spring:message code="description"/></th>
        <th><spring:message code="email"/></th>
        <th style="width: 50px"></th>
    </tr>

    <c:forEach items="${employersList}" var="employer">
        <%
            employer employer = (employer) pageContext.getAttribute("employer");

        %>

        <tr <%if (user.isemployerInUser(employer)) {%>style="background: #e8efe9"<%}%>>
            <td>${employer.getTitle()}</td>
            <td>${employer.getDescription()}</td>
            <td><a href="mailto:${employer.getEmail()}">${employer.getEmail()}</a></td>
            <td>
                <%
                    if (user.isemployerInUser(employer)) {
                %>
                <a href="<c:url value="/user/edit_employer/${employer.getId()}"/>"><spring:message code="edit"/></a>
                <%
                } else {
                %>
                <a href="<c:url value="/user/add_employer/${employer.getId()}"/>"><spring:message code="add"/></a>
                <%
                    }
                %>
            </td>

        </tr>
    </c:forEach>
</table>

<script type="application/javascript" src="<c:url value="/resources/js/slider.js"/>"></script>
