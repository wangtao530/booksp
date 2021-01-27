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
<script src="./js/ckAlltextValidate.js" defer="defer" ></script>
</head>
<body>
	<div id="login">
		<h2>用户登陆</h2>
		<form id="loginForm" method="post" action="<%=request.getContextPath()%>/login.do">

			<input type="hidden" id="checkName" name="checkName" value="#" />
			<dl>
				<dt>用户名：</dt>
				<dd>
					<c:if test='${!empty cookie["username"].value }'>
						<input class="input-text" type="text" name="userName"
							id="userName"
							value="<c:out value='${cookie["username"].value }' />" />
						<span id="userNameSP"></span>
					</c:if>
					<c:if test='${empty cookie["username"].value }'>
						<input class="input-text" type="text" name="userName"
							id="userName" value="" />
						<span id="userNameSP"></span>
					</c:if>
				</dd>
				<dt>密 码：</dt>
				<dd>
					<input class="input-text" type="password" name="passWord"
						id="passWord" value="" /><span id="passWordSP"></span>
				</dd>
				<dt>验证码：</dt>
				<dd>
					<input class="input-text" type="text" name="yzm"
						id="yzm" value="" /><span id="yzmSP"></span>
						<img src="./ValidateCode" alt="验证码" onclick="this.src=this.src+'?'+Math.random()" id="img"/><a href="#" onclick="document.getElementById('img').onclick()">&nbsp;换?</a>
				</dd>
				<dt></dt>
				<dd class="button">
					<input class="input-btn" type="submit" name="submit" value="" /><input
						class="input-reg" type="button" name="register" value=""
						onclick="window.location='<%=request.getContextPath()%>/jsp/register.jsp'" />
				</dd>
			<%-- 	<%=request.getParameter("mess") %> --%>
				<c:out value="${param.mess }" />
			<%-- 	<c:remove var="mess" /> --%>
			</dl>
		</form>
	</div>
</body>
</html>
