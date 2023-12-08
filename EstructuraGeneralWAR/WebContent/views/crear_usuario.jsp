<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
if (session.getAttribute("renderizarVista") == "crearUsuario") {
%>
	<h1>Estas en Crear Usuario</h1>
	<form action="<%=request.getContextPath()%>/Usuario" method="POST">
		<table border="5px">
			<tr>
				<td>Rut</td>
				<td><input type="text" name="rut" placeholder="19.757.106-3" required/></td>
			</tr>
			<tr>
				<td>Nombre</td>
				<td><input type="text" name="nombre" required/></td>
			</tr>
			<tr>
				<td>Apellido</td>
				<td><input type="text" name="apellido" required/></td>
			</tr>
			<tr>
				<td>Email</td>
				<td><input type="text" name="email" required/></td>
			</tr>
			<tr>
				<td>Clave</td>
				<td><input type="password" name="clave" required/></td>
			</tr>
			<tr>
				<td>Tipo</td>
				<td><input type="text" name="tipo" required/></td>
			</tr>
		</table>
		<button type="submit" name="opcion" value="1">Crear</button>
	</form>
	
	<%
	if (session.getAttribute("crearUsuario") == "Exitoso") {
	%>
		<strong class="alerta-verde">Usuario creado correctamente</strong>
	<%
	}
	if (session.getAttribute("crearUsuario") == "Fallido") {	
	%>
		<strong class="alerta-roja">Error al crear el usuario</strong>
	<%		
	}
	if (session.getAttribute("crearUsuario") == "Fallido" && session.getAttribute("errores") != null) {
		List<String> errores = (List<String>)session.getAttribute("errores");
	%>
		<div class="listado-errores">
			<ul>	
			<c:forEach items="${errores}" var="e">
				<li>${e}</li>
			</c:forEach>
			</ul>
		</div>
	<%
	}
	%>
<%
	session.removeAttribute("crearUsuario");
	session.removeAttribute("errores");
	session.removeAttribute("renderizarVista");
}
else {
	response.sendRedirect(request.getContextPath() + "/Usuario?vista=crear_usuario");
}
%>
</body>
</html>