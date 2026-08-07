<%@ page contentType="text/html; charset=GBK" pageEncoding="GBK" language="java"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<title>注册页面</title>
	<link rel="stylesheet" type="text/css" href="css/register.css">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css" integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>
<body>
<form class="register" action="RegisterController" onsubmit="return Check();" >		<!-- 前面不要加/,否則就是絕對路徑 -->
		<div class="box">
			<div class="login">
				<div class="loginBx">
					<h2><i class="fa-solid fa-right-to-bracket"></i> Sign up <i class="fa-solid fa-heart"></i></h2>
					<div class="form-group">
						<input type="text"  name="rs_username" id="rs_username" placeholder="用户名">
						<input type="password"  name="rs_userpwd" id="rs_userpwd" placeholder="密码">
						<input type="password" name="rs_qruserpwd" id="rs_qruserpwd" placeholder="确认密码" required>
						<input type="text" name="rs_tel" id="rs_tel" placeholder="电话号码" required>
						<input type="text" name="rs_email" id="rs_email" placeholder="邮箱号" required>
						<input type="text" name="rs_sex" id="rs_sex" placeholder="性别" required>
						<input type="submit" onclick="" value="Sign up">
					</div>
					<div class="group">
						<a href="retrieve.jsp">Forgot Password</a>
						<a href="login.jsp">Log in</a>
					</div>
				</div>
			</div>
		</div>
</form>

<script type="text/javascript" src="js/register.js"></script>

</body>
</html>