<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<h2 class="title"><spring:message code="payment_history"/></h2>
<table style="width: 100%"  id="myTable">
    <tr>
        <th><spring:message code="amount"/></th>
        <th><spring:message code="oparator_id"/></th>

    </tr>

    <c:forEach items="${payments}" var="payment">
        <tr>
            <td>${payment.getAmount()}</td>
            <td>${payment.getemployer_id()}</td>

        </tr>
    </c:forEach>
</table>

<script type="application/javascript" src="<c:url value="/resources/js/slider.js"/>"></script>
