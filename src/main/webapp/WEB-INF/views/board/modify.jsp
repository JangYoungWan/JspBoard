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
		//파일 추가 아이콘 클릭시..
		$('.addfile').on('click', function(){
			var li = $('</li>').append('<input type="file" name="files">')
							   .append('<i class="fas fa-minus-circle removefile"></i>');
			$('#filelist').append(li);
		});
		
		//파일 삭제 아이콘을 클릭할시..
		//동적으로 생성된 엘리먼트에 이멘트 달기
		$('#filelist').on('click', '.removefile', function(){
			$(this).parent().remove();
		});
		
		//수정버튼을 클릭시..
		$('#btnModify').on('click', function(e){
			e.preventDefault(); //기본 이벤트 제거
			var subject = $('#subject').val();
			var content = $('#content').val();
			if (subject == ''){
				alert('제목을 입력해주세요.');
				$('#subject').focus();
			}else if (content == ''){
				alert('내용을 입력해주세요.');
				$('#content').focus();
			}else {
				$('#frmModify').attr('action', '${path}/board/modify')
				.attr('method', 'post')
				.attr('enctype', 'multipart/form-data')
				.submit();
			}
		});
	});
</script>
</head>
<body>
	<div class="container">
		<%@ include file="../header.jsp" %>
		<h2>수정</h2>
		<form id="frmModify">
			<table border="1">
				<tr>
					<th>번호</th>
					<td><input type="text" name="boardNum" id="boardNum" value="${result.board.boardNum}" readonly></td>
				</tr>
				<tr>
					<th>이메일</th>
					<td><input type="email" name="email" id="email" value="${result.board.email}" readonly></td>
				</tr>
				<tr>
					<th>제목</th>
					<td><input type="text" name="subject" id="subject" value="${result.board.subject}"></td>
				</tr>
				<tr>
					<th>내용</th>
					<td><textarea rows="5" cols="20" name="content" id="content">${result.board.content}</textarea></td>
				</tr>
				<tr>
					<th>파일<i class="fas fa-plus-circle addfile"></i></th>
					<td>
						<c:forEach var="boardFile" items="${result.bflist}">
							${boardFile.fileName}
							<input type="checkbox" name="removeFile" value="${boardFile.fileNum}"><i class="fas fa-minus-circle"></i><br>
						</c:forEach>
						<hr>
						<ol id="filelist">
							<li>
								<input type="file" name="files"><i class="fas fa-minus-circle removefile"></i>
							</li>
						</ol>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<button id="btnModify">수정</button>
						<button type="reset">취소</button>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>