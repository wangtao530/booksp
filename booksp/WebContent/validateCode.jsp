<%@ page language="java"
	import="book.util.VerifyCode,java.awt.image.BufferedImage"
	contentType="image/jpeg; charset=UTF-8" pageEncoding="UTF-8"%>
<%
response.setHeader("Pragma", "No-cache");
response.setHeader("Cache-Control", "no-cache");
response.setDateHeader("Expires", 0);
response.setContentType("image/jpeg");
VerifyCode v = new VerifyCode();
BufferedImage i = v.getImage();
String c = v.getText();
request.getSession().setAttribute("vc", c);
VerifyCode.output(i, response.getOutputStream());
out.clear();
out = pageContext.pushBody();
%>