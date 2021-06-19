<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Product</title>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<link rel="stylesheet"
	href="${ pageContext.request.contextPath }/css/bootstrap.min.css"></link>
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.min.js" integrity="sha384-Atwg2Pkwv9vp0ygtn1JAojH0nYbwNJLPhwyoVbhoPwBhjQPR5VtM2+xf0Uwh9KtT" crossorigin="anonymous"></script>
</head>
<body>
	<div class="col-10 offset-1 mt-5 border border-primary p-2">
		<form method="GET"
			action="${ pageContext.request.contextPath }/admin/product">
			<div class="row col-12 mt-2">
				<div class="col-6">
					<label>Sắp xếp theo</label> <select name="sort_by"
						class="form-control">
						<option value="id">Mặc định</option>
						<option value="name">Tên</option>
						<option value="createDate">Email</option>
						<option value="avaiable">Trạng thái</option>
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
				<a href="${ pageContext.request.contextPath }/admin/products"
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
				href="${ pageContext.request.contextPath }/admin/product/create">Create</a>
		</div>
		<table class="table table-strip table-dark mt-3">
			<thead>
				<tr>
					<td>Id</td>
					<td>Name</td>
					<td>Image</td>
					<td>Create Date</td>
					<td>Avaiable</td>
					<td>Category</td>
					<td colspan="2">Thao tác</td>
				</tr>
			</thead>

			<tbody>
				<c:forEach items="${ pageData.content }" var="product">
					<tr>
						<td>${ product.id }</td>
						<td>${ product.name }</td>
						<td>${ product.image }</td>
						<td>${ product.create_date}</td>
						<td>${ product.avaliable == 1 ? "Đang hoạt động" : "Vô hiệu hóa" }</td>
						<td>${ product.category.name}</td>
						<td><a class="btn btn-primary"
							href="${ pageContext.request.contextPath }/admin/product/edit/${product.id}">Update</a>
						</td>
						<td>
							
								<!-- Button trigger modal -->
							<a type="button" class="btn btn-danger"
								data-bs-toggle="modal" data-bs-target="#exampleModal${product.id}">
								Delete</a> <!-- Modal -->
							<div class="modal fade" id="exampleModal${product.id}" tabindex="-1"
								aria-labelledby="exampleModalLabel" aria-hidden="true">
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
											<h5 class="modal-title text-dark" id="exampleModalLabel">Bạn muốn xóa không
												</h5>
											<button type="button" class="btn-close"
												data-bs-dismiss="modal" aria-label="Close"></button>
										</div>
										<div class="modal-body text-dark">Bạn muốn xóa chứ</div>
										<div class="modal-footer">
											<button type="button" class="btn btn-secondary"
												data-bs-dismiss="modal">Close</button>
											<a type="button" class="btn btn-danger"  href="${ pageContext.request.contextPath }/admin/product/delete/${product.id}">Delete
												</a>
										</div>
									</div>
								</div>
							</div>
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
						href="${ pageContext.request.contextPath }/admin/products/?page=${page.index}"
						class="page-link">${ page.index + 1 } </a></li>
				</c:forEach>
			</ul>
		</div>
	</div>
</body>
</html>