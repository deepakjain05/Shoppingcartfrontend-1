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
${clickedcategoriessuccess}
${clickedcategoriessuccess}
<form action="category/save/" method="post">
<table border="1">
<tr>
 <td> ID :</td>
 <td><input type="text" name="id" value="${selectCategories.id}"></td>
 </tr>
 
 <tr>
 <td> Name:</td>
 <td><input type="text" name="name" value="${selectCategories.name}"> </td>
 </tr>
 <tr>
 <td>Description :</td>
 <td><input type="text" name="description" value="${selectCategories.description}"></td>
 </tr>
  <tr>
 <td><input type="submit" value="CreateCategory/UpdateCategory"></td>
 
  <tr>
 </table>
 </form >
 <br>
  <br>
   <br>
   
 <div>
  
<table border="3" background="gray">
<tr>
<td>CategoryID </td>
<td>CategoryName </td>
<td>CategoryDescription </td>
<td>Action </td>
</tr>
<c:forEach var="category" items="${categories}">
<tr>
<td>${category.id}</td>
<td>${category.name}</td>
<td>${category.description}</td>
<td><a  href="category/delete/?id=${category.id}"> Delete</a></td>
<td><a  href="category/edit/?id=${category.id}"> Edit</a></td>
</tr>
</c:forEach>
</table>
 
 </div>
</body>
</html>