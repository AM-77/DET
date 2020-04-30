<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
	<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	
	<link rel="stylesheet" href="${contextPath}/assets/styles/loginForm.css">
	<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
	<title>DET | Admin Log</title>
</head>

<body>
	<div class="wrapper fadeInDown">
		<div id="formContent">
			<c:if test="${not empty error}">
				<div class="alert alert-danger">Error! ${error}</div>
			</c:if>
		    <div class="fadeIn first">
		    	<img src="https://img.icons8.com/cute-clipart/50/000000/login-rounded-right.png"/>
		    </div>
		    
		    <form action="/login" method="post">
		      <input type="text" id="login" class="fadeIn second" name="username" placeholder="login">
		      <input type="password" id="password" class="fadeIn third" name="password" placeholder="password">
		      <input type="submit" class="fadeIn fourth" value="Log In">
		    </form>
		</div>
	</div>
</body>
</html>