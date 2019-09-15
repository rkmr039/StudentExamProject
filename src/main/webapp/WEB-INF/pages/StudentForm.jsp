<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>New/Edit Contact</title>
</head>
<body>
    <div align="center">
        <h1>New/Edit Student</h1>
        <form:form action="saveStudent" method="post" modelAttribute="student">
        <table>
            <form:hidden path="id"/>
             <tr>
                <td>RollNo:</td>
                <td><form:input path="rollNum" /></td>
            </tr>
            <tr>
                <td>Name:</td>
                <td><form:input path="sname" /></td>
            </tr>
            <tr>
                <td>Age:</td>
                <td><form:input path="age" /></td>
            </tr>
            <tr>
                <td>Gender:</td>
                <td><form:radiobutton path="gender" value="M"/>Male
                    <form:radiobutton path="gender" value="F"/>Female</td>
            </tr>
            <tr>
                <td>Country:</td>
                <td>
                	<form:select path="country" >
                		<form:option value="NONE">Please Select Any One</form:option>
                		<form:option value="IN">India</form:option>
                		<form:option value="US">USA</form:option>
                		<form:option value="JPN">Japan</form:option>
                		<form:option value="AUS">Australia</form:option>
                	</form:select>
                </td>
            </tr>
            <%-- <tr>
                <td>Final Score:</td>
                <td><form:input path="finalScore" /></td>
            </tr> --%>
             <tr>
                <td>DateOfJoin:</td>
                <td><form:input path="dateOfJoin" type="date" /></td>
            </tr>
            
            <tr>
                <td colspan="2" align="center"><input type="submit" value="Save"></td>
            </tr>
        </table>
        </form:form>
    </div>
</body>
</html>