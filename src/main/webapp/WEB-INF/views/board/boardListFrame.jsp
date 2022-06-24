<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value ="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table>
		<tr>
			<td>순서</td>
			<td>번호</td>
			<td>제목</td>
			<td>내용</td>
			<td>작성자</td>
			<td>작성일</td>
			<td>수정일</td>
			<td></td>
		</tr>
		<c:forEach items="${boardDatas}" var="board" varStatus="rowstatus">
			<tr class="${rowstatus.count%2==0?'color1':'color2' }">
				<td>${rowstatus.count }..${listSize-rowstatus.index }</td>
				<td><a href="boardDetail.do?boardid=${board.bno}">${board.bno}</a></td>
				<td>${board.title }</td>
				<td>${board.content }</td>
				<td>${board.writer }</td>
				<td>${board.regdate }</td>
				<td>${board.updatedate }</td>
				<td><button class="btnDel btn btn-dark"data-bno="${board.bno}">삭제</button></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>