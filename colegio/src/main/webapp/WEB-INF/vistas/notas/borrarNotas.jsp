<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
        <%@ page isELIgnored="false" %>
            <!DOCTYPE html>
            <html>

            <head>
                <meta charset="UTF-8">
                <link rel="stylesheet" href="/colegio/css/index.css">
                <link rel="stylesheet" href="/colegio/css/formularios.css">
                <link rel="stylesheet" href="/colegio/css/tablas.css">
                <title>Borrar Notas</title>
            </head>

            <body>
                <h1>Borrar notas</h1>
                <%@include file="/menu.html" %>
                    <div class="container">
                        <h2>Borrar Notas</h2>
                        <div class="form">
                            <form action="http://localhost:8080/colegio/notas/formularioBorrarNotas" method="post">

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

                    <c:if test="${not empty lista}">
                        <table>
                            <tr>
                                <th>ID ALUMNO</th>
                                <th>NOMBRE ALUMNO</th>
                                <th>ASIGNATURA</th>
                                <th>NOTA</th>
                                <th>FECHA</th>
                                <th>ACCIÓN</th>
                            </tr>
                            <c:forEach items="${lista}" var="nota">
                                <tr>
                                    <td>${nota.idAlumno}</td>
                                    <td>${nota.nombreAlumno}</td>
                                    <td>${nota.nombreAsignatura}</td>
                                    <td>${nota.nota}</td>
                                    <td>${nota.fecha}</td>
                                    <td>
                                        <form action="http://localhost:8080/colegio/notas/borrarNota" method="post"
                                            style="display: inline;">
                                            <input type="hidden" name="id" value="${nota.id}">
                                            <input type="submit" value="Borrar"
                                                onclick="return confirm('¿Está seguro de que desea borrar esta nota?');">
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                    </c:if>

            </body>

            </html>