<%@ page contentType="text/html; charset=GBK" pageEncoding="GBK" language="java"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>找回密码</title>
	<link rel="stylesheet" type="text/css" href="css/retrieve.css">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css" integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>
<body>
<form action="RetrieveController" onsubmit="return passwordCheck();" >		<!-- 前面不要加/,否則就是絕對路徑 -->
		<div class="box">
			<div class="login">
				<div class="loginBx">
					<h2><i class="fa-solid fa-right-to-bracket"></i> Forgot <i class="fa-solid fa-heart"></i></h2>
					<input type="text" name="Ret_username" id="Ret_username" placeholder="账号" required>
					<input type="password" name="Ret_userpwd" id="Ret_userpwd" placeholder="新密码" required>
					<input type="password" name="Ret_qruserpwd" id="Ret_qruserpwd" placeholder="确认密码" required>
					<input type="submit" value="Forgot Password">
					<div class="group">
						<a href="register.jsp">Sign up</a>
						<a href="login.jsp">Login</a>
					</div>
				</div>
			</div>
		</div>
</form>

<script type="text/javascript" src="js/retrieve.js"></script>
</body>
</html>