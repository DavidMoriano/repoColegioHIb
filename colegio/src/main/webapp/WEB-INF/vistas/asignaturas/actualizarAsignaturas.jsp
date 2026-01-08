<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
        <%@ page isELIgnored="false" %>
            <!DOCTYPE html>
            <html>

            <head>
                <meta charset="UTF-8">
                <link rel="stylesheet" href="/colegio/css/index.css">
                <link rel="stylesheet" href="/colegio/css/formularios.css">
                <title>Actualizar Asignaturas</title>
            </head>

            <body>
                <h1>Actualizar asignaturas</h1>
                <%@include file="/menu.html" %>
                    <div class="container">
                        <h2>Actualizar Asignaturas</h2>
                        <div class="form">
                            <form action="http://localhost:8080/colegio/asignaturas/formularioActualizarAsignaturas"
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

                    <c:forEach items="${lista}" var="asignatura">
                        <div class="form">
                            <form action="http://localhost:8080/colegio/asignaturas/actualizarAsignatura" method="post">
                                <label for="id"> Id Asignatura </label>
                                <input type="text" id="id" name="id" value="${asignatura.id}" hidden>
                                <label for="nombre">Nombre Asignatura</label>
                                <input type="text" id="nombre" name="nombre" value="${asignatura.nombre}"><br>
                                <label for="curso">Curso</label>
                                <input type="text" id="curso" name="curso" value="${asignatura.curso}"><br>
                                <label for="tasa">Tasa</label>
                                <input type="text" id="tasa" name="tasa" value="${asignatura.tasa}"><br>
                                Activo:
                                <c:if test="${asignatura.activo == 1}">
                                    <input type="checkbox" id="activo" name="activo" value="1" checked>
                                </c:if>
                                <c:if test="${asignatura.activo == 0}">
                                    <input type="checkbox" id="activo" name="activo" value="1">
                                </c:if>
                                <br>

                                <input type="submit" value="Modificar">
                            </form>
                        </div>
                    </c:forEach>
            </body>

            </html>