<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
	<head>
		<title>My Page</title>
		<link rel="stylesheet" href="/resources/css/login-page.css">
		<link rel="stylesheet" href="https://fonts.googleapis.com/earlyaccess/notosanskr.css">
		<!-- Bootstrap core JavaScript -->
  		<script src="/resources/vendor/jquery/jquery.min.js"></script>
  		
  		<style type="text/css">
  		button{
  			margin-bottom: 10px;
  			width: 260px;
  			padding: 10px;
  			font-size: 14px;
  			box-sizing: border-box;
  			border-radius: 5px;
  		}
  		</style>
	</head>
	<body>
		<div class="login-form">
			<h1>마이페이지</h1>
			<form id=loginForm method="post">
				아이디<input type="text" name="userId" id="userId" class="text-field" placeholder="${sessionScope.user.id }"> 
				<button name="userIdCheck" id="userIdCheck">아이디 중복확인</button>
				이름<input type="text" name="userName" id="userName" class="text-field" placeholder="${sessionScope.user.name }">
				email<input type="text" name="userEmail" id="userEmail" class="text-field" placeholder="${sessionScope.user.eMail }"> 
				<button name="userEmailCheck" id="userEmailCheck">이메일 중복확인</button>
				<input type="button" id="loginBtn" class="login-btn" value="수정하기">
			</form>
		</div>
	</body>
</html>