<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/includeFile.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	$(function() {
		//회원조회
		$('.btnList').on('click', function(){
			var findValue = $('#findValue').val();
			location.href = '${path}/member/list?findValue='+findValue'
		});
	});
</script>
</head>
<body>
	<%@ include file="../header.jsp" %>
	<h2>회원 리스트</h2>
	이메일 <input type="text" id="findValue" value="${param.findValue}">
	<button class="btnList">조회</button>
	<hr>
	<table border="1">
		<tr>
			<th>이메일</th>
			<th>도로명 주소</th>
			<th>등록일자</th>
		</tr>
		<c:forEach var="member" items="${mlist}">
			<tr>
				<td><a href="${path}/member/modify?email=${member.email}">${member.email}</a></td>
				<td>${member.addr}</td>
				<td><fmt:formatDate value="${member.regiDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>