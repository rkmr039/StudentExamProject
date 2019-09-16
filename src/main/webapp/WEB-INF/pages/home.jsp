<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Student Management Screen</title>
</head>
<body>
	<div align="center">
		<h1>Student List</h1>
		
		<table border="1">
			<th>id</th>
			<th>Roll Number</th>
			<th>Name</th>
			<th>Age</th>
			<th>Gender</th>
			<th>Country</th>
			<th>Date Of Join</th>
			<th>Final Score</th>
			<th>Grade</th>
			<th>Action</th>
			

			<c:forEach var="student" items="${listStudent}">
				<tr>
					<td>${student.id}</td>
					<td>${student.rollNum}</td>
					<td>${student.sname}</td>
					<td>${student.age}</td>
					<td>${student.gender}</td>
					<td>${student.country}</td>
					<td>${student.dateOfJoin}</td>
					<td>${student.finalScore}</td>
					<td>${student.grade}</td>
					<td><a href="editStudent?id=${student.id}">Edit</a>&nbsp;&nbsp;&nbsp;&nbsp; 
					    <a href="deleteStudent?id=${student.id}">Delete</a>&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="exam/showExam?sid=${student.id}&sname=${student.sname}">Marks</a></td> 
				</tr>
			</c:forEach>
		</table>
		<h4>
			New Student Register <a href="newStudent">Click Here</a>
		</h4>
	</div>
</body>
</html>