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
<center> <h1>T Shirt </h1></center>

${Welcomemessage}
${errormessage}

<br>
<a href="login">Existing user</a>

<a href="Registration">New user</a>






<jsp:include page="product_menu.jsp"></jsp:include>

<c:if test="${isUserSelectedproduct==true}">
<jsp:include page="selected_product.jsp"></jsp:include>
</c:if>
<c:if test="${adminlogin==true}">
<jsp:include page="admin/adminhome.jsp"></jsp:include>
</c:if>



${successMessage}
${errorMessage}
${SuccessMessage}
${ErrorMessage}




<c:if test="${UserClicked==true}">
<jsp:include page="login.jsp"></jsp:include>
</c:if>
<c:if test="${isUserClicked==true}">
<jsp:include page="Registration.jsp"></jsp:include>
</c:if>

</body>
</html>
