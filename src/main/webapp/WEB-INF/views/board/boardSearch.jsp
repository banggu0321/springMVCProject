<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판조회</title>
</head>
<body>
<h1>특정게시판조회</h1>
<form action="boardDetail.do" method="get">
게시판번호: <input type="number" name="boardid" value="1">
<input type="submit" value="부서조회">
</form>
</body>
</html>