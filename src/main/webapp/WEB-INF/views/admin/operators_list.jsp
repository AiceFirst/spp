<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<h2 class="title"><spring:message code="employers_list"/></h2>


<table style="width: 100%" id="myTable">
    <tr>
        <th><spring:message code="login"/></th>
        <th><spring:message code="email"/></th>
        <th><spring:message code="date"/></th>
        <th><spring:message code="title"/></th>
        <th><spring:message code="description"/></th>
        <th style="width:50px;"><spring:message code="delete"/></th>
    </tr>

    <c:forEach items="${employersList}" var="employer">
        <tr>
            <td>${employer.getUsername()}</td>
            <td>${employer.getEmail()}</td>
            <td>${employer.getDate()}</td>
            <td>${employer.getTitle()}</td>
            <td>${employer.getDescription()}</td>
            <td>
                <button class="delete" data-deleted_id="${employer.getId()}"><spring:message code="delete"/></button>
            </td>
        </tr>
    </c:forEach>
</table>

<script type="application/javascript" src="<c:url value="/resources/js/slider.js"/>"></script>

<script>


    $("button[class=delete]").click(function () {

        var deleted_id = $(this).data("deleted_id");



        $(this).parents("tr").hide("slow");

        $.post("/mvc/admin/delete_employer/" + deleted_id, {} , function (answer) {

            console.log(answer);

        });

    });

</script>

