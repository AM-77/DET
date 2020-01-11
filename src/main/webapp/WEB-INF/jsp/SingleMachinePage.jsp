<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Machine page</title>
</head>
<body>
		<h2>${Machine.mapName}</h2>
		<img src="${contextPath}/${Machine.imageName}" style="width:auto;height:200px;">
		 <table>
	  	<tbody>
		  	<tr>
				<c:forEach var="column" items="${Machine.columns}">
					<th>${column.title}</th>
					
				<c:forEach var="feature" items="${column.features}">
					<tr>
						<td>${feature.feature} :</td>
						<td>${feature.value} :</td>
					</tr>
				</c:forEach>
			</c:forEach>
				
				
			</tr>	
			
	  	</tbody>
	  </table>
</body>
</html>