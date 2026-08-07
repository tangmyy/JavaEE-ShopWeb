<%@ page contentType="text/html; charset=GBK" pageEncoding="GBK" language="java"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>登陆页面</title>
	<link rel="stylesheet" type="text/css" href="css/login.css">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css" integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>
<body>
<form action="LoginController">		<!-- 前面不要加/,否則就是絕對路徑 -->
		<div class="box">
			<div class="login">
				<div class="loginBx">
					<h2><i class="fa-solid fa-right-to-bracket"></i> Login <i class="fa-solid fa-heart"></i></h2>
					<input type="text"  name="username" id="username" placeholder="用户名">
					<input type="password"  name="userpwd" id="userpwd" placeholder="密码">
					<input type="submit" value="Log in">
					<div class="group">
						<a href="retrieve.jsp">Forgot Password</a>
						<a href="register.jsp">Sign up</a>
					</div>
				</div>
			</div>
		</div>
</form>
</body>
</html>