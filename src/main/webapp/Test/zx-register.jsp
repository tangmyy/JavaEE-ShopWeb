<%@ page contentType="text/html; charset=GBK" pageEncoding="GBK" language="java"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>注册页面</title>
    <link rel="stylesheet" href="../css/public.css">
    <link rel="stylesheet" href="zx-register.css"/>

</head>
<body>
<div class="box">
    <h2>Sign in</h2>
    <form action="RegisterController" onsubmit="return Check();" >		<!-- 前面不要加/,否則就是絕對路徑 -->
        <div class="input-box">
            <label>账号</label>
            <input type="text" name="rs_username" id="rs_username" placeholder="账号" required>
        </div>
        <div class="input-box">
            <label>密码</label>
            <input type="password" name="rs_userpwd" id="rs_userpwd" placeholder="密码" required>
        </div>
        <div class="input-box">
            <label>确认密码</label>
            <input type="password" name="rs_qruserpwd" id="rs_qruserpwd" placeholder="确认密码" required>
        </div>
        <div class="input-box">
            <label>电话号码</label>
            <input type="text" name="rs_tel" id="rs_tel" placeholder="电话号码" required>
        </div>
        <div class="input-box">
            <label>邮箱号</label>
            <input type="text" name="rs_email" id="rs_email" placeholder="邮箱号" required>
        </div>
        <div class="input-box">
            <label>性别</label>
            <input type="text" name="rs_sex" id="rs_sex" placeholder="性别" required>
        </div>
        <div class="btn-box">
            <div>
                <input type="submit" onclick="" value="注册">
            </div>
        </div>
    </form>
</div>

<script type="text/javascript" src="../js/register.js"></script>

</body>
</html>