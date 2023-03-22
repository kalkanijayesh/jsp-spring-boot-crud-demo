<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<style>
    h5 {
        color:red;
    }
</style>
<h5>Role: ${role}</h5>
<form action="/createUser">
    <input type="submit" value="Create User">
</form>

<table width="100%" border="1" >
    <th>User Id</th>
    <th>First Name</th>
    <th>Last Name</th>
    <th>Email Id</th>
    <th>Actions</th>
    <c:forEach items="${users}" var="user">
        <tr>
            <td>${user.userId}</td>
            <td>${user.firstName}</td>
            <td>${user.lastName}</td>
            <td>${user.emailId}</td>
            <td style="text-align:center">
                <c:if test="${editAccess}">
                    <a href="/edit/id=<c:out value='${user.userId}' />">Edit</a>
                </c:if>
                <c:if test="${deleteAccess}">
                    <a href="/delete/id=<c:out value='${user.userId}'/>">Delete</a>
                </c:if>
                <c:if test="${viewAccess}">
                    <a href="/view/id=<c:out value='${user.userId}'/>">View</a>
                </c:if>
            </td>
        </tr>
    </c:forEach>

</table>
