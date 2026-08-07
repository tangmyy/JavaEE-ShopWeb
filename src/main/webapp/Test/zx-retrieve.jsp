<%--
  Created by IntelliJ IDEA.
  User: TaoTao
  Date: 2024/6/17
  Time: 上午2:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=GBK" pageEncoding="GBK" language="java"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="GBK">
    <title>找回密码</title>
    <link rel="stylesheet" href="../css/public.css"/>
    <link rel="stylesheet" href="zx-retrieve.css"/>
</head>

<body>
    <div class="box">
    <h2>Retrieve</h2>

    <form action="RetrieveController" onsubmit="return passwordCheck();" >		<!-- 前面不要加/,否則就是絕對路徑 -->
        <div class="input-box">
            <label>账号</label>
            <input type="text" name="Ret_username" id="Ret_username" placeholder="账号" required>
        </div>
        <div class="input-box">
            <label>新密码</label>
            <input type="password" name="Ret_userpwd" id="Ret_userpwd" placeholder="新密码" required>
        </div>
        <div class="input-box">
            <label>确认密码</label>
            <input type="password" name="Ret_qruserpwd" id="Ret_qruserpwd" placeholder="确认密码" required>
        </div>
        <input type="hidden" id="oldPwdMD5Check" name="oldPwdMD5Check">
        <div class="btn-box">
            <a href="zxlogin.jsp"> [登录页面] </a>
            <div>
                <input type="submit" onclick="" value="找回密码">
            </div>
        </div>
    </form>
    </div>
</body>

<script type="text/javascript" src="../js/retrieve.js"></script>
</html>
