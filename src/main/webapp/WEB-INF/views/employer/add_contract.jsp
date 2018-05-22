<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<h2 class="title"><spring:message code="contract"/></h2>

<form class="form">
    <input type="hidden" name="id" placeholder="Title" value="${contractEntity.getId()}"/>
    <table style="width: 100%">
        <tr>
            <td>
                <spring:message code="title"/>
            </td>
            <td>
                <input type="text" pattern="[\S]{3,30}" required name="title" placeholder="Title" value="${contractEntity.getTitle()}"/>
            </td>
        </tr>
        <tr>
            <td>
                <spring:message code="description"/>
            </td>
            <td>
                <textarea  maxlength="250" name="description">${contractEntity.getDescription()}</textarea>
            </td>
        </tr>

        <tr>
            <td>
                <spring:message code="daysNumber"/>
            </td>
            <td>
                <input type="text" name="daysNumber" pattern="[0-9]{3,10}" required placeholder="days" value="${contractEntity.getdaysNumber()}"/>
            </td>
        </tr>

        <tr>
            <td>
                <spring:message code="cost2"/>
            </td>
            <td>
                <input type="text" name="costPerMonth" pattern="[0-9]{1,10}" required placeholder="cost"
                       value="${contractEntity.getCostPerMonth()}"/>
            </td>
        </tr>
    </table>

    <button name="regSubmit"><spring:message code="save"/></button>

</form>


<script>
    $(".form").submit(function () {

        var form = $(this);

        $.post("/mvc/employer/save_contract", form.serialize(), function (answer) {

            alert("<spring:message code="all_right"/>");

            console.log(answer);

        });

        return false;

    });

</script>
