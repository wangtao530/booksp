<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style type="text/css" >
.page-bar {
	position: relative;
	margin-top: 10px;
}

.page-num-ul li {
	float: left;
}

.page-num-ul li a {
	display: inline-block;
	padding: 3px 5px;
	margin: 0px 3px;
	border: 1px solid #b8b8b8;
}

.page-num-ul a:hover, .page-num-ul .thisclass {
	border: 1px solid rgb(255, 128, 64);
	background-color: rgb(255, 128, 64);
	color: #FFF;
	text-decoration: none;
}

.page-key {
	width: 50px;
}

.page-btn {
	border: 1px solid #b8b8b8;
	background-color: rgb(255, 243, 227);
	display: inline-block;
	width: 52px;
	height: 25px;
	line-height: 25px;
	font-weight: 20px;
}

.page-go-form {
	position: absolute;
	display: inline-block;
	right: 50px;
	top: 0px;
}

.page-go-form input, label, button {
	margin: 0px 5px;
}
</style>
<script type="text/javascript">
	function page_nav(frm, num) {
		frm.pageIndex.value = num;
		frm.submit();
	}
	function jump_to(frm, pageNo) {
		var regexp = /^[1-9]\d*$/;
		//var totalPageCount = document.getElementById("totalPage").value;
		if (!regexp.test(pageNo)) {
			alert("请输入正确的数字");
			return false;
		} else {
			page_nav(frm, pageNo);
		}
	}
</script>
<div class="page-bar">
	<ul class="page-num-ul clearfix">
		<li>共<c:out value="${param.recordCount }" default="无"
				escapeXml="true" /> 条记录&nbsp;&nbsp;<c:out
				value="${param.curPageNo }" escapeXml="true" />/<c:out
				value="${param.pageCount }" escapeXml="true" />页&nbsp;&nbsp; <c:if
				test="${param.curPageNo > 1 }">
				<a href="javascript:page_nav(document.forms[0],1)">首页</a>
				<a
					href="javascript:page_nav(document.forms[0],<c:out value="${param.curPageNo - 1 }" escapeXml="true"/>)">上一页</a>
			</c:if> <c:if test="${param.curPageNo < param.pageCount}">
				<a
					href="javascript:page_nav(document.forms[0],<c:out value="${param.curPageNo + 1 }" escapeXml="true"/>)">下一页</a>
				<a
					href="javascript:page_nav(document.forms[0],<c:out value="${param.pageCount }" escapeXml="true"/>)">最后一页</a>
			</c:if>

		</li>
	</ul>
	<span class="page-go-form"><label>跳转至</label> <input type="text"
		name="inputPage" id="inputPage" class="page-key" />页
		<button type="button" class="page-btn"
			onclick='jump_to(document.forms[0],document.getElementById("inputPage").value)'>GO</button></span>
</div>

