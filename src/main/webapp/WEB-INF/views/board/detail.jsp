<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/includeFile.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- handlebars cdn -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.7.7/handlebars.min.js" ></script>
<!-- 댓글리스트 탬플릿 소스 -->
<script id="template_source" type="text/x-handlebars-template">
{{#each .}}
	<hr>
	{{#levelSpace reLevel}} <!-- levelSpace : 헬퍼의 이름 -->
	{{/levelSpace}}
	<div id="reply{{replyNum}}">
		번호 :{{replyNum}} 
			reStep: <span id="reStep{{replyNum}}">{{reStep}}</span>  
			reLevel: <span id="reLevel{{replyNum}}">{{reLevel}}</span><br>
		이메일 : {{email}} <br>
		내용 : <span id="content{{replyNum}}">{{content}}</span> <br> 
		<button class="btnReReplyAdd" value="{{replyNum}}">댓글</button>
		<button class="btnReReplyModify" value="{{replyNum}}">수정</button>
		<button class="btnReReplyRemove" value="{{replyNum}}">삭제</button>
	</div>
{{/each}}
</script>
<script type="text/javascript">
	//핸들바의 헬퍼 작성
	//reLevel(레벨)에 따라서 반복문 헬퍼
	Handlebars.registerHelper('levelSpace', function(reLevel)){
		var result = '';
		for (var i=0; i<reLevel; i++){
			result += '<i class="fab fa-replyd"></li>';
		}
		return result;
	}
	
	$(function(){
		//삭제버튼을 클릭했을시에..
		$('#btnRemove').on('click', function(){
			//본인의 게시물만 삭제가능하도록 만들기
			if ('${result.board.email}' != '${sessionScope.email}'){
				alert('삭제 권한이 없습니다.');
				return ;
			}
			if (confirm('삭제하시겠습니까?'))
				location.href = '${path}/board/remove?boardNum=${result.board.boardNum}';
		});
		
		//수정버튼을 클릭했을시에..
		$('#btnModify').on('click', function(){
			location.href = "${path}/board/modify?boardNum=${result.board.boardNum}";
		});
		
		//좋아요를 클릭시에..
		$('#likeUp').on('click', function(){
			var boardNum = '$(result.board.boardNum)';
			console.log(boardNum);
			$.ajax({
				url : '${path}/board/likecnt/' + boardNum,
				type : 'put',
				dataType : 'text', //전송받을 데이터 타입
				success : function(data) {
					console.log(data);
					$('#likecnt').html(data);
				},
				error : function(e) {
					console.log(e);
					alert('실패');
				}
			});
		});
		
		//싫어요를 클릭시에..
		$('#dislikeUp').on('click', function(){
			var boardNum = '${result.board.boardNum}';
			console.log(boardNum);
			$.ajax({
				url : '${path}/board/dislikecnt/' + boardNum,
				type : 'put',
				dataType : 'text',
				success : function(data) {
					console.log(data);
					$('#dislikecnt').html(data);
				},
				error : function(e){
					console.log(e);
					alert('실패');
				}
			});
		});
		
		//---------------------------------------------------------
		//댓글 처리
		//댓글리스트 조회 함수
		function replyList() {
			//댓글 추가창이 게시물 테이블 아래에 위치 : 갯글 리스트 조회시 유지하기 위해서
			$('#divReply').insertAfter('#tblBoardDetail');
			$('#divReply').hide();
			
			//댓긇 수정창이 게시물 테이블 아래에 위치
			$('#divReplyModify'),insertAfter('#tblBoardDetail');
			$('#divReplyModify').hide();
			
			var boardNum = ${result.board.boardNum};
			$.ajax({
				url : '${path}/reply/list' + boardNum,
				type : 'get',
				dataType : 'json',
				success : function(data) {
					console.log(data);
					var source = $('#template_source').html();
					var template = Handlebars.compile(source); //template 객체 생성 
					$('#divReplyList').html(template(data));
				},
				error : function(e){
					console.log(e);
					alert('실패');
				}
			});
		}
		
		//댓글창의 추가 버튼을 클릭시에..
		$('#btnReplyAdd').on('click', fumction(){
			var boardNum = ${result.board.boardNum}; //원본글(게시물)의 번호
			var email = ${'#replyemail'}.val();
			var content = ${'#replycontent'}.val();
			var reStep = ${'#reStep'}.val(); //부모글 순서 
			var reLevel = ${'#reLevel'}.val(); //부모글 레벨
			
			if (email == ''){
				alert('댓글작성자 이메일을 입력해 주세요.');
				$('#replyemail').focus();
				return ; //함수 종료
			}else if (content == ''){
				alert('댓글을 입력해 주세요.');
				$('#replycontent').focus();
				return ; //함수의 종료
			}
			$.ajax({
				url : '${path}/reply/', //매핑 url
				type : 'post', //method방식 추가
				data : JSON.stringify({boardNum, email, content, reStep, reLevel}), //보낼 데이터
				contentType : 'application/json', //보낼 데이터 타입
				dataType : 'text', //받을 데이터 타입
				success : function(data) {
					console.log(data);
					//댓글 리스트
					replyList();
				},
				error : function(e) {
					console.log(e);
					alert('실패');
				}
			});
		});
		
		//댓글의 댓글 추가창 보이기
		//동적으로 이벤트 달기
		//부모의 선택자로 자식의 엘리먼트에 이벤트를 달아준다.
		$('#divReplyList').on('click', '.btnReReplyAdd', function() {
			//추가창 보이기 
			//#divReply를 '#reply'+ replyNum 뒤로 이동
			var replyNum = $(this).val();
			$('#divReply').insertAfter('#reply'+replyNum);
			$('#divReply').show();
			
			//reStep과 reLevel을 읽어서 댓글창에 set(부모의 정보를 알기 위함)
			var reStep = $('#reStep'+replyNum).text();
			var reLevel = $('#reLevel'+replyNum).text();
			$('#reStep').val(reStep);
			$('#reLevel').val(reLevel);
			$('#replyemail').val('');
			$('#replycontent').val('');
		});
		
		//원본글의 댓글을 클릭했을시에..
		$('#btnReply').on('click', function(){
			//게시물 테이블 아래에 위치
			$('#divReply').insertAfter('#tblBoardDetail');
			$('#divReply').show();
			$('#reStep').val('0');
			$('#reLevel').val('0');
			$('#replyemail').val('');
			$('#replycontent').val('');
		});
		
		//댓글의 삭제버튼을 클릭했을시에..
		$('#divReplyList').on('click', '.btnReReplyRemove', function(){
			if (!confirm('삭제하시겠습니까?')){
				return ; //함수 종료
			}
			var replyNum =$(this).val();
			$.ajax({
				url : '${path}/reply/'+replyNum,
				type : 'delete', //삭제 메소드
				dataType : 'text',
				success : function(data) {
					console.log(data);
					//댓글 리스트 
					replyList();
				},
				error : function(e) {
					console.log(e);
					alert('실패');
				}
			});
		});
		
		//댓글의 수정버튼을 클릭했을시에..
		$('#divReplyList').on('click', 'btnReReplyModify', function() {
			//수정창 보이기
			var replyNum = $(this).val();
			$('#divReplyModify').insertAfter('#reply'+replyNum);
			$('#divReplyModify').show();
			
			//replyNum 세팅
			$('#replyNum').val(replyNum);
			//content 세팅
			$('#replycontentModify').val('#content'+replyNum).text();
		});
		
		//수정창의 수정버튼을 클릭했을 경우
		$('#btnReplyModify').on('click', function() {
			var replyNum = $('#replyNum').val();
			var content = $('#replycontentModify').val();
			console.log(replyNum);
			console.log(content);
			$.ajax({
				url : '{path}/reply/',
				type : 'put',
				data : JSON.stringify({replyNum, content}),
				contentType : 'application/json',
				dataType : 'text',
				success : function() {
					console.log(data);
					//댓글리스트
					replyList();
				},
				error : function(e) {
					console.log(e);
					alert('실패');
				}
			});
		});
		
		//댓글 입력창 숨기기
		$('#divReply').hide();
		//댓글 수정창 숨기기
		$('#divReplyModify').hide();
		
		//댓글리스트 조회함수 호출
		replyList();
	});
</script>
</head>
<body>
	<div class="container">
		<%@ include file="../header.jsp" %>
		<h2>상세조회</h2>
		<table id="tblBoardDetail">
			<tr>
				<th>번호</th>
				<td>${result.board.boardNum}</td>
			</tr>
			<tr>
				<th>이메일</th>
				<td>${result.board.email}</td>
			</tr>
			<tr>
				<th>제목</th>
				<td>${result.board.subject}</td>
			</tr>
			<tr>
				<th>번호</th>
				<td>${result.board.content}</td>
			</tr>
			<tr>
				<td colspan="2">
					조회수:${result.board.readCnt}
					<i class="far fa-thumbs-up" id="likeUp"></i>
						<span id="likecnt">${result.board.likeCnt}</span>
					<i class="far fa-thumbs-down" id="dislikeUp"></i> 
						<span id="dislikecnt">${result.board.dislikeCnt}</span>
				</td>
			</tr>
			<tr>
				<th>둥록일자</th>
				<td><fmt:formatDate value="${result.board.regiDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			</tr>
			<tr>
				<th>수정일자</th>
				<td><fmt:formatDate value="${result.board.modiDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<button id="btnModify">수정</button>
					<button id="btnRemove">삭제</button>
					<button id="btnReply">댓글</button>
				</td>
			</tr>
		</table>
		
		<!-- 댓글 입력 창 -->
		<div class="card mb-2" id="divReply">
			<div class="card-header bg-light">
				<i class="fa fa-comment fa"></i> REPLY 추가
				<input type="hidden" id="reStep" size="3">
				<input type="hidden" id="reLevel" size="3">
			</div>
			<div class="card-body">
				<ul class="list-group list-group-flush">
					<li class="list-group-item">
					<div class="form-inline mb-2">
						<label for="replyemail"><i class="fas fa-envelope"></i></label>
						<input type="email" class="form-control ml-2" placeholder="이메일을 입력...." id="replyemail">
					</div>
					<textarea class="form-control" id="replycontent" rows="3"></textarea>
					<button type="button" class="btn btn-dark mt-3" id="btnReplyAdd">추가</button>
					</li>
				</ul>
			</div>
		</div>
		
		<!-- 댓글 수정 창 -->
		<div class="card mb-2" id="divReplyModify">
			<div class="card-header bg-light">
				<i class="fa fa-commemt fa"></i> REPLY 수정
				<input type="hidden" id="replyNum">
			</div>
			<div class="card-body">
				<ul class="list-group list-group-flush">
					<li class="list-group-item">
					<textarea class="form-control" id="replycontentModify" rows="3"></textarea>
					<button type="button" class="btn btn-dark mt-3" id="btnReplyModify">수정</button>
					</li>
				</ul>
			</div>
		</div>
		<!-- 댓글 조회 -->
		<div id="divReplyList"></div>
	</div>
</body>
</html>