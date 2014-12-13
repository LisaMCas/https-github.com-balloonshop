<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
<meta charset="UTF-8" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-2.1.1.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery.validate.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/balloon-shop.js"></script>
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/css/balloonshop.css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Balloon shop emk</title>
</head>
<body>

	<br />
	<div class="container">
		<c:if test="${not empty notice}">
			<div class="alert alert-warning alert-dismissible text-center"
				role="alert">
				<button type="button" class="close" data-dismiss="alert">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				${notice }
			</div>
		</c:if>
		<div class="row">
			<div class=" hidden-xs col-sm-2">
				<img width="200"
					src="${pageContext.request.contextPath}/images/logo.png"
					class="img-responsive" alt="Responsive image" />
			</div>
			<div class="col-sm-8">
				<h1>
					<strong>Balloon Shop</strong> <small>selling happiness</small>
				</h1>
			</div>
			<br>
			<div class="col-sm-2">
				<div class="btn-group " role="group">
					<button type="button" class="btn btn-primary" data-toggle="modal"
						data-target="#sing-in-modal">Sign in</button>
					<button type="button" class="btn btn-warning" data-toggle="modal"
						data-target="#sign-up-modal">Sign up</button>

				</div>
			</div>

		</div>
		<hr />
		<div class="row">
			<div class="col-lg-10 col-lg-offset-2">
				<h3>Our products</h3>
			</div>
		</div>
		<!-- header -->
		<div class="row">
			<div class="col-lg-2 col-md-3">
				<!-- sidebar -->
				<div class="container-fluid">
					<div class="row">
						<div class="list-group shadow">
							<div class="list-group-item list-group-item-warning">Choose
								a category</div>
							<a class="list-group-item" href="#">Love &amp; romance</a> <a
								class="list-group-item" href="#">Birthdays</a> <a
								class="list-group-item" href="#">Wedding</a> <a
								class="list-group-item" href="#">Cartoons</a> <a
								class="list-group-item" href="#">Message balloons</a>
						</div>
					</div>
					<div class="row">
						<!-- shopping cart -->
						<button class="btn btn-block btn-primary shadow" type="button">
							<span class="glyphicon glyphicon-shopping-cart pull-left"></span>
							Shopping cart <span class="badge">2</span>
						</button>
					</div>
					<br>
					<!-- shopping cart -->
				</div>
			</div>
			<div class="col-lg-10 col-md-9">
				<jsp:include page="${view}.jsp"></jsp:include>
			</div>
		</div>
		<nav class="text-center">
		<ul class="pagination shadow">
			<li><a href="#"> <span aria-hidden="true">&laquo;</span> <span
					class="sr-only">Previous</span></a></li>
			<li class="active"><a href="#">1</a></li>
			<li><a href="#">2</a></li>
			<li><a href="#">3</a></li>
			<li><a href="#">4</a></li>
			<li><a href="#">5</a></li>
			<li><a href="#"> <span aria-hidden="true">&raquo;</span> <span
					class="sr-only">Next</span>
			</a></li>
		</ul>
		</nav>
	</div>

	<jsp:include page="login-modal.jsp"></jsp:include>
	<jsp:include page="sign_up-modal.jsp"></jsp:include>
</body>
</html>