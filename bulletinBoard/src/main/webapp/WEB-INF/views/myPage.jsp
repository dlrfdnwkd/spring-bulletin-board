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
			<form id=updateForm method="post">
				아이디<input type="text" name="userId" id="userId" class="text-field" placeholder="${sessionScope.user.id }"> 
				<button type="button" name="userIdCheck" id="userIdCheck">아이디 중복확인</button>
				<input type="hidden" name="userIdCheckButton" id="userIdCheckButton" value="false">
				이름<input type="text" name="userName" id="userName" class="text-field" placeholder="${sessionScope.user.name }">
				pw<input type="password" name="userPw" id="userPw" class="text-field"> 
				<input type="button" id="updateBtn" class="login-btn" value="수정하기">
			</form>
		</div>
		<script type="text/javascript">
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
				$("#updateBtn").click(function(){
					var userId = $("#userId").val();
					var userName = $("#userName").val();
					var userPw = $("#userPw").val();
					if(!userId && !userName && !userPw){
						alert("수정할 정보를 입력하고 눌러주세요");
					}else {
						if($("#userIdCheckButton").val() == 'false')
							userId = null;
						$.ajax({
							url: "/user/updateUserInfo",
							type: "post",
							data: $("#updateForm").serialize(),
							success: function(data){
								if(data == 'fail'){
									alert("오류가 발생했습니다.\n 다시 시도해주세요");
									location.reload();
								}else {
									alert("수정이 완료되었습니다");
									location.href = '/';
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