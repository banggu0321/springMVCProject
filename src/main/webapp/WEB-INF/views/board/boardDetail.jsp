<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Board의 상세내역</title>
</head>
<body>
<h1>Board의 상세내역</h1>
<form action="boardUpdate.do" method="post">
	bno:${board.bno }<input type="hidden" name="bno" value="${board.bno }"><br>
	title:<input type="text" name="title" value="${board.title }"><br>
	content:<input type="text" name="content" value="${board.content }"><br>
	작성자:<input type="text" value="${board.writer }" disabled="disabled"><br>
	작성일:<input type="text" value="${board.regdate }" disabled="disabled"><br>
	수정일:<input type="text" value="${board.updatedate }" readonly="readonly"><br>
	이미지(다운로드시 이미지 클릭) <br> 
		<a href="${pageContext.request.contextPath}/download.do?fileName=${board.pic}" >
			<img alt="" 
				width="300"
				height="300"
				src="${pageContext.request.contextPath}/uploads/${board.pic}">
		</a><br>
	<input type="submit" value="수정">
	<input type="reset" value="취소">

</form>
</body>
</html>