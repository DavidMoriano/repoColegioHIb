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
                <title>Listado Faltas</title>
            </head>

            <body>

                <h1>Listado faltas</h1>
                <%@include file="/menu.html" %>

                    <div class="container">
                        <div class="form">
                            <form action="http://localhost:8080/colegio/faltas/listadoFaltas" method="post">
                                <label for="nombreAlumno">Nombre Alumno</label>
                                <input type="text" id="nombreAlumno" name="nombreAlumno"><br>
                                <label for="asignatura">Asignatura</label>
                                <input type="text" id="asignatura" name="asignatura"><br>
                                <label for="fecha">Fecha (desde - dejar vacío para hoy)</label>
                                <input type="date" id="fecha" name="fecha"><br>
                                Justificada:
                                <input type="checkbox" id="justificada" name="justificada" value="1"><br>
                                <input type="submit" value="Buscar">
                            </form>
                        </div>
                    </div>

                    <c:if test="${empty lista}">
                        <h2>No hay resultados que mostrar con esos filtros</h2>
                    </c:if>
                    <c:if test="${not empty lista}">
                        <table>
                            <tr>
                                <th>ID ALUMNO</th>
                                <th>NOMBRE ALUMNO</th>
                                <th>ASIGNATURA</th>
                                <th>FECHA</th>
                                <th>JUSTIFICADA</th>
                            </tr>
                            <c:forEach items="${lista}" var="falta">
                                <tr>
                                    <td>${falta.idAlumno}</td>
                                    <td>${falta.nombreAlumno}</td>
                                    <td>${falta.nombreAsignatura}</td>
                                    <td>${falta.fecha}</td>
                                    <td>
                                        <c:if test="${falta.justificada == 1}">Sí</c:if>
                                        <c:if test="${falta.justificada == 0}">No</c:if>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                    </c:if>

            </body>

            </html>