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
		//파일 추가 아이콘을 클릭할시에..
		$('.addfile').on('click', function(){
			var li = $('</li>').append('<input type="file" name="files">')
							   .append('<i class="fas fa-minus-circle removefile"></i>');
			$('#filelist').append(li);
		});
		
		//파일 삭제 아이콘을 클릭할 시에..
		//동적으로 생성된 엘리먼트에 이벤트 달기
		$('filelist').on('click', 'removefile', function(){
			$(this).parent().remove();
		});
		
		//저장버튼을 클릭할 시에..
		$('#btnAdd').on('click', function(e){
			e.preventDefault(); //기본이벤트 삭제(submit 기능)
			var email = $('#email').val();
			var subject = $('#subject').val();
			var content = $('#content').val();
			if (email == ''){
				alert('이메일을 입력해주세요.');
				$('#email').focus();
			}else if{
				alert('제목을 입력해주세요.');
				$('#subject').focus();
			}else if{
				alert('내용을 입력해주세요.');
				$('#content').focus();
			}else{
				$('frmAdd').attr('action', '${path}/board/add')
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
		<h2>게시물 등록</h2>
		<form id="frmAdd">
			<table>
				<tr>
					<th>이메일</th>
					<td><input type="email" name="email" id="email" value="${sessionScope.email}" readonly></td>
				</tr>
				<tr>
					<th>제목</th>
					<td><input type="text" name="subject" id="subject"></td>
				</tr>
				<tr>
					<th>내용</th>
					<td><textarea rows="5" cols="20" name="content" id="content"></textarea></td>
				</tr>
				<tr>
					<th>파일 <i class="fas fa-plus-circle addfile"></i> </th>
					<td>
						<ol id="filelist">
							<li>
								<input type="file" name="files">
								<i class="fas fa-minus-circle removefile"></i>
							</li>
						</ol>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<button id="btnAdd">등록</button>
						<button type="reset">취소</button>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>