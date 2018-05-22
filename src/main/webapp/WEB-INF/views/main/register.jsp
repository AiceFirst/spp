<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<h2 class="title"><spring:message code="register"/></h2>

<form class="form">

    <table style="width:100%;">
        <tr>
            <td>
                <spring:message code="name"/>
            </td>
            <td>
                <input type="text" pattern="[A-Za-z0-9]{3,30}" required name="username" placeholder="Login"  />
            </td>

        </tr>
        <tr>
            <td>
                <spring:message code="email"/>
            </td>
            <td>
                <input type="email" name="email" placeholder="Email" required />
            </td>

        </tr>
        <tr>
            <td>
                <spring:message code="pass"/>
            </td>
            <td>
                <input type="password" pattern="[\S]{3,30}" name="password" placeholder="Pass" required />
            </td>
        </tr>
        <tr>
            <td>
                <spring:message code="fio"/>
            </td>
            <td>
                <input type="text"  pattern="[\S]{3,44}[ ][\S]{3,44}[ ][\S]{3,44}" name="fio" placeholder="FIO" required />
            </td>
        </tr>
        <tr>
            <td>
                <spring:message code="city"/>
            </td>
            <td>
                <input type="text" pattern="[\D]{3,44}" name="city" placeholder="city" required />
            </td>
        </tr>
        <tr>
            <td>
                <spring:message code="other"/>
            </td>
            <td>
                <input type="text" maxlength="255" name="other" placeholder="Other" />
            </td>

        </tr>

    </table>


    <input type="hidden" name="type" value="user">

    <button name="regSubmit"><spring:message code="register"/></button>

</form>

<script>

    $("form").submit(function () {

        var form = $(this);

        $.post("/mvc/register", form.serialize() , function (answer) {

            alert("<spring:message code="all_right"/>");

            console.log(answer);

        });

        return false;

    });

</script>
