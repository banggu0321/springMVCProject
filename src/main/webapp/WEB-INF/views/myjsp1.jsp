<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<p>${major }</p>
	<p><%=request.getAttribute("major") %></p>
	<p>${phone }</p>
	<p>${car.model }</p>
	<p>${car.price }</p>
	<p>${car.color }</p>
</body>
</html>