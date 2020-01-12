<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="./header.jsp" />
	
	<c:forEach var="machineType" items="${MachineTypes}">
		
	  
	 <a href="${category}/${machineType.mapName}">
	 <img src="${contextPath}/${machineType.imageName}" style="width:auto;height:200px;">
	 </a><br />
	 
	  <a style="text-decoration:none" href="/${category}/${machineType.mapName}">
	  <Strong>Machine Name: </Strong> <c:out value="${machineType.machineName}"/> <br /> 
	  </a>
	 <br />
	</c:forEach>

<jsp:include page="./footer.jsp" />