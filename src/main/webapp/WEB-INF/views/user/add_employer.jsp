<%@ page import="by.bsuir.model.User" %>
<%@ page import="by.bsuir.model.employer" %>
<%@ page import="by.bsuir.model.thingEntity" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="employer" value="${employer}"/>
<c:set var="user" value="${user}"/>
<%
    User user = (User) pageContext.getAttribute("user");
    employer employer = (employer) pageContext.getAttribute("employer");
%>
<h2 class="title"><spring:message code="add_employer"/></h2>

<form class="form">

    <input name="field_1" type="hidden" value="${user.getId()}">
    <input name="field_2" type="hidden" value="${employer.getId()}">

    <select name="field_3" required>
        <c:forEach items="${employer.getthings()}" var="thing">
            <%
                thingEntity thing = (thingEntity) pageContext.getAttribute("thing");
            %>
            <option value="${thing.getId()}">${thing.getTitle()}</option>

        </c:forEach>
    </select>

    <button id="btn"><spring:message code="save"/></button>
</form>

<h2 class="title"><spring:message code="contract"/></h2>

<table style="width: 100%" id="myTable">
    <tr>
        <th><spring:message code="title"/></th>
        <th><spring:message code="cost"/></th>
        <th><spring:message code="daysNumber"/></th>
        <th><spring:message code="description"/></th>
    </tr>
    <c:forEach items="${employer.getcontracts()}" var="contract">
        <tr>
            <td>${contract.getTitle()}</td>
            <td>${contract.getCostPerMonth()}</td>
            <td>${contract.getdaysNumber()}</td>
            <td>${contract.getDescription()}</td>
        </tr>
    </c:forEach>

</table>

<script type="application/javascript" src="<c:url value="/resources/js/slider.js"/>"></script>

<script>

    $(".form").submit(function () {

        var form = $(this);

        $.post("/mvc/user/add_employer", form.serialize(), function (resp) {

            alert("<spring:message code="all_right"/>");

            console.log(resp);

        });

        return false;

    })

</script>
