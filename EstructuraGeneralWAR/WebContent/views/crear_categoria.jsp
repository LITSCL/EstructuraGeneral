<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Document</title>
	<%@ include file='includes/head_styles.jsp' %>
	<%@ include file='includes/head_scripts.jsp' %>
</head>
<body>
	<%@ include file='includes/header.jsp' %>

<% 
if (session.getAttribute("renderizarVista") == "crearCategoria") {
%>
	<h1>Estas en Crear Categoria</h1>
	<form action="<%=request.getContextPath()%>/Categoria" method="POST">
		<table border="5px">
			<tr>
				<td>Nombre</td>
				<td><input type="text" name="nombre" required/></td>
			</tr>
		</table>
		<button type="submit" name="opcion" value="1">Crear</button>
	</form>
	
	<%
	if (session.getAttribute("crearCategoria") == "Exitoso") {
	%>
		<strong class="alerta-verde">Categoria creada correctamente</strong>
	<%
	}
	if (session.getAttribute("crearCategoria") == "Fallido") {	
	%>
		<strong class="alerta-roja">Error al crear la categoria</strong>
	<%		
	}
	%>
<%
	session.removeAttribute("crearCategoria");
	session.removeAttribute("renderizarVista");
}
else {
	response.sendRedirect(request.getContextPath() + "/Categoria?vista=crear_categoria");
}
%>
</body>
</html>