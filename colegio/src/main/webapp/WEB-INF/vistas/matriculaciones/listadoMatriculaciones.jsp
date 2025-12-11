<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
        <%@ page isELIgnored="false" %>
            <!DOCTYPE html>
            <html>

            <head>
                <link rel="stylesheet" type="text/css" href="/colegio/css/index.css">
                <link rel="stylesheet" type="text/css" href="/colegio/css/formularios.css">
                <link rel="stylesheet" type="text/css" href="/colegio/css/tablas.css">
                <meta charset="ISO-8859-1">
                <title>Listado Matriculaciones</title>
            </head>

            <body>

                <h1>Listado matriculaciones</h1>
                <%@include file="/menu.html" %>

                    <div class="container">
                        <div class="form">
                            <form action="http://localhost:8080/colegio/matriculaciones/listadoMatriculaciones"
                                method="post">
                                <label for="nombreAsignatura">Nombre Asignatura</label>
                                <input type="text" id="nombreAsignatura" name="nombreAsignatura"><br>
                                <label for="nombreAlumno">Nombre Alumno</label>
                                <input type="text" id="nombreAlumno" name="nombreAlumno"><br>
                                <label for="fecha">Fecha (desde)</label>
                                <input type="date" id="fecha" name="fecha"><br>
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
                                <th>NOMBRE ASIGNATURA</th>
                                <th>NOMBRE ALUMNO</th>
                                <th>FECHA</th>
                                <th>ACTIVO</th>
                                <th>TASA</th>
                            </tr>
                            <c:forEach items="${lista}" var="matriculacion">
                                <tr>
                                    <td>${matriculacion.nombreAsignatura}</td>
                                    <td>${matriculacion.nombreAlumno}</td>
                                    <td>${matriculacion.fecha}</td>
                                    <td>${matriculacion.activo}</td>
                                    <td>${matriculacion.tasa}</td>
                                </tr>
                            </c:forEach>
                        </table>
                    </c:if>

            </body>

            </html>