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
                <title>Borrar Asignaturas</title>
            </head>

            <body>
                <h1>Borrar asignaturas</h1>
                <%@include file="/menu.html" %>
                    <div class="container">
                        <h2>Borrar Asignaturas</h2>
                        <div class="form">
                            <form action="http://localhost:8080/colegio/asignaturas/formularioBorrarAsignaturas"
                                method="post">

                                <label for="id">Id Asignatura</label>
                                <input type="text" id="id" name="id">
                                <label for="nombre">Nombre Asignatura</label>
                                <input type="text" id="nombre" name="nombre"><br>
                                <label for="curso">Curso</label>
                                <input type="text" id="curso" name="curso"><br>
                                <label for="tasa">Tasa (mayor a)</label>
                                <input type="text" id="tasa" name="tasa"><br>
                                Activo:
                                <input type="checkbox" id="activo" name="activo" value="1" checked><br>
                                <input type="submit" value="Enviar">
                            </form>
                        </div>
                    </div>

                    <c:if test="${not empty lista}">
                        <table>
                            <tr>
                                <th>ID</th>
                                <th>NOMBRE</th>
                                <th>CURSO</th>
                                <th>TASA</th>
                                <th>ACTIVO</th>
                                <th>ACCIÓN</th>
                            </tr>
                            <c:forEach items="${lista}" var="asignatura">
                                <tr>
                                    <td>${asignatura.id}</td>
                                    <td>${asignatura.nombre}</td>
                                    <td>${asignatura.curso}</td>
                                    <td>${asignatura.tasa}</td>
                                    <td>${asignatura.activo}</td>
                                    <td>
                                        <form action="http://localhost:8080/colegio/asignaturas/borrarAsignatura"
                                            method="post" style="display: inline;">
                                            <input type="hidden" name="id" value="${asignatura.id}">
                                            <input type="submit" value="Borrar"
                                                onclick="return confirm('¿Está seguro de que desea borrar esta asignatura?');">
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                    </c:if>

            </body>

            </html>