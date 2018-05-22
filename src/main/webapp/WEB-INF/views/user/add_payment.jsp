<%@ page import="by.bsuir.model.User" %>
<%@ page import="by.bsuir.model.employer" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="user" value="${user}"/>
<%
    User user = (User) pageContext.getAttribute("user");
%>
<h2 class="title"><spring:message code="add_payment"/></h2>

<form class="form">

    <input type="hidden" name="field_1" value="${user.getId()}">


    <table style="width: 100%;">
        <tbody>
        <tr>
            <td><spring:message code="employer"/></td>
            <td>
                <select name="field_2" required>
                    <c:forEach items="${user.getemployers()}" var="employer">
                        <%
                            employer employer = (employer) pageContext.getAttribute("employer");
                        %>
                        <option value="${employer.getId()}">${employer.getTitle()}</option>

                    </c:forEach>
                </select>

            </td>
        </tr>

        <tr>
            <td><spring:message code="amount"/></td>
            <td><input name="field_3" pattern="[0-9]{4,44}" placeholder="payment" required type="text"></td>
        </tr>

        </tbody>
    </table>

    <button class="add_paymen_form_btn"><spring:message code="save"/></button>

</form>

<script>

    $(".form").submit(function () {

        var form = $(this);

        $.post("/mvc/user/add_payment", form.serialize(), function (resp) {

            alert("<spring:message code="all_right"/>");

            console.log(resp);

        });

        return false;

    })

</script>