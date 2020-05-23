<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Editar Producto</title>
</head>
<body>
<h1>Editar Producto</h1>
<!--Hemos copiado el form de crear.jsp y adaptamos el formato y los valores a la nueva jsp-->
<form action="Producto" method="post">
<!-- Con esta etiqueta asignamos a la variable definida lo que nos viene del controlador -->
<c:set var="producto" value="${producto }"></c:set>
	<input type="hidden" name="opcion" value="editar">
<!-- Otro campo oculto para ue el controlador tenga el id del producto a editar -->
	<input type="hidden" name="id" value="${producto.id}">
	<table border="1">
		<tr>
			<td>Nombre:</td>
			<td><input type="text" name="nombre" size=50 value="${producto.nombre}"></td>
		</tr>
		<tr>
			<td>Cantidad:</td>
			<td><input type="text" name="cantidad" size=50 value="${producto.cantidad}"></td>
		</tr><tr>
			<td>Precio:</td>
			<td><input type="text" name="precio" size=50 value="${producto.precio}"></td>
		</tr>
	</table>
	<input type="submit" value="Guardar">
</form>

</body>
</html>