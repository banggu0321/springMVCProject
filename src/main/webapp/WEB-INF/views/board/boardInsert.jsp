<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Board생성</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
  <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
  <link rel="stylesheet" href="${path}/css/common.css">
</head>
<body>
<h1>Board작성하기</h1>
<form action="boardInsert.do" method="post" enctype="multipart/form-data">
	제목:<input type="text" name="title" value=""><br>
	내용:<input type="text" name="content" value=""><br>
	작성자:<input type="text" name="writer" value="100"><br>
	<input type="file" name="photos">
	<input type="submit" value="등록">
	<input type="reset" value="취소">
</form>
</body>
</html>