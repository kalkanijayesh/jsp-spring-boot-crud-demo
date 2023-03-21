<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<form action="/createUser">
<input type="submit" value="Create User">
</form>

<table width="100%" border="1" >
<th>User Id</th>
<th>First Name</th>
<th>Last Name</th>
<th>EmailId</th>
<th>Actions</th>

<c:forEach items="${ListOfUsers}" var="user">
<tr>

<td>${user.userId}</td>
<td>${user.firstName}</td>
<td>${user.lastName}</td>
<td>${user.emailId}</td>
<td> <c:if test="${role eq 'MANAGER' or role eq 'DIV_MANAGER'}">
         <a href="/edit/id=<c:out value='${user.userId}' />">Edit</a>
     </c:if>
     <c:if test="${role eq 'MANAGER'}">
              <a href="/delete/id=<c:out value='${user.userId}'/>">Delete</a>
          </c:if>

          <c:if test="${role eq 'MANAGER' or role eq 'DIV_MANAGER' or role eq 'DEVELOPER'}">
                        <a href="/view/id=<c:out value='${user.userId}'/>">View</a></td>
                    </c:if>
</tr>
</c:forEach>

</table>
