<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ page isELIgnored="false"%>
<html>
<head>
<link rel="stylesheet" href="/colegio/css/index.css">
<link rel="stylesheet" href="/colegio/css/formularios.css">
</head>
<body>
<header>
<h2>Inserción de nuevo alumno</h2>
</header>
<%@include file="/menu.html"%>

<div class="container">
<div class="form">
<form action="http://localhost:8080/colegio/alumnos/insertarAlumno" method="post">
    <div id="formulario">
        <label for="id">DNI Alumno</label>
        <input type="text" id="id" name="id">
        <label for="nombre">Nombre Alumno</label>
        <input type="text" id="nombre" name="nombre"><br>
        <label for="apellido">Apellido Alumno</label>
        <input type="text" id="apellido" name="apellido"><br>

        <label for="famNumerosa">Familia Numerosa:</label>
        <input type="checkbox" id="famNumerosa" name="famNumerosa" value="1" checked>

        <label for="activo">Activo:</label>
        <input type="checkbox" id="activo" name="activo" value="1" checked>

        <select name="municipios" id="municipios">
            <c:forEach items="${desplegableMunicipios}" var="municipio">
                <option value="${municipio.id}">${municipio.descripcion}</option>
            </c:forEach>
        </select>
        <input type="submit" value="Enviar">
    </div>
    </form>
    
    <c:if test="${resultado == 1}">
		<b>Alumno insertado correctamente</b>
	</c:if>
</div>
</div>
</body>