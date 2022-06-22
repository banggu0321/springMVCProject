<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판목록</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
  <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
  <link rel="stylesheet" href="../css/common.css">
<style>
	table, td {
		border:1px solid black;
		border-collapse: collapse;
		padding: 10px;}
	.color1{background-color: lightgray;}
	.color2{background-color: white;}
</style>
</head>
<body>
	<h1>게시판목록</h1>
	<h3>
		application 정보 얻기 : ${myname}
	</h3>
	<a href="boardInsert.do">게시글 작성하기</a>
	<br><br>
	전체건수 : ${boardSize }<br>
	방법2 : <c:set var="listSize" value="${boardDatas.size() }"></c:set>
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
<%-- 		<c:if test="${rowstatus.first}">
				<tr class="color1">
			</c:if>
			<c:if test="${rowstatus.last }">
				<tr class="color2">
			</c:if>
			<c:if test="${rowstatus.first==false && status.last==false }">
				<tr>
			</c:if> --%>
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
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script>
		$(function(){
			//#:id 아이디는 문서내에서 유일 맨위만 찾음 (id->class로 변경)
			$(".btnDel").click(function(){
				var bno = $(this).attr("data-bno");
				if(confirm(bno+"삭제?")){
					location.href = "boardDelete.do?bno=" + bno;
				}
			})
		});
	</script>
	<%-- ${boardDatas } --%>
</body>
</html>