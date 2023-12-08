<%@page import="java.util.List"%>
<%@page import="cl.litscl.estructurageneralejb.model.Producto"%>
<%@page import="cl.litscl.estructurageneralejb.fk.ProductoFK"%>
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
if (session.getAttribute("renderizarVista") == "mostrarProductos") {
	List<Producto> productos = (List<Producto>)session.getAttribute("productos");
	ProductoFK fkProducto = new ProductoFK();
%>
	<h1>Listado de productos</h1>	
	<table border="5px">
		<tr>
			<th>Codigo</th>
			<th>Nombre</th>
			<th>Precio</th>
			<th>Categoria</th>
		</tr>
	<%
	for (Producto p : productos) {
	%>
		<tr>
			<td><%=p.getCodigo()%></td>
			<td><%=p.getNombre()%></td>
			<td><%=p.getPrecio()%></td>
			<td><%=fkProducto.getCategoriaNombre(p)%></td>
		</tr>
	<%
	}
	%>
	</table>
<%
	session.removeAttribute("productos");
	session.removeAttribute("renderizarVista");
}
else {
	response.sendRedirect(request.getContextPath() + "/Producto?vista=mostrar_productos");
}
%>
</body>
</html>