<%@page import="java.util.List"%>
<%@page import="cl.litscl.estructurageneralejb.model.Usuario"%>
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
if (session.getAttribute("renderizarVista") == "mostrarUsuarios") {
	List<Usuario> usuarios = (List<Usuario>)session.getAttribute("usuarios");
%>
	<h1>Listado de usuarios</h1>	
	<table border="5px">
		<tr>
			<th>Rut</th>
			<th>Nombre</th>
			<th>Apellido</th>
			<th>Email</th>
			<th>Clave</th>
			<th>Tipo</th>
		</tr>
	<%
	for (Usuario u : usuarios) {
	%>
		<tr>
			<td><%=u.getRut()%></td>
			<td><%=u.getNombre()%></td>
			<td><%=u.getApellido()%></td>
			<td><%=u.getEmail()%></td>
			<td><%=u.getClave()%></td>
			<td><%=u.getTipo()%></td>
		</tr>
	<%
	}
	%>
	</table>
<%
	session.removeAttribute("usuarios");
	session.removeAttribute("renderizarVista");
}
else {
	response.sendRedirect(request.getContextPath() + "/Usuario?vista=mostrar_usuarios");
}
%>
</body>
</html>