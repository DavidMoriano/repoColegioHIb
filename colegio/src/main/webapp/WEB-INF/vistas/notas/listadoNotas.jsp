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
                <title>Listado Notas</title>
            </head>

            <body>

                <h1>Listado notas</h1>
                <%@include file="/menu.html" %>

                    <div class="container">
                        <div class="form">
                            <form action="http://localhost:8080/colegio/notas/listadoNotas" method="post">
                                <label for="idAlumno">Id Alumno</label>
                                <input type="text" id="idAlumno" name="idAlumno">
                                <label for="nombreAlumno">Nombre Alumno</label>
                                <input type="text" id="nombreAlumno" name="nombreAlumno"><br>
                                <label for="asignatura">Asignatura</label>
                                <input type="text" id="asignatura" name="asignatura"><br>
                                <label for="nota">Nota</label>
                                <input type="text" id="nota" name="nota"><br>
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
                                <th>ID ALUMNO</th>
                                <th>NOMBRE ALUMNO</th>
                                <th>ASIGNATURA</th>
                                <th>NOTA</th>
                                <th>FECHA</th>
                            </tr>
                            <c:forEach items="${lista}" var="nota">
                                <tr>
                                    <td>${nota.idAlumno}</td>
                                    <td>${nota.nombreAlumno}</td>
                                    <td>${nota.nombreAsignatura}</td>
                                    <td>${nota.nota}</td>
                                    <td>${nota.fecha}</td>
                                </tr>
                            </c:forEach>
                        </table>
                    </c:if>

            </body>

            </html>