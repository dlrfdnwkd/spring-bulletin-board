<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
	<head>
		<title>LOGIN</title>
		<link rel="stylesheet" href="/resources/css/login-page.css">
		<link rel="stylesheet" href="https://fonts.googleapis.com/earlyaccess/notosanskr.css">
		<!-- Bootstrap core JavaScript -->
  		<script src="/resources/vendor/jquery/jquery.min.js"></script>
	</head>
	<body>
		<div class="login-form">
			<h1>LOGIN</h1>
			<form id=loginForm method="post">
				<input type="text" name="userId" id="userId" class="text-field" placeholder="아이디"> 
				<input type="password" name="userPw" id="userPw" class="text-field" placeholder="비밀번호">
				<input type="button" id="loginBtn" class="login-btn" value="로그인">
			</form>
		</div>
		
		<script>
		$(function(){
			$("#loginBtn").click(function(){
				var id = $("#userId").val();
				var pw = $("#userPw").val();
				if(!id){
					alert('아이디를 입력해주세요');
				}else {
					if(!pw){
						alert('비밀번호를 입력해주세요');
					}else $.ajax({
						url: "/session/login",
						type: "post",
						data: $("#loginForm").serialize(),
						success: function(data){
							switch(data){
							case "none": alert('계정이 존재하지 않습니다'); break;
							case "fail": alert('비밀번호를 다시 확인해주세요'); break;
							case "success": location.href = '/'; break;
							default: alert('다시 시도해주세요'); break;
							}
						}, error: function(data, status, error){
							alert("code : " + data.status + "\n" + "message : "+ data.responseText + "\n" + "error :" + error);
						}
					});
				}
			});
		});
		</script>
	</body>
</html>