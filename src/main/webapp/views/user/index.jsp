<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
</head>
<body>
	<div class="col-10 offset-1 mt-5 border border-primary p-2">
		<form method="GET"
			action="${ pageContext.request.contextPath }/home">
			<div class="row col-12 mt-2">
				<div class="col-6">
					<label>Sắp xếp theo</label> <select name="sort_by"
						class="form-control">
						<option value="id">Mặc định</option>
						<option value="name">Tên Sản Phẩm</option>
						<option value="price">Giá</option>
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
				<a href="${ pageContext.request.contextPath }/home"
					class="btn btn-danger mt-4" type="reset"> Reset </a>
			</div>
		</form>
	</div>


		<div class="row p-2">
			<c:forEach var="item" items="${pageData.content}">
				<div class="col-3 mt-4">
					<div class="card text-center">
						<div class="card-body" style="height:230px;">
							<img src="${item.image}" style="height:150px;" alt="" class="img-fluid">
							<div class="row border-top mt-2">
								<b>Tên: ${item.name}</b>
							</div>
							<b class="text-danger">Giá: ${item.price} VND</b>
						</div>
						<div class="card-footer">
							<a href="${ pageContext.request.contextPath }/user/order?id=${ item.id }" class="btn btn-danger">Add to Cart</a>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
		<div class="d-flex justify-content-center">
			<ul class="pagination">
				<c:forEach begin="0" end="${ pageData.totalPages - 1 }"
					varStatus="page">
					<li class="page-item"><a
						href="${ pageContext.request.contextPath }/home/?page=${page.index}"
						class="page-link">${ page.index + 1 } </a></li>
				</c:forEach>
			</ul>
		</div>
</body>
</html>