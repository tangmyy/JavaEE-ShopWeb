<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" language="java" %>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>

<html>
	<head>
		<meta charset="utf-8">
		<title>首页</title>
		<link rel="stylesheet" href="css/home.css"/>
	</head>
	<body onload="setActiveElement(${homeData.sort},${homeData.curPage})">
		<div class="top">
				<div>
					<c:if test="${empty user}">
						<a class="btn-animate btn-animate__vibrate" href="login.jsp">亲，请登录</a>
						<a class="btn-animate btn-animate__vibrate" href="register.jsp">注册</a>
					</c:if>
					<c:if test="${!empty user}">
						<div class="welcome-container">
							欢迎您：<span class="username">${user.name}</span>
							<a class="btn-animate btn-animate__vibrate" href="LoginController?logout">退出</a>
						</div>
					</c:if>
				</div>
			<a  class="btn-animate btn-animate__vibrate" href="myshopcart.jsp"> 购物车 </a>
		</div>
		<hr>
		<header>
			<div class="header-logo">
				<img alt="logo" src="image/home/logo.png">
			</div>
			<form action="HomeController" method="get">
				<input class="searchText" type="search" name="keyword" >				<!--placeholder元素 底面提示-->
				<input class="searchBtn"type="submit" value="搜索"> 					<!-- ONCLICK="HomeServlet?keyword="-->
			</form>
		</header>
		<nav>
			<ul>
				<li><a id="sort0" href="HomeController?sort=0" class="${homeData.sort == 0 ? 'active' : ''}">首页</a></li>
				<li><a id="sort1" href="HomeController?sort=1" class="${homeData.sort == 1 ? 'active' : ''}">食品</a></li>
				<li><a id="sort2" href="HomeController?sort=2" class="${homeData.sort == 2 ? 'active' : ''}">电器</a></li>
				<li><a id="sort3" href="HomeController?sort=3" class="${homeData.sort == 3 ? 'active' : ''}">书籍</a></li>
			</ul>
		</nav>
		<main>
			<div class="content">
				<c:forEach var="commodity" items="${requestScope.homeData.commodityList}">	<!-- request.getAttribute("HomeData") .getCommodityList-->
					<div class="item">
						<img alt="" src="${commodity.imgpath}">
						<div class="item-1">${commodity.description}</div><br>
						<div class="item-2">
							<span class="price">${commodity.price}</span>
							<button onclick="addToCart(${commodity.id})">加入购物车</button>
<%--						<div>${commodity.price}<button onclick="addToCart(${commodity.id})">加入购物车</button></div>--%>
						</div>
					</div>
				</c:forEach>
			</div>
			<br>
			<div class="pagination">
				<c:if test="${homeData.curPage > 1}">
					<a href="HomeController?sort=${homeData.sort}&curPage=${homeData.curPage - 1}">«</a>
				</c:if>
				  <c:forEach var="i" begin="${requestScope.homeData.firstPageForNavigation}" end="${requestScope.homeData.lastPageForNavigation}">
					  <c:if test="${empty homeData.keyword}">
						  <a id="page${i}" href="HomeController?sort=${homeData.sort}&curPage=${i}">${i}</a>
					  </c:if>
					  <c:if test="${!empty homeData.keyword}">
						  <a id="page${i}" href="HomeController?keyword=${homeData.keyword}&curPage=${i}">${i}</a>
					  </c:if>
				  </c:forEach>
				<c:if test="${homeData.curPage < homeData.totalPages}">
					<a href="HomeController?sort=${homeData.sort}&curPage=${homeData.curPage + 1}">»</a>
				</c:if>			</div>
		</main>
		<hr>
		<footer>
			版权信息：信息学院
		</footer>
		<script type="text/javascript" src="js/mycart.js"></script>
		<script type="text/javascript" src="js/home.js"></script>
	</body>
</html>