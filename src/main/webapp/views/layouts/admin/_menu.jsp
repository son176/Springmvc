<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<link rel="stylesheet"
	href="${ pageContext.request.contextPath }/css/bootstrap.min.css"></link>
</head>
<body>
	<nav class="row">
		<nav class="col navbar navbar-expand-sm navbar-light bg-light">
			<a class="navbar-brand col-5 mr-5" href=""> <i class="fa fa-home"
				aria-hidden="true"></i>ADMIN
			</a>
			<div class="collapse navbar-collapse col-6" id="collapsibleNavId">
				<ul class="navbar-nav mr-auto mt-2 mt-lg-0">
					<li class="nav-item"><a class="nav-link"
						href="${ pageContext.request.contextPath }/admin/users"><i
							class="fa fa-user" aria-hidden="true"></i> User</a></li>
					<li class="nav-item"><a class="nav-link"
						href="${ pageContext.request.contextPath }/admin/categories"><i
							class="fa fa-comment" aria-hidden="true"></i>Category</a></li>
					<li class="nav-item"><a class="nav-link" href="${ pageContext.request.contextPath }/admin/product"><i
							class="fa fa-comment" aria-hidden="true"></i>Product</a></li>
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" id="dropdownId"
						data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
							<i class="fa fa-user" aria-hidden="true"></i> My Account
					</a>
						<div class="dropdown-menu" aria-labelledby="dropdownId">
							<a class="dropdown-item" href="">${user.username}</a>
							<c:if test="${!isLogin}">
								<a class="dropdown-item"
									href="${ pageContext.request.contextPath }/login">Login</a>
								<a class="dropdown-item" href="">Forgot Password</a>
								<a class="dropdown-item" href="">Register</a>
							</c:if>
							<c:if test="${isLogin}">
								<a class="dropdown-item" href="${ pageContext.request.contextPath }/logout">Logoff</a>
								<a class="dropdown-item" href="">Change Password</a>
							</c:if>
						</div></li>
				</ul>
			</div>
		</nav>
	</nav>
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
		integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
		integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
		crossorigin="anonymous"></script>
</body>
</html>