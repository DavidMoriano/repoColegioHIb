<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="/colegio/css/index.css">
<link rel="stylesheet" type="text/css"
	href="/colegio/css/formularios.css">
<link rel="stylesheet" type="text/css" href="/colegio/css/tablas.css">
<meta charset="ISO-8859-1">
<title>Listado Alumnos</title>
</head>
<body>

	<h1>Listado alumnos</h1>
	<%@include file="/menu.html"%>

	<div class="container">
		<div class="form">
			<form action="http://localhost:8080/colegio/alumnos/listadoAlumnos"
				method="post">
				<label for="id">Id Alumno</label> 
				<input type="text" id="id" name="id"> 
				<label for="nombre">Nombre Alumno</label> 
				<input type="text" id="nombre" name="nombre"><br> 
				<label for="apellido">Apellido Alumno</label> 
				<input type="text" id="apellido" name="apellido"><br>
					Familia Numerosa: 
				<input type="checkbox" id="famNumerosa" name="famNumerosa" value="1" checked> 
				Activo: 
				<input type="checkbox" id="activo" name="activo" value="1" checked><br> 
				<input type="submit" value="Enviar">
			</form>
		</div>
	</div>

	<c:if test="${empty lista}">
		<h2>No hay resultados que mostrar con esos filtros</h2>
	</c:if>
	<c:if test="${not empty lista}">
		<table>
			<tr>
				<th>ID</th>
				<th>NOMBRE</th>
				<th>APELLIDO</th>
				<th>MUNICIPIO</th>
			</tr>
			<c:forEach items="${lista}" var="alumno">
				<tr>
					<td>${alumno.id}</td>
					<td>${alumno.nombre}</td>
					<td>${alumno.apellido}</td>
					<td>${alumno.municipio}</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>

</body>
</html>

