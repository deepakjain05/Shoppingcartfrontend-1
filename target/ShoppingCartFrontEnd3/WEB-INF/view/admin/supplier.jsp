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
	${clickedsuppliersuccess} ${clickedsuppliererorr}
	<form action="supplier/save/" method="post">
		<table border="1">
			<tr>
				<td>ID :</td>
				<td><input type="text" name="id" value="${selectSuppliers.id}"></td>
			</tr>
			<tr>
				<td>Name:</td>
				<td><input type="text" name="name"
					value="${selectSuppliers.name}"></td>
			</tr>
			<tr>
				<td>Address:</td>

				<td><input type="text" name="address"
					value="${selectSuppliers.address}"></td>
			</tr>
			<tr>
				<td><input type="submit" value="CreateSupplier/UpdateSupplier"></td>
			<tr>
		</table>
	</form>
	<br>
	<br>
	<br>

	<div>

		<table border="3" background=>
			<tr>
				<td>SupplierID</td>
				<td>SupplierName</td>
				<td>SupplierAddress</td>
				<td>Action</td>
			</tr>
			<c:forEach var="supplier" items="${suppliers}">
				<tr>
					<td>${supplier.id}</td>
					<td>${supplier.name}</td>
					<td>${supplier.address}</td>
					<td><a href="supplier/delete/?id=${supplier.id}"> Delete</a></td>
					<td><a href="supplier/edit/?id=${supplier.id}"> Edit</a></td>
				</tr>
			</c:forEach>
		</table>

	</div>
</body>
</html>