<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/css/style.css" />
<script src="../js/computePrice.js" defer="defer"></script>
</head>
<body>
	<div id="header" class="wrap">
		<div id="logo">北大青鸟网上书城</div>
		<div id="navbar">
			<div class="userMenu">
				<ul>
					<c:if test='${!empty user }'>
						<li>欢迎您，<c:out value='${user.userName }' /></li>
					</c:if>
					<li id="mark"><a href="../pgQue">首页</a></li>
					<li><a href="../OrdersPageQuery">我的订单</a></li>
					<li class="current"><a href="shopping.jsp">购物车</a></li>
					<li><a href="../logOut">注销</a></li>
				</ul>
			</div>
			<form method="post" name="search" action="#">
				搜索：<input class="input-text" type="text" name="keywords" /> <input
					type="submit" class="input-btn" disabled="disabled" value="" />
			</form>
		</div>
	</div>
	<div id="content" class="wrap">
		<div class="list bookList">
			<form method="post" name="shoping" action="../PayShoppingServlet"
				onsubmit='return confirm("确认购买?")'>
				<table>
					<tr class="title">
						<th class="view">图片预览</th>
						<th class="title">书名</th>
						<th class="nums">数量</th>
						<th class="price">价格</th>
						<th class="price">操作</th>
					</tr>
					<c:if test="${empty b_list }">
						<tr>
							<td colspan="4"><a href="../pgQue">您的购物车空空如也，赶快去看看有什么需要的吧</a></td>
						</tr>
					</c:if>

					<c:forEach var="book" items="${b_list}" varStatus="status">
						<tr class="odd">
							<td style="display: none;"><input type="hidden" name="b_id"
								value='<c:out value="${book.bid }" />' /></td>
							<td class="thumb"><img
								src="<%=request.getContextPath() %>/images/book/${book.image }" /></td>
							<td><c:out value="${book.name }" /></td>
							<td><input class="input-text" type="text" name="b_nums"
								id="b_nums" value="${book.bcount }" /></td>
							<td><input type="hidden" name="b_price"
								value="${book.price}" />￥<span class="ebt_price">${book.price * book.bcount }</span></td>
							<td><span id="remove_1"><a
									href="../delShoppingCart?id=${book.bid }">移除</a></span> <c:if
									test="${!empty t_mess }">
									<c:out value="${t_mess }" />
								</c:if></td>
						</tr>
					</c:forEach>

				</table>
				<div class="button">
					<h4>
						总价：￥<span id="count">0.00</span>元
					</h4>
					<input class="input-chart" type="submit" name="submit" value="" />
				</div>
			</form>
			<form id="u_b_count" action="../UpdShopping" method="post">
				<input type="hidden" name="u_b_id" id="u_b_id"/>
				<input type="hidden" name="u_b_nums" id="u_b_nums"/>
			</form>
		</div>
	</div>

</body>
</html>
