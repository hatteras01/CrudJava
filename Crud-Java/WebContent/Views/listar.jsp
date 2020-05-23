<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Listar Producto</title>
</head>
<body>
<h1>Listar Producto</h1>
<table border="1">
	<tr>
		<td>Id</td>
		<td>Nombre</td>
		<td>Cantidad</td>
		<td>Precio</td>
		<td>Fecha Creación</td>
		<td>Fecha Actualización</td>
		<!-- Añadimos otra columna para habilitar la eliminación del objeto -->
		<td>Accion</td>
	</tr>
	<!--var indentifica el tipo de objeto que vamos a representar
		items representa el objeto recibido desde el controlador-->
	<c:forEach var="producto" items="${lista }">
	<tr>
		<td><a href="Producto?opcion=meditar&id=<c:out value="${producto.id}"></c:out>"><c:out value="${producto.id}"></c:out></a></td>
		<td><c:out value="${producto.nombre}"></c:out></td>
		<td><c:out value="${producto.cantidad}"></c:out></td>
		<td><c:out value="${producto.precio}"></c:out></td>
		<td><c:out value="${producto.fechaCrear}"></c:out></td>
		<td><c:out value="${producto.fechaActualizar}"></c:out></td>
		<td><a href="Producto?opcion=eliminar&id=<c:out value="${producto.id}"></c:out>">Eliminar</a></td>
	</tr>
	</c:forEach>

</table>

</body>
</html>