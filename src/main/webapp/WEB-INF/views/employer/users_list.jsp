<%@ page import="by.bsuir.model.User" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h2 class="title"><spring:message code="users_list"/></h2>

<form class="form" >

    <table style="width: 100%"  id="myTable">
        <tr>
            <th><spring:message code="in"/></th>
            <th><spring:message code="fio"/></th>
            <th><spring:message code="login"/></th>
            <th><spring:message code="email"/></th>
            <th><spring:message code="city"/></th>
            <th><spring:message code="other"/></th>
        </tr>


        <c:forEach items="${users}" var="user">

            <%

                User user = (User) pageContext.getAttribute("user");
            %>
            <tr>
                <td><input type="checkbox" name="int_list" value="${user.getId()}"></td>
                <td>${user.getFio()}</td>
                <td>${user.getUsername()}</td>
                <td>${user.getEmail()}</td>
                <td>${user.getcity()}</td>
                <td>${user.getOther()}</td>
            </tr>
        </c:forEach>
    </table>

    <h2 class="title"><spring:message code="message"/></h2>


    <table style="width:100%;">
        <tr>
            <td><spring:message code="title"/></td>
            <td><input type="text"  name="string_1"></td>
        </tr>

        <tr>
            <td><spring:message code="message"/></td>
            <td><textarea name="string_2"></textarea></td>
        </tr>

    </table>

    <button id="btn"><spring:message code="save"/></button>

</form>

<script type="application/javascript" src="<c:url value="/resources/js/slider.js"/>"></script>


<script>

    $(".form").submit(function () {

        var form = $(this);

        console.log(form.serialize());

        $.post("/mvc/employer/send_mail", form.serialize(), function (resp) {

            <%--alert("<spring:message code="all_right"/>");--%>

            console.log("rest: " + resp);

        });

        return false;

    })

</script>