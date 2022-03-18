<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="./include/includeFile.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	$(function() {
		//로그인 버튼을 클릭했을때
		$('#btnLogin').on('click', function() {
			var email = $('#email').val();
			var passwd = $('#passwd').val();
			if (email==''){
				alert('이메일을 입력해 주세요!');
				$('#email').focus();
			}else if (passwd==''){
				alert('비밀번호를 입력해 주세요!');
				$('#passwd').focus();
			}else{
				$('#frmLogin').attr('action', '${path}/login')
							  .attr('method', 'post')
							  .submit();
			}
			
		});
	});

</script>
</head>
<body>
	<div class="container">
	<%@ include file="header.jsp" %>
	<h2>로그인</h2>
	<form id="frmLogin" action="" method="">
		<table>
			<tr>
			 	<th>이메일</th>
			 	<td><input type="email" name="email" id="email"></td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td><input type="password" name="passwd" id="passwd"></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<button type="button" id="btnLogin">로그인</button> 
					<button type="reset">취소</button> 
					<a href="${apiURL}"><img height="50" src="${path}/resources/images/btnNaver.png"/></a>
				</td>
			</tr>
		</table>
	</form>
	</div>
</body>
</html>