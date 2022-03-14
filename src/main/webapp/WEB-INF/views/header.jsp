<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	$(function(){
		const email = '${sessionScope.email}';
		if (email== ''){ //로그인상태가 아닌경우
			$('#loginInfo').hide();
			$('#logout').hide();
			$('#list').hide();
		}else{
			$('#loginInfo').hide();
			$('#login').hide();
			$('#join').hide();
			
		}
		
	});
</script>
</head>
<body>
	<header>
		<div> <h2>게시물 관리</h2> </div>
	 	<div id="memberInfo">
	 		<div id="loginInfo"><a href="${path}/member/modify?email=${sessionScope.email}">${sessionScope.email}</a></div>
	 		</div>
	 		<div><a href="${path}/login" id="login">로그인</a></div>
	 		<div><a href="${path}/logout" id="logout">로그아웃</a></div>
	 		<div><a href="${path}/member/join" id="join">회원가입</a></div>
	</header>
	<hr>
	<nav>
		<a href="${path}/" id="home">홈으로</a>
		<a href="${path}/member/" id="list">회원 리스트</a>
		<a href="${path}/board/list" id="list">게시물 리스트</a>
		<a href="${path}/board/add" id="add">게시물 등록</a>
	</nav>
	<hr>
</body>
</html>