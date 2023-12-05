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
if (session.getAttribute("renderizarVista") == "iniciarSesion") {
%>	
	<h1>Iniciar sesi�n</h1>	
	
	<form action="<%=request.getContextPath()%>/Usuario" method="POST">
		<table>
			<tr>
				<td>Rut</td>
				<td><input type="text" name="rut" required/></td>
			</tr>
			<tr>
				<td>Contrase�a</td>
				<td><input type="password" name="clave" required/></td>
			</tr>
		</table>
		<button name="opcion" value="2">Login</button>
	</form>
	
	<%
	if (session.getAttribute("errorLogin") == "Credenciales incorrectas") {
	%>
		<strong class="alerta-roja"><%=session.getAttribute("errorLogin")%></strong>
	<%
	}
	%>	
	
<%
	session.removeAttribute("errorLogin");
	session.removeAttribute("renderizarVista");
}
else {
	response.sendRedirect(request.getContextPath() + "/Usuario?vista=iniciar_sesion");
}
%>
</body>
</html>