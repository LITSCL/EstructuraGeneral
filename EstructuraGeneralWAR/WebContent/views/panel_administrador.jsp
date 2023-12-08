<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file='includes/head_styles.jsp' %>
<%@ include file='includes/head_scripts.jsp' %>
</head>
<body>
	<%@ include file='includes/header.jsp' %>

<% 
if (session.getAttribute("renderizarVista") == "panelAdministrador") {
%>	
	<h1>Panel administrador</h1>
<%
	session.removeAttribute("renderizarVista");
}
else {
	response.sendRedirect(request.getContextPath() + "/Usuario?vista=panel_administrador");
}
%>
</body>
</html>