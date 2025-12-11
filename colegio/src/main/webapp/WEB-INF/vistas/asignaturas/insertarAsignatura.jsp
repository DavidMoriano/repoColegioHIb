<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
        <%@ page isELIgnored="false" %>
            <!DOCTYPE html>
            <html>

            <head>
                <meta charset="UTF-8">
                <link rel="stylesheet" href="/colegio/css/index.css">
                <link rel="stylesheet" href="/colegio/css/formularios.css">
                <title>Insertar Asignatura</title>
            </head>

            <body>
                <h1>Insertar asignatura</h1>
                <%@include file="/menu.html" %>
                    <div class="container">
                        <h2>Insertar Asignatura</h2>
                        <div class="form">
                            <form action="http://localhost:8080/colegio/asignaturas/insertarAsignatura" method="post">
                                <label for="id">Id Asignatura</label>
                                <input type="text" id="id" name="id" required>
                                <label for="nombre">Nombre Asignatura</label>
                                <input type="text" id="nombre" name="nombre" required><br>
                                <label for="curso">Curso</label>
                                <input type="text" id="curso" name="curso" required><br>
                                <label for="tasa">Tasa</label>
                                <input type="text" id="tasa" name="tasa" required><br>
                                Activo:
                                <input type="checkbox" id="activo" name="activo" value="1" checked><br>
                                <input type="submit" value="Insertar">
                            </form>
                        </div>
                    </div>

                    <c:if test="${not empty resultado}">
                        <c:if test="${resultado > 0}">
                            <h2 style="color: green;">Asignatura insertada correctamente</h2>
                        </c:if>
                        <c:if test="${resultado == 0}">
                            <h2 style="color: red;">Error al insertar la asignatura</h2>
                        </c:if>
                    </c:if>

            </body>

            </html>