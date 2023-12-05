<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="cl.litscl.estructurageneralejb.model.Categoria"%>
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
if (session.getAttribute("renderizarVista") == "mostrarCategorias") {
	List<Categoria> categorias = (List<Categoria>)session.getAttribute("categorias");
%>
	<h1>Listado de categorias</h1>	
	<table border="5px">
		<tr>
			<th>ID</th>
			<th>Nombre</th>
		</tr>
		<c:forEach items="${categorias}" var="c">
			<tr>
				<td>${c.id}</td>
				<td>${c.nombre}</td>
			</tr>
		</c:forEach>
	</table>
<%
	session.removeAttribute("categorias");
	session.removeAttribute("renderizarVista");
}
else {
	response.sendRedirect(request.getContextPath() + "/Categoria?vista=mostrar_categorias");
}
%>
</body>
</html>