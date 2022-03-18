<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/includeFile.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	$(function(){
		$('.alist').on('click', function(e) {
			e.preventDefault(); //기본이벤트 제거
			var curPage = $(this).attr('href');
			var findKey = $('#findKey').val();
			var findValue = $('#findValue').val();
			location.href = '${path}/board/list?curPage='+curPage+'&findKey='+findKey+'&findValue='+findValue;
		});
	});
</script>
</head>
<body>
	<div class="container">
		<%@ include file="../header.jsp" %>
		<h2>게시물 조회</h2>
		<form class="form-inline" action="${path}/board/list">
			<div class="form-group">
				<select class="form-control" name="findKey" id="findKey">
					<option value="email"<c:out value="${page.findKey=='email'?'selected':''}"/>>이메일</option>
					<option value="subject"<c:out value="${page.findKey=='subject'?'selected':''}"/>>제목</option>
					<option value="content"<c:out value="${page.findKey=='content'?'selected':''}"/>>내용</option>
					<option value="subcon"<c:out value="${page.findKey=='subcon'?'selected':''}"/>>제목+내용</option>
				</select>
				<input class="form-control mr-sm-2" placeholder="Search" name="findValue" id="findValue" value="${page.findValue}">
				<button class="btn btn-success">조회</button>
			</div>
		</form>
		
		<table class="table table-striped">
			<tr>
				<th>번호</th>
				<th>이메일</th>
				<th>제목</th>
				<th>조회수</th>
				<th>좋아요</th>
				<th>싫어요</th>
				<th>등록일자</th>
			</tr>
			<c:forEach var="board" items="${blist}">
				<tr>
					<td>${board.boardNum}</td>
					<td>${board.email}</td>
					<td><a href="${path}/board/detail?boardNum=${board.boardNum}">${board.subject}</a></td>
					<td>${board.readCnt}</td>
					<td>${board.likeCnt}</td>
					<td>${board.dislikeCnt}</td>
					<td><fmt:formatDate value="${board.regiDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				</tr>
			</c:forEach>
		</table>
		
		<!-- 페이징 처리 -->
		<div class="pagination">
			<c:if test="${page.startPage != 1}">
				<a href="${page.startPage-1}" class="alist">&laquo;</a>
			</c:if>
			<c:forEach var="i" begin="${page.startPage}" end="${page.endPage}">
				<c:if test="${i==page.curPage}">
					<a href="${i}" class="alist active">${i}</a>
				</c:if>
				<c:if test="${i!=page.curPage}">
					<a href="${i}" class="alist">${i}</a>
				</c:if>
			</c:forEach>
			<c:if test="${page.totPage>page.endPage}">
					<a href="${page.endPage+1}" class="alist">&raquo;</a>
			</c:if>
		</div>
		${msg}
	</div>
</body>
</html>