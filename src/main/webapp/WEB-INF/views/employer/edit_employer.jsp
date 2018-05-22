<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<h2 class="title"><spring:message code="edit_info"/></h2>
<form class="form">

    <input type="hidden" name="id" placeholder="Login" value="${employerInfo.getId()}"/>
    <input type="hidden" name="username" value="${employerInfo.getUsername()}"/>

    <table style="width:100%;margin-top: 40px;">
        <tr>
            <td>
                <spring:message code="email"/>
            </td>
            <td>
                <input type="email" name="email" required placeholder="Email" value="${employerInfo.getEmail()}"/>
            </td>
        </tr>
        <tr>
            <td>
                <spring:message code="pass"/>
            </td>
            <td>
                <input type="text" pattern="[\S]{3,30}" required  name="password" placeholder="Pass" value="${employerInfo.getPassword()}"/>
            </td>
        </tr>
        <tr>
            <td>
                <spring:message code="title"/>
            </td>
            <td>
                <input type="text" pattern="[\S]{3,30}" required name="title" placeholder="Title" value="${employerInfo.getTitle()}"/>
            </td>
        </tr>
        <tr>
            <td>
                <spring:message code="description"/>
            </td>
            <td>
                <textarea maxlength="250" name="description">${employerInfo.getDescription()}</textarea>
            </td>
        </tr>

    </table>


    <input type="hidden" name="type" value="employer">
    <button name="regSubmit"><spring:message code="save"/></button>

</form>


<script>
    $(".form").submit(function () {

        var form = $(this);

        $.post("/mvc/employer/save_employer", form.serialize(), function (answer) {

            alert("<spring:message code="all_right"/>");
            console.log(answer);

        });

        return false;

    });

</script>
