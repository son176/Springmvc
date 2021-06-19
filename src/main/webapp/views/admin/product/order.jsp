<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<link rel="stylesheet"
	href="${ pageContext.request.contextPath }/css/bootstrap.min.css"></link>
</head>
<body>
	<div class="col-10 offset-1 mt-5  p-2">
		<table class="table table-strip table-dark mt-3">
			<thead>
				<tr>
					<td>Id</td>
					<td>Name</td>
					<td colspan="2">Thao tác</td>
				</tr>
			</thead>

			<tbody>
				<c:forEach items="${ listod }" var="i">
					<tr>
						<td>${i.id }</td>
						<td>${ i.username }</td>
						<td><a class="btn btn-primary"
							href="${ pageContext.request.contextPath }/admin/order/${i.id}">Edit</a>
						</td>
						<td></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>