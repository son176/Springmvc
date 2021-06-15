<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<link rel="stylesheet"
	href="${ pageContext.request.contextPath }/css/bootstrap.min.css"></link>
<title>Insert title here</title>
</head>
<body>
	<div class="row">
		<tiles:insertAttribute name="header"></tiles:insertAttribute>
	</div>
	<div class="row">
		<tiles:insertAttribute name="menu"></tiles:insertAttribute>
	</div>

	<div class="row">
		<tiles:insertAttribute name="content"></tiles:insertAttribute>
	</div>
	<div class="row">
		<tiles:insertAttribute name="footer"></tiles:insertAttribute>
	</div>
</body>
</html>