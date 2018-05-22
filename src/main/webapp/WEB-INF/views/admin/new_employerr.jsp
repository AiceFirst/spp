<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<h2 class="title"><spring:message code="new_employer"/></h2>

<form class="form">

    <table style="width: 100%">
        <tr>
            <td>
                <spring:message code="name"/>
            </td>
            <td>
                <input type="text" pattern="[A-Za-z0-9]{3,30}" required  name="username" placeholder="Login"/>
            </td>
        </tr>
        <tr>
            <td>
                <spring:message code="email"/>
            </td>
            <td>
                <input type="email" name="email" required  placeholder="Email"/>
            </td>
        </tr>

        <tr>
            <td>
                <spring:message code="pass"/>
            </td>
            <td>
                <input type="password" pattern="[\S]{3,30}" name="password" placeholder="Pass" required/>
            </td>
        </tr>
        <tr>
            <td>
                <spring:message code="title"/>
            </td>
            <td>
                <input type="text" pattern="[\S]{3,30}" required name="title" placeholder="Title"/>
            </td>
        </tr>
        <tr>
            <td>
                <spring:message code="description"/>
            </td>
            <td>
                <input type="text" maxlength="250" name="description" placeholder="description"/>
            </td>
        </tr>

    </table>


    <input type="hidden" name="type" value="employer">

    <button name="regSubmit"><spring:message code="save"/></button>

</form>


<script>
    $(".form").submit(function () {

        var form = $(this);

        $.post("/mvc/admin/save_employer", form.serialize(), function (answer) {

            console.log(answer);

            alert("<spring:message code="all_right"/>");

        });

        return false;

    });

</script>
