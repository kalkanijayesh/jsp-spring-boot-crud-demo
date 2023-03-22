<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<form:form action="/home">
<input type="submit" value="Go Home" />
</form:form>
<form:form action="/editedRecord" method="post" modelAttribute="userDtoObject">
<form:input type="text" path="userId" style="visibility:hidden"  value="${user.userId}" readonly="readonly"/> <br/>
<form:input type="text" path="emailId" style="visibility:hidden"  value="${user.emailId}" readonly="readonly"/> <br/>

<table width="30%">

    <tr>
        <td>Firstname :</td>
        <td><form:input type="text" path="firstName" value="${user.firstName}"/></td>
    </tr>
    <tr>
        <td>Lastname :</td>
        <td><form:input type="text" path="lastName" value="${user.lastName}"/></td>
    </tr>
    <tr>
        <td>Designation :</td>
        <td>
          <form:select path="designation" >
            <c:forEach items="${designations}" var="designation">
                <form:option value="${designation}" label="${designation}" selected="${designation == user.designation ? 'selected' : '' }" />
            </c:forEach>
          </form:select>

        </td>
    </tr>
</table>
<input type="submit" value="Submit" /> <br/>
</form:form>