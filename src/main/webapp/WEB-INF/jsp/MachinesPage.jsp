<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="./header.jsp" />
<div class="machines-container">
	<div class="sidebar list">
		<div class="head">
			<div class="title">
				<div class="hide-sidebar"><img src="${contextPath}/assets/images/arrowhead-left.svg" alt="hide sidebar" /></div>
				<p>machining center</p>
				<div class="view">
					<img class="grid-view" src="${contextPath}/assets/images/grid.svg" alt="grid view" />
					<img class="list-view" src="${contextPath}/assets/images/list.svg" alt="list view" />
				</div>
			</div>
			<div class="search">
				<span><img src="${contextPath}/assets/images/search.svg" alt="search button" /></span>
				<input type="text" class="machine-filter" placeholder="Search">
			</div>
		</div>
		<div class="machines"></div>
	</div>
	<div class="content"></div>
</div>
<jsp:include page="./footer.jsp" />