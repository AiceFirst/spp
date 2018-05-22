<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<h2 class="title"><spring:message code="things_list"/></h2>
<table style="width: 100%" id="myTable">
    <tr>
        <th><spring:message code="title"/></th>
        <th><spring:message code="cost"/></th>
        <th><spring:message code="description"/></th>
        <th><spring:message code="edit"/></th>
        <th style="width: 50px"><spring:message code="delete"/></th>
    </tr>

    <c:forEach items="${thingsList}" var="thing">
        <tr>
            <td>${thing.getTitle()}</td>
            <td>${thing.getCostPerMonth()}</td>
            <td>${thing.getDescription()}</td>
            <td>
                <a href="<c:url value="/employer/edit_thing/${thing.getId()}"/>"><spring:message code="edit"/></a>
            </td>
            <td>
                <button class="delete" data-deleted_id="${thing.getId()}"><spring:message code="delete"/></button>
            </td>
        </tr>
    </c:forEach>
</table>

<script type="application/javascript" src="<c:url value="/resources/js/slider.js"/>"></script>


<script>


    $("button[class=delete]").click(function () {

        var deleted_id = $(this).data("deleted_id");

        $(this).parents("tr").hide("slow");

        $.post("/mvc/employer/delete_thing/" + deleted_id, {} , function (answer) {

            console.log(answer);

        });

    });

</script>

