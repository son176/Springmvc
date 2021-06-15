<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
</head>
<body>
	<nav class="row">
		<nav class="col navbar navbar-expand-sm navbar-light bg-light">
			<a class="navbar-brand col-5 mr-5" href="${ pageContext.request.contextPath }/home"> <i
				class="fa fa-home" aria-hidden="true"></i>Home
			</a>
			<div class="collapse navbar-collapse col-6" id="collapsibleNavId">
				<ul class="navbar-nav mr-auto mt-2 mt-lg-0">
					<li class="nav-item"><a class="nav-link"
						href=""><i class="fa fa-user"
							aria-hidden="true"></i> Order</a></li>
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" id="dropdownId"
						data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
							<i class="fa fa-user" aria-hidden="true"></i> My Account
					</a>
						<div class="dropdown-menu" aria-labelledby="dropdownId">
							<a class="dropdown-item" href="">${user.username}</a>
							<c:if test="${!isLogin}">
								<a class="dropdown-item" href="">Login</a>
								<a class="dropdown-item" href="">Forgot
									Password</a>
								<a class="dropdown-item" href="">Register</a>
							</c:if>
							<c:if test="${isLogin}">
								<a class="dropdown-item" href="${ pageContext.request.contextPath }/logout">Logoff</a>
								<a class="dropdown-item" href="">Change
									Password</a>
							</c:if>

						</div></li>
				</ul>

			</div>
		</nav>
	</nav>
</body>
</html>