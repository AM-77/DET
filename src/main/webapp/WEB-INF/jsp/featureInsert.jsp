<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib  uri="http://www.springframework.org/tags" prefix="spring"%>
	
	<li class="list-group-item feature">
		<spring:bind path="machine.columns[${colCount}].features[${featCount}].feature">
			<input type="text" name="${status.expression}" required="required" value="${status.value}" placeholder="Feature title" class="form-control">
		</spring:bind>
		<spring:bind path="machine.columns[${colCount}].features[${featCount}].value">
			<input type="text" name="${status.expression}" required="required" value="${status.value}" placeholder="Feature value" class="form-control">
		</spring:bind>
	</li>

		
				
		
		
	