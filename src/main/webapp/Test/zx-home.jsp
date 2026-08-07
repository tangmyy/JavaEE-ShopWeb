<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" language="java"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>首页</title>
<link rel="stylesheet" href="../css/home.css"/>
</head>


<body>
	<table width="1024" border="0" align="center" cellpadding="0" cellspace="3">
		<thead>
			<tr>
				<td colspan="3" align="left">
					<a href="../login.jsp" >亲，请登录</a>
					<a href="#" >注册</a>
				</td>
				<td colspan="2" align="right">
					<a href="#" >购物车</a>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="left"><img src="../image/logo.png" width="400" height="100" alt="banner"/></td>
				<td colspan="3" align="left">
				<input type="text" name="" id="" >
				<input type="button" onclick="" value="搜索">
				</td>
			</tr>
			<tr>
				<td height="50" colspan="5" align="left" bgcolor=blanchedalmond>　　　
					<a href="#">首页</a> 　　　
					<a href="#">食品</a>　　　
					<a href="#">电器</a>　　　
					<a href="#">书籍</a>
				</td>
			</tr>
		
		</thead>
		<tbody>
			<tr>
				<td class="image" height="200" colspan="1" align="left">
					<img src="../image/book1.jpg" width="200" height="200" alt="banner"/>
					<a class="b">腹有诗书气自华</a><br><a>35.00</a>
				<td class="td-image" height="200" colspan="1" align="left">
					<img src="../image/book1.jpg" width="200" height="200" alt="banner"/>
					<a>腹有诗书气自华</a>
					<br>
					<a>35.00</a>
					<input class="input-button" type="button" value="加入购物车">
				</td>
				<td class="td-image" height="200" colspan="1" align="left">
					<img src="../image/book1.jpg" width="200" height="200" alt="banner"/>
					<a>腹有诗书气自华</a><br><a>35.00</a>
					<input class="input-button" type="button" value="加入购物车">
				</td>
				<td class="td-image" height="200" colspan="1" align="left">
					<img src="../image/book1.jpg" width="200" height="200" alt="banner"/>
					<a>腹有诗书气自华</a><br><a>35.00</a>
					<input class="input-button" type="button" value="加入购物车">
				</td>
				<td class="td-image" height="200" colspan="1" align="left">
					<img src="../image/book1.jpg" width="200" height="200" alt="banner"/>
					<a id="a-id">腹有诗书气自华</a>
					<br>
					<a>35.00</a>
					<input class="input-button" type="button" value="加入购物车">
				</td>
			</tr>
		</tbody>
		<tfoot>
			<tr>
				<td height="50" colspan="5" align="right" bgcolor=blanchedalmond>
					<ul>
						<li>&lt;</li>
						<li>1</li>
						<li>2</li>
						<li>3</li>
						<li>4</li>
						<li>5</li>
						<li>&gt;</li>
					</ul>
				</td>
			</tr>
		</tfoot>
	</table>
</body>
</html>
