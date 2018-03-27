<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	${clickedproductssuccess} ${clickedproducterror}
	<form action="product/save/" method="post">
		<table border="1">
			<tr>
				<td>ID :</td>
				<td><input type="text" name="id" value="${selectProducts.id}"></td>
			</tr>

			<tr>
				<td>Name:</td>
				<td><input type="text" name="name"
					value="${selectProducts.name}"></td>
			</tr>
			<tr>
				<td>Description :</td>
				<td><input type="text" name="description"
					value="${selectProducts.description}"></td>
			</tr>
			<tr>
				<td>Price :</td>
				<td><input type="text" name="price"
					value="${selectProducts.price}"></td>

			</tr>
			<tr>
				<td>Select Category</td>
				<td><select name="categoryID">
						<c:forEach var="category" items="${categories}">
							<option value="${category.id}">${category.name}</option>
						</c:forEach>
				</select></td>

			</tr>
			<tr>
				<td>Select Supplier</td>
				<td><select name="supplierID">
						<c:forEach var="supplier" items="${suppliers}">
							<option value="${supplier.id}">${supplier.name}</option>
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td><input type="submit" value="CreateProduct/UpdateProduct"></td>
			<tr>
			<tr>
		</table>
	</form>
	<br>
	<br>
	<br>

	<div>

		<table border="3" background="gray">
			<tr>
				<td>ProductID</td>
				<td>ProductName</td>
				<td>ProductDescription</td>
				<td>Action</td>
			</tr>

			<c:forEach var="product" items="${products}">
				<tr>
					<td>${product.id}</td>
					<td>${product.name}</td>
					<td>${product.description}</td>


					<td><a href="product/delete/?id=${product.id}"> Delete</a></td>
					<td><a href="product/edit/?id=${product.id}"> Edit</a></td>
				</tr>
			</c:forEach>
		</table>

	</div>
</body>
</html>