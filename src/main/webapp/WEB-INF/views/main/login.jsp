<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<h2 class="title"><spring:message code="login"/></h2>


<form action="/mvc/j_spring_security_check" method="post">

    <table style="width:100%;">
        <tr>
            <td><spring:message code="name"/></td>
            <td><input type="text"  pattern="[A-Za-z0-9]{3,30}" name="j_username" placeholder="name" required></td>
        </tr>

        <tr>
            <td><spring:message code="pass"/></td>
            <td><input type="password"  pattern="[\S]{3,30}" name="j_password" placeholder="password" required></td>
        </tr>

        <tr>
            <td><spring:message code="remember_me"/></td>
            <td><input type="checkbox" name="_spring_security_remember_me"></td>
        </tr>
    </table>

    <button name="regSubmit"><spring:message code="login"/></button>

</form>