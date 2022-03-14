<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="./include/includeFile.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>홈</title>
	<script>
		$(function(){
			$('.bxslider').bxSlider({
				auto: true,
				autoControls: true,
				stopAutoOnClick: true,
				pager: true,
				slideWidth: 400
			});
		});
	</script>
</head>
<body>
	<div class="container">
		<%@ include file="header.jsp" %>
		<div class="bxslider">
			<div><img src="${path}/resources/images/city1.jpg" title="도시1"></div>
			<div><img src="${path}/resources/images/city2.jpg" title="도시2"></div>
			<div><img src="${path}/resources/images/city3.jpg" title="도시3"></div>
		</div>
	</div>
</body>
</html>