<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div>
    <h2><spring:message code="Hi"/>, ${userName}</h2>
    <ul class="list-style1">
        <li class="first"><a href="<c:url value="/user"/>"><spring:message code="main"/></a></li>
        <li><a href="<c:url value="/user/employers_list"/>"><spring:message code="employers_list"/></a></li>
        <li><a href="<c:url value="/user/add_payment"/>"><spring:message code="add_payment"/></a></li>
        <li><a href="<c:url value="/user/payments_history"/>"><spring:message code="payment_history"/></a></li>
        <li><a href="<c:url value="/logout"/>"><spring:message code="logout"/></a></li>
    </ul>
</div>