<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="https://fonts.googleapis.com/css?family=Poppins&display=swap" rel="stylesheet"> 
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Machines Page</title>
</head>
<body>
	<h1>Search Results :</h1>
	<c:forEach var="machineType" items="${MachineTypes}">
		
	  
	 <a href="${category}/${machineType.mapName}">
	 <img src="${contextPath}/${machineType.imageName}" style="width:auto;height:200px;">
	 </a><br>
	 
	  <a style="text-decoration:none" href="/${category}/${machineType.mapName}">
	  <Strong>Machine Name: </Strong> <c:out value="${machineType.machineName}"/> <br> 
	  </a>
	 
	  	 <p>///////////////////////////////////////////////////////////////////////</p><br>
	</c:forEach>

</body>
</html>