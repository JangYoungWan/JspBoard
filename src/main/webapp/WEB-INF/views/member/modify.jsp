<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/includeFile.jsp" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
function jusoCallBack(roadFullAddr,roadAddrPart1,addrDetail,roadAddrPart2,engAddr, jibunAddr, zipNo, admCd, rnMgtSn, bdMgtSn,detBdNmList,bdNm,bdKdcd,siNm,sggNm,emdNm,liNm,rn,udrtYn,buldMnnm,buldSlno,mtYn,lnbrMnnm,lnbrSlno,emdNo){
	// 팝업페이지에서 주소입력한 정보를 받아서, 현 페이지에 정보를 등록합니다.
	document.frmModify.addr.value = roadAddrPart1;//도로명
	document.frmModify.addrdetail.value = addrDetail;//상세주소
	document.frmModify.zipcode.value = zipNo;//우편번호	
}

	$(function() {
		//주소창 팝업 띄우기
		$('#findaddr').on('click', function() {
			//WEB-INF 하위 폴더는 직접 접근 불가
			// /app/WEB-INF/views/member/jusoPopup.jsp
			var pop = window.open('${path}/member/jusoPopup', 'juso', 'width=570, height=420, scrollbars=yes, resizable=yes');
		});
		
		//수정을 클릭했을 경우
		$('#btnModify').on('click', function(e) {
			e.preventDefault(); //기본이벤트 제거
			var passwd = $('#passwd').val();
			console.log(passwd);
			if (passwd == '') {
				alert('기존 비밀번호를 입력해 주세요.');
				$('#passwd').focus();
				return ;
			}
			$('#frmModify').attr('action', '${path}/member/modify').submit();
		});
		
		//회원탈퇴버튼을 클릭했을시..
		$('#btnRemove').on('click', function(e) {
			e.preventDefault(); //기본이벤트 삭제
			var result = confirm('회원탈퇴를 하시겠습니까?');
			if (result) {
				var passwd = $('#passwd').val();
				if (passwd == '') {
					alert('기존비밀번호를 입력해주세요.');
					$('#passwd').focus();
					return ;
				}
				location.href = '${path}/member/remove?email=${member.email}&passwd='+passwd;
			}
		});
	});

</script>
</head>
<body>
	<%@ include file="../header.jsp" %>
	<h2>수정</h2>
	<form id="frmModify" name="frmModify" action="" method="post" enctype="multipart/form-data">
		<table border="1">
			<tr>
				<th>이메일</th>
				<td colspan="2">
					<input type="email" name="eamil" value="${member.email}" readonly>
				</td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td colspan="2">
					기존:<input type="password" name="passwd" id="passwd">
					<hr>
					변경:<input type="password" name="chgpasswd" id="chgpasswd">
				</td>
			</tr>
			<tr>
				<th>주소</th>
				<td colspan="2">
					<input type="text" name="zipcode" size="5" value="${member.zipcode}">
						<button type="button" id="findaddr">찾기</button>
					<hr>
					<input type="text" name="addr" size="40" value="${member.addr}">
					<input type="text" name="addrdetail" size="40" value="${member.addrDetail}">
				</td>
			</tr>
			<tr>
				<th>사진</th>
				<td><img src="${path}/saveDir/${member.fileName}" width="50px"></td>
				<td><input type="text" name="fileName" value="${member.fileName}" readonly>
					<hr>
					<input type="file" name="photofile">			
				</td>
			</tr>
			<tr>
				<td colspan="3" align="center">
					<button id="btnModify">수정</button>
					<button type="reset">취소</button>
					<button id="btnRemove">회원탈퇴</button>
				</td>
			</tr>
		</table>
	</form>
	${msg}
</body>
</html>