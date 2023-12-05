<%@page import="cl.litscl.estructurageneralejb.model.Usuario"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/styles.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/vendor/jquery-3.5.0/jquery-3.5.0.min.js"></script>
</head>
<body>
<% 
if (session.getAttribute("renderizarVista") == "index") {
%>
	<%@ include file='views/includes/header.jsp' %>
		
	<% 
	if (session.getAttribute("usuario") != null) {
		Usuario u = (Usuario)session.getAttribute("usuario");
	%>	
		<%
		if (u.getTipo().equals("Administrador")) {
		%>
			<br><h1>Bienvenido Administrador</h1>
			<a href="<%=request.getContextPath()%>/Usuario?opcion=1">Cerrar Sesión</a>
		<%
		}
		if (u.getTipo().equals("Cliente")) {
		%>
			<br><h1>Bienvenido Cliente</h1>
			<a href="<%=request.getContextPath()%>/Usuario?opcion=1">Cerrar Sesión</a>	
	<%
		}
	}
	else {
	%>
		<br><h1>No hay sesiones activas</h1>
	<%
	}
	%>
<%
	session.removeAttribute("renderizarVista");
}
else {
	response.sendRedirect(request.getContextPath() + "/Index");
}
%>
</body>
</html>