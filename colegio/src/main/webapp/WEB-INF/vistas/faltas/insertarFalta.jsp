<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
        <%@ page isELIgnored="false" %>
            <!DOCTYPE html>
            <html>

            <head>
                <meta charset="UTF-8">
                <link rel="stylesheet" href="/colegio/css/index.css">
                <link rel="stylesheet" href="/colegio/css/formularios.css">
                <title>Poner Falta</title>
            </head>

            <body>
                <h1>Poner falta a alumno</h1>
                <%@include file="/menu.html" %>
                    <div class="container">
                        <h2>Insertar Falta</h2>
                        <div class="form">
                            <form action="http://localhost:8080/colegio/faltas/insertarFalta" method="post">
                                <label for="alumno">Alumno</label>
                                <select name="alumno" id="alumno" required>
                                    <option value="">Seleccione un alumno</option>
                                    <c:forEach items="${desplegableAlumnos}" var="alumno">
                                        <option value="${alumno.id}">${alumno.descripcion}</option>
                                    </c:forEach>
                                </select><br>

                                <label for="asignatura">Asignatura</label>
                                <select name="asignatura" id="asignatura" required>
                                    <option value="">Seleccione una asignatura</option>
                                    <c:forEach items="${desplegableAsignaturas}" var="asignatura">
                                        <option value="${asignatura.id}">${asignatura.descripcion}</option>
                                    </c:forEach>
                                </select><br>

                                <label for="fecha">Fecha (dejar vacío para fecha actual)</label>
                                <input type="date" id="fecha" name="fecha"><br>

                                Justificada:
                                <input type="checkbox" id="justificada" name="justificada" value="1"><br>

                                <input type="submit" value="Poner Falta">
                            </form>
                        </div>
                    </div>

                    <c:if test="${not empty resultado}">
                        <c:if test="${resultado > 0}">
                            <h2 style="color: green;">Falta insertada correctamente</h2>
                        </c:if>
                        <c:if test="${resultado == 0}">
                            <h2 style="color: red;">Error al insertar la falta</h2>
                        </c:if>
                    </c:if>

            </body>

            </html>