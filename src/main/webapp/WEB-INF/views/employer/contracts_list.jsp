<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<h2 class="title"><spring:message code="contracts_list"/></h2>


<table style="width: 100%" id="myTable">
    <tr>
        <th><spring:message code="titleid"/></th>
        <th><spring:message code="title"/></th>
        <th><spring:message code="cost2"/></th>
        <th><spring:message code="daysNumber"/></th>
        <th><spring:message code="description"/></th>

        <th><spring:message code="edit"/></th>
    </tr>

    <c:forEach items="${contractsList}" var="contract">
        <tr>
            <td>${contract.getId()}</td>
            <td>${contract.getTitle()}</td>
            <td>${contract.getCostPerMonth()}</td>
            <td>${contract.getdaysNumber()}</td>
            <td>${contract.getDescription()}</td>
            <td>
                <a href="<c:url value="/employer/edit_contract/${contract.getId()}"/>"><spring:message
                        code="edit"/></a>
            </td>

        </tr>
    </c:forEach>
</table>

<script type="application/javascript" src="<c:url value="/resources/js/slider.js"/>"></script>

<script>


    $("button[class=delete]").click(function () {

        var deleted_id = $(this).data("deleted_id");

        $(this).parents("tr").hide("slow");

        $.post("/mvc/employer/delete_contract/" + deleted_id, {}, function (answer) {

            console.log(answer);

        });

    });

</script>

