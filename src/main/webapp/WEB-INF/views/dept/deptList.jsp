<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>부서목록</title>
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
	tr:nth-child(odd) {
		background-color: lightgray;
	}
	tr:nth-child(even) {
		background-color: white;
	}
	tr:first-child {
		background-color: orange;
	}
</style>
</head>
<body>
<h1>부서목록</h1>
<a href="deptInsert.do">신규 부서 등록</a>
<br><br>
<table>
	<tr>
		<td>순서</td>
		<td>부서번호</td>
		<td>부서이름</td>
		<td>매니저</td>
		<td>지역번호</td>
		<td></td>
	</tr>
	<c:forEach items="${deptlist}" var="dept" varStatus="rowstatus"> <!-- var : DTO -->
		<tr>
			<td>${rowstatus.count }..${rowstatus.count%2==0?'짝':'홀'}</td>
			<td><a href="../html/dept.do?dept_id=${dept.department_id}">${dept.department_id}</a></td>
			<td><a href="../html/dept.do?dept_id=${dept.department_id}">${dept.department_name }</a></td>
			<td>${dept.manager_id }</td>
			<td>${dept.location_id }</td>
			<td><button class="btnDell btn btn-dark" data-deptid="${dept.department_id}">삭제</button></td>
		</tr>
	</c:forEach>
</table>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script>
		$(function(){
			$(".btnDel").on("click",f);
		});
		function f(){
			var deptid = $(this).attr("data-deptid");
			location.href = "deptDelete.do?deptid="+deptid;
		}
		
	</script>
</body>
</html>