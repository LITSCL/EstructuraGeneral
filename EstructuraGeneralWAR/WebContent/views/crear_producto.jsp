<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="cl.litscl.estructurageneralejb.model.Categoria"%>
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
if (session.getAttribute("renderizarVista") == "crearProducto") {
	List<Categoria> categorias = (List<Categoria>)session.getAttribute("categorias");
%>
	<h1>Estas en Crear Producto</h1>
	<form action="<%=request.getContextPath()%>/Producto" method="POST">
		<table border="5px">
			<tr>
				<td>Codigo</td>
				<td><input type="text" name="codigo" required/></td>
			</tr>
			<tr>
				<td>Nombre</td>
				<td><input type="text" name="nombre" required/></td>
			</tr>
			<tr>
				<td>Precio</td>
				<td><input type="number" name="precio" required/></td>
			</tr>
			<tr>
				<td>Categoria</td>
				<td>
					<select name="categoria">
						<c:forEach items="${categorias}" var="ca">
							<option value="${ca.id}">${ca.nombre}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
		</table>
		<button type="submit" name="opcion" value="1">Crear</button>
	</form>
	
	<%
	if (session.getAttribute("crearProducto") == "Exitoso") {
	%>
		<strong class="alerta-verde">Producto creado correctamente</strong>
	<%
	}
	if (session.getAttribute("crearProducto") == "Fallido") {	
	%>
		<strong class="alerta-roja">Error al crear el producto</strong>
	<%		
	}
	%>
<%
	session.removeAttribute("crearProducto");
	session.removeAttribute("categorias");
	session.removeAttribute("renderizarVista");
}
else {
	response.sendRedirect(request.getContextPath() + "/Producto?vista=crear_producto");
}
%>
</body>
</html>