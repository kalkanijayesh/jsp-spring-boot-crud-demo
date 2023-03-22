<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<form action="/home">
<input type="submit" value="Go Home" />
</form>

<table width="100%" border="1" >
<th>User Id</th>
<th>First Name</th>
<th>Last Name</th>
<th>EmailId</th>
<th>Designation</th>

<tr>

<td>${user.userId}</td>
<td>${user.firstName}</td>
<td>${user.lastName}</td>
<td>${user.emailId}</td>
<td>${user.designation}</td>

</tr>

</table>