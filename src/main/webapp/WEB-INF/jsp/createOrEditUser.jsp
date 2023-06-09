<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<form:form action="/createRecord" method="post" modelAttribute="user">
<table width="20%">
    <tr>
        <td>Email</td>
        <td><form:input type="text" path="emailId"/> </td>
    </tr>
    <tr>
            <td>Password</td>
            <td><form:input type="password" path="password"/> </td>
        </tr>
    <tr>
        <td>Firstname</td>
        <td><form:input type="text" path="firstName"/></td>
    </tr>
    <tr>
        <td>Lastname</td>
        <td><form:input type="text" path="lastName"/></td>
    </tr>
    <tr>
        <td>Designation</td>
        <td>
        <form:select path="designation" items="${designations}"></form:select>
        </td>

    </tr>
</table>

<input type="submit" value="Submit"> <br/>
</form:form>

<form action="/home">
<input type="submit" value="Go Home" />
</form>