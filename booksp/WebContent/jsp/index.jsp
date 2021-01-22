<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link type="text/css" rel="stylesheet" href="../css/style.css" />
<script src="../js/choiceBooks.js" defer="defer"></script>
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
				action="${pageContext.request.contextPath }/pgQue">
				<input type="hidden" id="pageIndex" name="pageIndex" value="1" />
			</form>
			<form method="post" name="search" action="">
				搜索：<input class="input-text" type="text" name="keywords" /> <input
					type="submit" class="input-btn" disabled="disabled" value="" />
			</form>
		</div>
	</div>
	<div id="content" class="wrap">
		<div class="list bookList">
			<!-- 加入购物车 -->
			<form method="post" name="shopping" id="shoppingId"
				action="../shopping">
				<table id="b_table">
					<tr class="title">
						<th class="checker">选择</th>
						<th class="title">书名</th>
						<th class="price">价格</th>
						<th class="store">库存</th>
						<th class="view">图片预览</th>
					</tr>
					<c:forEach var="bk" items="${list }" varStatus="status">
						<tr class="odd">
							<td><input type="checkbox" name="select"
								value='<c:out value="${bk.bid }"/>' /></td>
							<td><c:out value="${bk.name }" /></td>
							<td><c:out value="${bk.price }" /></td>
							<td><c:out value="${bk.stock }" /></td>
							<td class="thumb"><input type="hidden" value="${bk.image }" /><img
								src="<%=request.getContextPath() %>/images/book/${bk.image }" /></td>
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
				<div class="button">
					<input class="input-btn" type="submit" name="bcar" value="" />
				</div>
			</form>
		</div>
	</div>

</body>
</html>
