<%@ page import="by.bsuir.model.User" %>
<%@ page import="by.bsuir.model.employer" %>
<%@ page import="by.bsuir.model.thingEntity" %>
<%@ page import="by.bsuir.model.contractEntity" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="employer" value="${employer}"/>
<c:set var="user" value="${user}"/>
<c:set var="employerUserthingId" value="${employerUserthingId}"/>
<%
    User user = (User) pageContext.getAttribute("user");
    employer employer = (employer) pageContext.getAttribute("employer");
    Integer employerUserthingId = (Integer) pageContext.getAttribute("employerUserthingId");
%>
<h2 class="title"><spring:message code="edit_employer"/></h2>

<input name="user_id" type="hidden" value="${user.getId()}">
<input name="employer_id" type="hidden" value="${employer.getId()}">

<select class="select-thing" required>
    <c:forEach items="${employer.getthings()}" var="thing">
        <%
            thingEntity thing = (thingEntity) pageContext.getAttribute("thing");
        %>
        <option value="${thing.getId()}"
                <% if(thing.getId().equals(employerUserthingId)){%>selected<%}%>>${thing.getTitle()}</option>

    </c:forEach>
</select>

<% if (employerUserthingId != null) {%>
<h2 class="title"><spring:message code="contract"/></h2>


<table style="width: 100%">
    <tr>
        <th><spring:message code="title"/></th>
        <th><spring:message code="cost"/></th>
        <th><spring:message code="daysNumber"/></th>
        <th><spring:message code="description"/></th>
        <th style="width: 20px;"><spring:message code="in"/> ?</th>
    </tr>
    <c:forEach items="${employer.getcontracts()}" var="contract">
        <tr>
            <td>${contract.getTitle()}</td>
            <td>${contract.getCostPerMonth()}</td>
            <td>${contract.getdaysNumber()}</td>
            <td>${contract.getDescription()}</td>
            <td>
                <%
                    contractEntity contract = (contractEntity) pageContext.getAttribute("contract");

                %>
                <input data-contract_id="${contract.getId()}" class="thing-checkbox" type="checkbox" value=""
                       <%if(user.iscontractInUser(contract)){%>checked="checked"<%}%>>
            </td>
        </tr>
    </c:forEach>

</table>



<%}%>
<script>


    $("input[type=checkbox]").click(function () {


        var contract_id = $(this).data('contract_id');
        var checked = $(this).is(":checked");
        var user_id = $("input[name=user_id]").val();

        var data = {
            field_1: contract_id,
            field_2: user_id,
            string_1: checked,
        };

        $.post("/mvc/user/save_or_delete_contract_in_user", data, function (resp) {

            console.log(resp);

        });

    });

    $(".select-thing").change(function () {

        var thing_id = $(this).val();
        var user_id = $("input[name=user_id]").val();
        var employer_id = $("input[name=employer_id]").val();

        var data = {
            field_1: thing_id,
            field_2: user_id,
            field_3: employer_id,
        };


        $.post("/mvc/user/update_user_employer_thing", data, function (resp) {

            console.log(resp);

        });
    })


</script>