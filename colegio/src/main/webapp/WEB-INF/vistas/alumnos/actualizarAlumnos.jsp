<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/colegio/css/index.css">
<link rel="stylesheet" href="/colegio/css/formularios.css">
<title>Actualizar Alumnos</title>
</head>
<body>
	<h1>Actualizar alumnos</h1>
	<%@include file="/menu.html" %>
	<div class="container">
		<h2>Actualizar Alumnos</h2>
		<div class="form">
			<form action="http://localhost:8080/colegio/alumnos/formularioActualizarAlumnos" method="post">

				<label for="id">Id Alumno</label> 
				<input type="text" id="id" name="id"> 
				<label for="nombre">Nombre Alumno</label> 
				<input type="text" id="nombre" name="nombre"><br> 
				<label for="apellido">Apellido Alumno</label> 
				<input type="text" id="apellido" name="apellido"><br>
				Familia Numerosa: 
				<input type="checkbox" id="famNumerosa" name="famNumerosa" value="1" checked> 
				Activo: 
				<input type="checkbox" id="activo"name="activo" value="1" checked><br> 
				<input type="submit" value="Enviar">
			</form>
		</div>
	</div>
	
	<c:forEach items="${lista}" var="alumno">
		<div class="form">
			<form action="http://localhost:8080/colegio/alumnos/actualizarAlumno" method="post">
			    <label for="id"> Id Alumno </label>
				<input type="text" id="id" name="id" value="${alumno.id}" hidden>
				<label for="nombre">Nombre Alumno</label>
				<input type="text" id="nombre" name="nombre" value="${alumno.nombre}"><br>
				<label for="apellido">Apellido Alumno</label>
				<input type="text" id="apellido" name="apellido" value="${alumno.apellido}"><br>
				Familia Numerosa:
				<c:if test="${alumno.familiaNumerosa == 1}">
					<input type="checkbox" id="familiaNumerosa" name="familiaNumerosa" value="1" checked>
				</c:if>
				<c:if test="${alumno.familiaNumerosa == 0}">
					<input type="checkbox" id="familiaNumerosa" name="familiaNumerosa" value="1">
				</c:if>
				Activo:
				<c:if test="${alumno.activo == 1}">
					<input type="checkbox" id="activo" name="activo" value="1" checked><br>
				</c:if>
				<c:if test="${alumno.activo == 0}">
					<input type="checkbox" id="activo" name="activo" value="0" checked><br>
				</c:if>
				<label for="municipios">Municipio</label>
				<select name="municipio" id="municipios">
					<c:forEach items="${desplegableMunicipios}" var="municipio">
						<option value="${municipio.id}">  ${municipio.descripcion}</option>
					</c:forEach>
					<option value="${alumno.idMunicipio}" selected> ${alumno.municipio} </option>
				</select><br>

			<input type="submit" value="Modificar">
			</form>
		</div>
	</c:forEach>
</body>
</html>



