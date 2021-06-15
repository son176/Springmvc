<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User</title>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<link rel="stylesheet"
	href="${ pageContext.request.contextPath }/css/bootstrap.min.css"></link>
</head>
<body>
	<div class="col-10 offset-1 mt-5 border border-primary p-2">
		<form method="GET"
			action="${ pageContext.request.contextPath }/admin/categories">
			<div class="row col-12 mt-2">
				<div class="col-6">
					<label>Sắp xếp theo</label> <select name="sort_by"
						class="form-control">
						<option value="id">Mặc định</option>
						<option value="name">Tên</option>
						
					</select>
				</div>
				<div class="col-6">
					<label>Thứ tự</label> <select name="sort_direction"
						class="form-control">
						<option value="asc">Tăng dần</option>
						<option value="desc">Giảm dần</option>
					</select>
				</div>
			</div>

			<div>
				<button class="btn btn-primary mt-4">Lọc</button>
				<a href="${ pageContext.request.contextPath }/admin/categories"
					class="btn btn-danger mt-4" type="reset"> Reset </a>
			</div>
		</form>
	</div>



	<div class="mt-5 col-10 offset-1 border border-primary p-2">
		<c:if test="${ not empty sessionScope.sucessfully }">
			<div class="text-success text-center">${ sessionScope.sucessfully }</div>
			<c:remove var="sucessfully" scope="session" />
		</c:if>
		<c:if test="${ not empty sessionScope.status }">
			<div class="text-danger text-center">${ sessionScope.status }</div>
			<c:remove var="status" scope="session" />
		</c:if>
		<div class="">
			<a class="btn btn-success col-1"
				href="${ pageContext.request.contextPath }/admin/categories/create">Create</a>
		</div>
		<table class="table table-strip table-dark mt-3">
			<thead>
				<tr>
					<td>Id</td>
					<td>Name</td>	
					<td colspan="4">Thao tác</td>
				</tr>
			</thead>

			<tbody>
				<c:forEach items="${ pageData.content }" var="category">
					<tr>
						<td>${ category.id }</td>
						<td>${ category.name }</td>
						<td><a class="btn btn-primary"
							href="${ pageContext.request.contextPath }/admin/categories/edit/${category.id}">Update</a>
						</td>
						<td>
							<form
								action="${ pageContext.request.contextPath }/admin/categories/delete/${category.id}"
								method="POST">
								<button class="btn btn-danger">Delete</button>
							</form>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

		<div>
			<ul class="pagination">
				<c:forEach begin="0" end="${ pageData.totalPages - 1 }"
					varStatus="page">
					<li class="page-item"><a
						href="${ pageContext.request.contextPath }/admin/categories/?page=${page.index}"
						class="page-link">${ page.index + 1 } </a></li>
				</c:forEach>
			</ul>
		</div>
	</div>
</body>
</html>