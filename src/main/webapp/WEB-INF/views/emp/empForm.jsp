<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
  <style>
  	table{ border : 1px solid black; border-collapse: collapse;}
  	table td {border : 1px solid black; border-collapse: collapse;}
  </style>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
  <script>
	$(function(){
		$("#btnRetrieveAll").click(f1);
	});
	function f1(){
		$.ajax({
			url:"${path}/emp/emplist.do",
			success: function(responseData){
				console.log(responseData);
				printEmp(responseData);
			}
		});
	}
	function printEmp(emplist){
		var output = `
					<table>
						<tr>
							<td>순서</td>
							<td>상세보기</td>
							<td>직원번호</td>
							<td>성</td>
							<td>이름</td>
							<td>입사일</td>
							<td>급여</td>
							<td>전화번호</td>
							<td>부서</td>
							<td>커미션</td>
							<td>매니저</td>
							<td>직책</td>
							<td>이메일</td>
							<td></td>
						</tr>
					`;
		$.each(emplist, function(index, item){
			output += `
				<tr>
					<td>\${index}</td>
					<td>상세보기</td>
					<td>\${item.employees_id}</td>
					<td>\${item.last_name}</td>
					<td>\${item.first_name}</td>
					<td>\${item.hire_date}</td>
					<td>\${item.salary}</td>
					<td>\${item.phone_number}</td>
					<td>\${item.department_id}</td>
					<td>\${item.commission_pct}</td>
					<td>\${item.manager_id}</td>
					<td>\${item.job_id}</td>
					<td>\${item.email}</td>
					<td></td>
				</tr>
			`;
		});
					
		$("#here").html(output + "</table>");
	}
  </script>
</head>
<body>
	<h1>RestController연습</h1>
	<button id="btnRetrieveAll">모든직원조회</button>
	<button id="btnRetrieveById">특정직원조회</button>
	<button id="btnRetrieveByManager">모든매니저조회</button>
	<button id="btnRetrieveByManagerEmp">특정매니저의 부하직원조회</button>
	<button id="btnRetrieveByEmail">Email해당직원존재?</button>
	<button id="btnRetrieveByJobAll">모든 JOB</button>
	<button id="btnRetrieveByJobEmp">특정 JOB직원</button>
	<button id="btnRetrieveByCondition">조건조회</button>
	<div id="here"></div>
</body>
</html>