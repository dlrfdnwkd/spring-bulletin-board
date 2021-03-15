<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<title>Sign Up</title>
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
			<h1>LOGIN</h1>
			<form id=signupForm method="post">
				<input type="text" name="userEmail" id="userEmail" class="text-field" placeholder="이메일">
				<button type="button" name="userEmailCheck" id="userEmailCheck">이메일 중복확인</button> 
				<input type="text" name="userId" id="userId" class="text-field" placeholder="아이디">
				<button type="button" name="userIdCheck" id="userIdCheck">이메일 중복확인</button> 
				<input type="password" name="userPw" id="userPw" class="text-field" placeholder="비밀번호">
				<input type="text" name="userName" id="userName" class="text-field" placeholder="이름"> 
				<input type="button" id="signupBtn" class="login-btn" value="회원가입">
			</form>
		</div>
		
		<script type="text/javascript">
		$("#userEmailCheck").click(function(){
			var userEmail = $("#userEmail").val();
			if(!userEmail){
				alert("이메일을 입력해주세요");
			} else{
				$.ajax({
					url: "/user/userEmailCheck",
					type: "post",
					data: {"userEmail" : userEmail},
					success: function(data){
						if(data == 'none'){
							alert("이 이메일은 사용가능합니다");
							$("#userEmail").attr("readonly",true);
						} else{
							alert("이 이메일은 이미 존재합니다\n 다시 시도해주세요");
						}
					}, error: function(data, status, error){
						alert("code : " + data.status + "\n" + "message : "+ data.responseText + "\n" + "error :" + error);
					}
				});
			}
		});
		$("#userIdCheck").click(function(){
			var userId = $("#userId").val();
			if(!userId){
				alert("아이디를 입력해주세요");
			}else {
				$.ajax({
					url: "/user/userIdCheck",
					type: "post",
					data:  {"userId" : userId},
					success: function(data){
						if(data == 'none'){
							alert("현재 아이디는 사용 가능합니다");
							$("#userId").attr("readonly",true);
						}else {
							alert("현재 아이디는 사용 불가능합니다");
						}
					}, error: function(data, status, error){
						alert("code : " + data.status + "\n" + "message : "+ data.responseText + "\n" + "error :" + error);
					}
				});
			}
		});
		$("#signupBtn").click(function(){
			var userEmail = $("#userEmail").val();
			var userId = $("#userId").val();
			var userPw = $("#userPw").val();
			if(!userEmail || !userId || !userPw){
				alert("아이디, 이메일, 비밀번호는 필수입력입니다");
			} else{
				$.ajax({
					url: "/user/signupUser",
					type: "post",
					data : $("#signupForm").serialize(),
					success: function(data){
						if(data == 'success'){
							alert("회원가입 되셨습니다");
							location.href = "/";
						} else {
							alert("다시 시도해주세요");
						}
					}, error: function(data, status, error){
						alert("code : " + data.status + "\n" + "message : "+ data.responseText + "\n" + "error :" + error);
					}
				});
			}
		});
		</script>
	</body>
</html>