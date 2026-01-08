<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
        <%@ page isELIgnored="false" %>
            <!DOCTYPE html>
            <html>

            <head>
                <meta charset="UTF-8">
                <link rel="stylesheet" href="/colegio/css/index.css">
                <link rel="stylesheet" href="/colegio/css/formularios.css">
                <title>Borrar Matriculaciones</title>
            </head>

            <body>
                <h1>Borrar matriculaciones</h1>
                <%@include file="/menu.html" %>
                    <div class="container">
                        <h2>Buscar Matriculaciones</h2>
                        <div class="form">
                            <form action="http://localhost:8080/colegio/matriculaciones/formularioBorrarMatriculaciones"
                                method="post">

                                <label for="nombreAlumno">Nombre Alumno</label>
                                <input type="text" id="nombreAlumno" name="nombreAlumno"><br>
                                <label for="asignatura">Asignatura</label>
                                <input type="text" id="asignatura" name="asignatura"><br>
                                <label for="fecha">Fecha</label>
                                <input type="date" id="fecha" name="fecha"><br>
                                <input type="submit" value="Buscar">
                            </form>
                        </div>
                    </div>

                    <c:forEach items="${lista}" var="matriculacion">
                        <div class="form">
                            <form action="http://localhost:8080/colegio/matriculaciones/borrarMatriculacion"
                                method="post">
                                <input type="hidden" name="id" value="${matriculacion.id}">

                                <p><strong>Alumno:</strong> ${matriculacion.nombreAlumno}</p>
                                <p><strong>Asignatura:</strong> ${matriculacion.nombreAsignatura}</p>
                                <p><strong>Fecha:</strong> ${matriculacion.fecha}</p>
                                <p><strong>Tasa:</strong> ${matriculacion.tasa}</p>

                                <input type="submit" value="Borrar">
                            </form>
                        </div>
                    </c:forEach>
            </body>

            </html>