<%@ page import="by.bsuir.model.thingEntity" %>
<%@ page import="by.bsuir.model.contractEntity" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="thingEntity" value="${thingEntity}"/>
<h2 class="title"><spring:message code="thing"/></h2>

<form class="form">
    <input type="hidden" name="id" value="${thingEntity.getId()}"/>

    <table style="width:100%;">
        <tr>
            <td>
                <spring:message code="title"/>
            </td>
            <td>
                <input type="text" pattern="[\S]{3,30}" required name="title" placeholder="Title" value="${thingEntity.getTitle()}"/>
            </td>
        </tr>

        <tr>
            <td>
                <spring:message code="description"/>
            </td>
            <td>
                <textarea maxlength="250" name="description">${thingEntity.getDescription()}</textarea>
            </td>
        </tr>

        <tr>
            <td>
                <spring:message code="cost"/>
            </td>
            <td>
                <input type="text" pattern="[0-9]{1,10}" required name="costPerMonth" placeholder="costPerMonth"
                       value="${thingEntity.getCostPerMonth()}"/>
            </td>
        </tr>

    </table>

    <button name="regSubmit"><spring:message code="save"/></button>
</form>

<c:if test="${not empty thingEntity}">

    <h2 class="title"><spring:message code="contract"/></h2>

    <table style="width: 100%;">
        <tr>
            <th><spring:message code="title"/></th>
            <th><spring:message code="cost"/></th>
            <th><spring:message /></th>
            <th><spring:message code="description"/></th>
            <th style="width: 20px"><spring:message code="in"/> ?</th>
        </tr>
        <c:forEach items="${contractsList}" var="contract">
            <tr>
                <td>${contract.getTitle()}</td>
                <td>${contract.getCostPerMonth()}</td>
                <td>${contract.getdaysNumber()}</td>
                <td>${contract.getDescription()}</td>
                <td>
                    <%
                        contractEntity contract = (contractEntity) pageContext.getAttribute("contract");
                        thingEntity te = (thingEntity) pageContext.getAttribute("thingEntity");
                        te.iscontractInThingServices(contract);
                    %>
                    <input data-contract_id="${contract.getId()}" class="thing-checkbox" type="checkbox" value=""
                           <%if(te.iscontractInThingServices(contract)){%>checked="checked"<%}%>>
                </td>
            </tr>
        </c:forEach>

    </table>

</c:if>


<script>


    $("input[type=checkbox]").click(function () {


        var contract_id = $(this).data('contract_id');
        var checked = $(this).is(":checked");
        var thing_id = $(".form input[name=id]").val();

        var data = {
            field_1: contract_id,
            field_2: thing_id,
            string_1: checked,
        };

        $.post("/mvc/employer/save_or_delete_contract_in_thing", data, function (resp) {

            console.log(resp);

        });

    });


    $(".form").submit(function () {

        var form = $(this);

        $.post("/mvc/employer/save_thing", form.serialize(), function (answer) {

            alert("<spring:message code="all_right"/>");

            console.log(answer);

        });

        return false;

    });

</script>