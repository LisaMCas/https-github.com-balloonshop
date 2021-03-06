<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="row">
	<div class="col-lg-12">
		<a href="${pageContext.request.contextPath}/admin/product?productId=0"
			class="btn btn-danger">Add new product</a>
	</div>
</div>
<br>
<div class="row">
	<div class="col-lg-12">
		<table class="tablesorter table table-border" id="myTable">
			<thead>
				<tr>
					<th>Name</th>
					<th>Description</th>
					<th>Price</th>
					<th>Large image</th>
					<th>Small image</th>
					<th>On promotion</th>
					<th class="not-sortable">Action</th>
				</tr>
			</thead>
			<c:forEach var="product" items="${products}">
				<tr>
					<td>${product.name}</td>
					<td>${product.description}</td>
					<td>${product.price}</td>
					<td>${product.largeImage}</td>
					<td>${product.smallImage}</td>
					<td>${product.onPromotion}</td>
					<td><a class="btn btn-warning btn-block"
						href="${pageContext.request.contextPath}/admin/product?productId=${product.id}">Edit</a>
					<button class="btn btn-danger btn-block"
							onclick="deleteProduct(${product.id})">Delete</button></td>
				</tr>
			</c:forEach>
		</table>
		<br>
	</div>
</div>
<div class="row">
	<nav class="text-center">
		<ul class="pagination shadow">
			<c:if test="${page ne 1}">
				<li><a
					href="?page=${page - 1}">
						<span aria-hidden="true">&laquo;</span> <span class="sr-only">Previous</span>
				</a></li>
			</c:if>
			<c:forEach begin="1" end="${pageCount}" varStatus="status">
				<li class="${page == status.count? 'active':'' }"><a
					href="?page=${status.count}">${status.count}</a></li>
			</c:forEach>
			<c:if test="${page ne pageCount}">
				<li><a
					href="?page=${page + 1}">
						<span aria-hidden="true">&raquo;</span> <span class="sr-only">Next</span>
				</a></li>
			</c:if>
		</ul>
	</nav>
</div>


<script type="text/javascript">
		$(document).ready(function() {
			$("#myTable").tablesorter();
		});
		
		function deleteProduct(id){
		    if(confirm("Are you sure you want to delete the product?") ){
		           location.href="/balloonshop/admin/delete/product?id="+id;
		     }
		}
	</script>