<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
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