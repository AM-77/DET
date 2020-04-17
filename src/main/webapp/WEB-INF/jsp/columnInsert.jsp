<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib  uri="http://www.springframework.org/tags" prefix="spring"%>
		
	
	<li class="list-group-item">
		<spring:bind path="machine.columns[${colCount}].title">
			<input type="text" name="${status.expression}" required="required" value="${status.value}" placeholder="Column title" class="form-control">
		</spring:bind>
	</li>
	<li class="list-group-item column">
		<ul class="list-group list-group-horizontal-sm featureList">
			<li class="list-group-item feature">
				<spring:bind path="machine.columns[${colCount}].features[0].feature">
					<input type="text" name="${status.expression}" required="required" value="${status.value}" placeholder="Feature title" class="form-control">
				</spring:bind>
				<spring:bind path="machine.columns[${colCount}].features[0].value">
					<input type="text" name="${status.expression}" required="required" value="${status.value}" placeholder="Feature value" class="form-control">
				</spring:bind>
			</li>
			<li class="list-group-item"> 	
				<input type="button" class="btn btn-md btn-success addrow" value="Add Feature" />
				<input type="button" class="ibtnDel btn btn-md btn-danger"  value="Delete">	
			</li>
		</ul>
	</li>
				
		
		
	