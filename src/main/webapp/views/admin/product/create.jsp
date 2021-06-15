<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create</title>
<link rel="stylesheet"
	href="${ pageContext.request.contextPath }/css/bootstrap.min.css"></link>
</head>
<body>
	
	<div class="mt-5 col-10 offset-1">
		<form:form 
			modelAttribute="product" 
			method="POST"
			enctype="multipart/form-data"
			action="${ pageContext.request.contextPath }/admin/product/store">
			<div class="form-group mt-3">
				<label for="name">Tên sản phẩm</label>
				<form:input path="name" class="form-control" autocomplete="off" />
				<form:errors path="name" element="span" cssClass="text-danger" />
			</div>
			<div class="form-group mt-3">
				<label for="image">Ảnh</label>
				<form:input path="image" class="form-control" autocomplete="off" />
				<form:errors path="image" element="span" cssClass="text-danger" />
			</div>
		   
			<div class="form-group mt-3">
				<label for="price">Giá</label>
				<form:input path="price" name="price" class="form-control" />
				<form:errors path="price" element="span" cssClass="text-danger" />
			</div>
			<div class="form-group mt-3">
				<label for="avaliable">Trạng thái</label>
				<form:select path="avaliable" id="avaliable" class="form-control">
					<form:option value="1">Đang hoạt động</form:option>
					<form:option value="0">Vô hiệu hóa</form:option>
				</form:select>
				<form:errors path="avaliable" element="span" cssClass="text-danger" />
			</div>
			<div class="form-group mt-3">
				<label for="status">Category</label>
				<form:select path="category" id="category" class="form-control">
					<c:forEach items="${listCate}" var="category">					
					<form:option value="${category.id }">${category.name}</form:option>
					</c:forEach>
				</form:select>
			</div>
			<div class="form-group mt-3">
				<button class="btn btn-primary">Submit</button>
				<a href="${ pageContext.request.contextPath }/admin/product/create" type="reset" class="btn btn-danger">Clear</a >
			</div>
		</form:form>
	</div>


</body>
</html>