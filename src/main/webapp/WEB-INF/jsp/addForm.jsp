<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib  uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Add Machine</title>
        <script src="${contextPath}/assets/js/jquery-2.2.4.min.js"></script>
        <style>
        	.machineList{height:800px;overflow-y:scroll;}
        </style>
		<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
		<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
        <script src="${contextPath}/assets/js/column-feature-injector.js"></script>
	<body>
		
	<body>
		<div class="container justify-content-md-center">
			<div class="row">
					<div class="col-md-6 machineList">
						<div class="list-group">
							<div class="list-group-item row">
								<div class="col-md-7"><strong>machine Name</strong></div>
								<div class="col-md-3"><strong>num. columns</strong></div>
							</div>
							<c:forEach items="${machineList}" var="machine">
								<div class="list-group-item row">
									<div class="col-md-7 mapName">${machine.mapName}</div>
									<div class="col-md-3">${fn:length(machine.columns)}</div>
									<div class="col-sm btn btn-sm btn-danger machineDel">X</div>
								</div>
							</c:forEach>
						</div>
					</div>
			
				<div class="col-md-6">
					<c:if test="${error}">
						<div class="alert alert-danger">
							<strong>Error!</strong> Failed to add machine, ${errorMsg}.
						</div>
					</c:if>
					<c:if test="${success}">
						<div class="alert alert-success">
							<strong>Success!</strong> Machine uploaded successfully.
						</div>
					</c:if>
					<form action="/admin/addMachine" method="post" enctype="multipart/form-data">
						<ul class="list-group">
							<li class="list-group list-group-item">
								<label>Category :</label>
								<select  name="category" class="form-control">
									<option selected="selected" value="centre">Centre</option>
									<option value="tour">Tour</option>
								</select>
							</li>
							<li class="list-group list-group-item">
								<label>Machine Image :
								<input class="btn btn-success btn-block" type="file" name="image" accept="image/png, image/jpeg">
								</label>
							</li>
							<li class="list-group list-group-item">
								<label>Machine Name :</label>
								<spring:bind path="machine.mapName">
									<input type="text" name="${status.expression}" required="required" value="${status.value}" placeholder="Machine Name" class="form-control"><br />
								</spring:bind>
						</ul>
						<ul class="list-group columnList">
							<li class="list-group-item">
								<spring:bind path="machine.columns[0].title">
									<input type="text" name="${status.expression}" required="required" value="${status.value}" placeholder="Column title" class="form-control"><br />
								</spring:bind>
							</li>
							<li class="list-group-item column">
								<ul class="list-group list-group-horizontal-sm featureList">
									<li class="list-group-item feature">
										<spring:bind path="machine.columns[0].features[0].feature">
											<input type="text" name="${status.expression}" required="required" value="${status.value}" placeholder="Feature title" class="form-control">
										</spring:bind>
										<spring:bind path="machine.columns[0].features[0].value">
											<input type="text" name="${status.expression}" required="required" value="${status.value}" placeholder="Feature value" class="form-control">
										</spring:bind>
									</li>
									<li class="list-group-item"> 
										<input type="button" class="btn btn-md btn-success addrow"  value="Add Feature" />
										<input type="button" class="ibtnDel btn btn-md btn-danger"  value="Delete">
									</li>
								</ul>
							</li>
							<li class="list-group-item">
								<input type="button" class="btn btn-lg" id="addrowCol" value="Add Column" />
								<input type="button" class="btn btn-lg btn-danger"  id="ibtnDelCol" value="Delete last column">
							</li>
						</ul>
						<input type="submit" class="btn btn-lg btn-success btn-block" value="Add Machine"/>
					</form>
				</div>
			</div>
		</div>
		
		
		
		
				
		
		
	
	</body>
</html>