<%@page import="cl.litscl.estructurageneralejb.model.Usuario"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Document</title>
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