<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div>
    <h2><spring:message code="Hi"/>, ${userName}</h2>
    <ul class="list-style1">
        <li  class="first"><a href="<c:url value="/employer"/>"><spring:message code="main"/></a></li>
        <li><a href="<c:url value="/employer/contracts_list"/>"><spring:message code="contracts_list"/></a></li>
        <li><a href="<c:url value="/employer/add_contract"/>"><spring:message code="add_contract"/></a></li>
        <li><a href="<c:url value="/employer/things_list"/>"><spring:message code="things_list"/></a></li>
        <li><a href="<c:url value="/employer/add_thing"/>"><spring:message code="add_thing"/></a></li>
        <li><a href="<c:url value="/employer/edit_info"/>"><spring:message code="edit_info"/></a></li>
        <li><a href="<c:url value="/logout"/>"><spring:message code="logout"/></a></li>
    </ul>
</div>