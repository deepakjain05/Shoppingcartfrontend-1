<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
           <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h3> <a href= "ManageCategories"> Manage Categories</a>
<a href= "ManageProducts"> Manage Products</a>
<a href= "ManageSupplier"> Manage Supplier</a></h3>

<c:if test="${AdminClickedCategories==true}">
<jsp:include page="category.jsp"></jsp:include>
</c:if>
<c:if test="${AdminClickedProduct==true}">
<jsp:include page="product.jsp"></jsp:include>
</c:if>
<c:if test="${AdminClickedSupplier==true}">
<jsp:include page="supplier.jsp"></jsp:include>
</c:if>
</body>
</html>