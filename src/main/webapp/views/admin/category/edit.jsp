<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<title>Edit</title>
<link rel="stylesheet" href="${ pageContext.request.contextPath }/css/bootstrap.min.css" ></link>
</head>
<body>
	<div class="mt-5 col-10 offset-1">
		<form:form
			modelAttribute="category"
			method="POST"
			enctype="multipart/form-data"
			action="${ pageContext.request.contextPath }/admin/categories/update/${category.id }">
			<div class="form-group mt-3">
				<label for="name">Name</label>
			    <form:input path="name" class="form-control" autocomplete="off" />
			    <form:errors path="name" element="span" cssClass="text-danger" />
			</div>
			<c:if test="${ not empty sessionScope.error }">
			<div class="alert alert-danger">${ sessionScope.error }</div>
			<c:remove var="error" scope="session"/>
		   	</c:if>
			<div class="form-group mt-3">
				<button class="btn btn-primary">Submit</button>
			</div>
		</form:form>
	</div>
</body>
</html>