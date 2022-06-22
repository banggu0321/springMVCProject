<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>직원조회</title>
</head>
<body>
	<h1>특정직원조회</h1>
	<form action="empDetail.do" method="post">
		직원번호 : <input type="number" name="empid" value="100"> 
		<input type="submit" value="직원조회">
	</form>
</body>
</html>