<%@ page contentType="text/html; charset=GBK" pageEncoding="GBK" language="java"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="GBK">
    <title>登陆页面</title>
	<link rel="stylesheet" href="../css/public.css"/>
	<link rel="stylesheet" href="zx-login.css"/>
</head>
<body>
    <div class="box">
        <h2>Log in</h2>
        <form action="LoginController">		<!-- 前面不要加/,否則就是絕對路徑 -->
	        <div class="input-box">
	            <label>账号</label>
	            <input type="text" name="username" id="username" >
	        </div>
	        <div class="input-box">
	            <label>密码</label>
	            <input type="password" name="userpwd" id="userpwd" >
	        </div>
			<div class="btn-box">
				<a href="zxretrieve.jsp">忘记密码?</a>
				<div class="button button__around-2">
					<svg>
						<rect height="100%" width="100%"></rect>
					</svg>
					<input type="submit" value="登录" style="background: transparent; color: inherit; border: none; padding: 0; margin: 0; width: 100%; height: 100%; cursor: pointer;">
				</div>
				<div class="button button__around-2">
					<svg>
						<rect height="100%" width="100%"></rect>
					</svg>
					<a href="zxregister.jsp" style="display: block; width: 100%; height: 100%; color: inherit; text-decoration: none;">
						<span style="display: block; line-height: 35px;">注册</span>
					</a>
				</div>
			</div>
        </form>
	</div>


    </div>
</body>
</html>