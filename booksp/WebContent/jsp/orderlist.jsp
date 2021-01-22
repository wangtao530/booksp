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
			<form method="get" name="pageQuery"
				action="${pageContext.request.contextPath }/OrdersPageQuery">
				<input type="hidden" id="pageIndex" name="pageIndex" value="1" />
			</form>
			<form method="post" name="search" action="#">
				搜索：<input class="input-text" type="text" name="keywords" /> <input
					type="submit" class="input-btn" disabled="disabled" value="" />
			</form>
		</div>
	</div>
	<div id="content" class="wrap">
		<div class="list orderList">
			<table>
				<tr class="title">
					<th class="orderId">订单编号</th>
					<th class="userName">收货人</th>
					<th class="createTime">下单时间</th>
					<th class="totalPrice">总价</th>
					<th>订单商品</th>
					<th class="goodsName">商品名称</th>
					<th class="price">商品单价</th>
					<th class="count">商品数量</th>
				</tr>
				<c:if test="${empty list }">
						<tr>
							<td colspan="7"><a href="../pgQue">您还没有订单，看看有什么需要的</a></td>
						</tr>
					</c:if>
				<c:forEach var="queOrder" items="${list }">
					<tr>
						<td><c:out value="${queOrder.iid }" default="无" /></td>
						<td><c:out value="${queOrder.username }" default="无" /></td>
						<td><c:out value="${queOrder.createdate }" default="无" /></td>
						<td><c:out value="${queOrder.totalprice }" default="无" /></td>
						<td class="thumb"><img
							src="<%=request.getContextPath() %>/images/book/${queOrder.image }"/></td>
						<td><c:out value="${queOrder.name }" default="无" /></td>
						<td><c:out value="${queOrder.price }" default="无" /></td>
						<td><c:out value="${queOrder.count }" default="无" /></td>
					</tr>
				</c:forEach>
				<c:remove var="list" scope="session" />
			</table>
			<!-- 分页条的复用 -->
			<c:import url="rollPage.jsp">
				<c:param name="recordCount" value="${curPage.recordCount }" />
				<c:param name="curPageNo" value="${curPage.curPageNo }" />
				<c:param name="pageCount" value="${curPage.pageCount }" />
			</c:import>
			<c:remove var="curPage" />
		</div>
	</div>

</body>
</html>
