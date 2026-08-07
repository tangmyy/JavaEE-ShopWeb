<%@ page contentType="text/html; charset=GBK" pageEncoding="GBK" language="java"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="GBK">
    <title>购物车</title>
    <link rel="stylesheet" href="css/myshopcart.css"/>
</head>
<body>
<div class="top">
    <div>
        <c:if test="${empty user}">
            <div class="login-register-container">
                <a class="btn-animate btn-animate__vibrate" href="login.jsp">亲，请登录</a>
                <a class="btn-animate btn-animate__vibrate" href="register.jsp">注册</a>
            </div>
        </c:if>
        <c:if test="${!empty user}">
            <div class="welcome-container">
                欢迎您：<span class="username">${user.name}</span>
                <a class="btn-animate btn-animate__vibrate" href="LoginController?logout">退出</a>
            </div>
        </c:if>
    </div>
    <a class="btn-animate btn-animate__vibrate" href="HomeController">首页</a>
</div>
<hr>
<header>
    <div class="header-logo">
        <img alt="logo" src="image/shopcart/ShopCart.png">
    </div>
</header>
<c:if test="${empty myShopCartDate.cartList}">
    <h1>购物车空空如也，赶快去购物！</h1>
</c:if>
<c:if test="${!empty myShopCartDate.cartList}">
    <main>
        <div class="itemTitle">
            <input type="checkbox" id="select-all" ${myShopCartDate.allChecked?"checked":""} onclick ="updateAllCheckedBox(${itemEntity.value.commodity.id})"/> 全选
            <img class="cell cell1" alt="" src="image/empty.png">
            <div class="cell cell3">商品信息</div>
            <div class="cell cell1">商品价格</div>
            <div class="cell cell1">数量</div>
            <div class="cell cell1">操作</div>
        </div>
        <c:forEach var="itemEntity" items="${myShopCartDate.cartList}">
        <div class="item" data-id="${itemEntity.value.commodity.id}" data-num="${itemEntity.value.num}" >
            <input class="item-checkbox" type="checkbox" data-id="${itemEntity.value.commodity.id}" data-num="${itemEntity.value.num}" onclick ="updateByCheckedBox(${itemEntity.value.commodity.id})" ${itemEntity.value.checked?"checked":""}/>
            <img class="cell" src="${itemEntity.value.commodity.imgpath}"/>
            <div class="cell cell3">${itemEntity.value.commodity.description}</div>
            <div class="cell cell1 price">${itemEntity.value.commodity.price}</div>
            <div class="cell cell1">
                <button class="num-btn" onclick="updateByNum(${itemEntity.value.commodity.id}, -1)">-</button>
                <span class="num-display">${itemEntity.value.num}</span>
                <button class="num-btn" onclick="updateByNum(${itemEntity.value.commodity.id}, 1)">+</button>
            </div>
            <div class="cell cell1">
                <button class="remove-btn" onclick="remove(${itemEntity.value.commodity.id})">移除</button>
            </div>
        </div>
        </c:forEach>
    </main>
</c:if>
<div class="total">
    <div class="totalContent">
        总计（不含运费）：<span class="total-price">${myShopCartDate.total}</span>
        <button>结算</button>
    </div>
</div>
<script type="text/javascript" src="js/home.js"></script>
<script type="text/javascript" src="js/mycart.js"></script>
</body>
</html>
