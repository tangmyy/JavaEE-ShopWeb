# JavaEE大作业 购物网站
## JSP + fetch

1.单选框会触发两次请求一myshopcart.jsp:49上有 onclick="updateByCheckedBox(...)",
而mycant.js:19又用addEventListener('change')调用了一次。结果是同一次勾选会发两个fetch,Bean里
checked被取反两次等于没变化但total 加减了两次,总价会错乱。建议删掉JSP里的onclick，只保留JS的事件绑定。
全选框也有同样问题(myshopcart.jsp:40的onclick引用了不存在的itemEntity).

2.全选框初始状态可能不准一 MyShopCartDate.java:15 构造时altchecked=true.但当用户单选取消某项后,
allChecked并未被更新。刷新页面时全选框仍会显示选中(因为JSP读的是allchecked),与实际不符。

3.addToCart 时 allChecked 不同步新加入购物车的项默 checked=true (MyShopCantDate.java:22),
但如果当前allChecked=false(用户已取消全选),新加的项却还是选中状态,逻辑不一致。