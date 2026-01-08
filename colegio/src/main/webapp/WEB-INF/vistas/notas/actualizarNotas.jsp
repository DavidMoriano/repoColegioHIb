<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
        <%@ page isELIgnored="false" %>
            <!DOCTYPE html>
            <html>

            <head>
                <meta charset="UTF-8">
                <link rel="stylesheet" href="/colegio/css/index.css">
                <link rel="stylesheet" href="/colegio/css/formularios.css">
                <title>Actualizar Notas</title>
            </head>

            <body>
                <h1>Actualizar notas</h1>
                <%@include file="/menu.html" %>
                    <div class="container">
                        <h2>Actualizar Notas</h2>
                        <div class="form">
                            <form action="http://localhost:8080/colegio/notas/formularioActualizarNotas" method="post">

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

                    <c:forEach items="${lista}" var="nota">
                        <div class="form">
                            <form action="http://localhost:8080/colegio/notas/actualizarNota" method="post">
                                <input type="hidden" name="id" value="${nota.id}">

                                <label for="alumno">Alumno</label>
                                <select name="alumno" id="alumno">
                                    <c:forEach items="${desplegableAlumnos}" var="alumno">
                                        <c:if test="${alumno.id == nota.idAlumno}">
                                            <option value="${alumno.id}" selected>${alumno.descripcion}</option>
                                        </c:if>
                                        <c:if test="${alumno.id != nota.idAlumno}">
                                            <option value="${alumno.id}">${alumno.descripcion}</option>
                                        </c:if>
                                    </c:forEach>
                                </select><br>

                                <label for="asignatura">Asignatura</label>
                                <select name="asignatura" id="asignatura">
                                    <c:forEach items="${desplegableAsignaturas}" var="asignatura">
                                        <c:if test="${asignatura.id == nota.idAsignatura}">
                                            <option value="${asignatura.id}" selected>${asignatura.descripcion}</option>
                                        </c:if>
                                        <c:if test="${asignatura.id != nota.idAsignatura}">
                                            <option value="${asignatura.id}">${asignatura.descripcion}</option>
                                        </c:if>
                                    </c:forEach>
                                </select><br>

                                <label for="nota">Nota</label>
                                <input type="text" id="nota" name="nota" value="${nota.nota}"><br>

                                <label for="fecha">Fecha</label>
                                <input type="date" id="fecha" name="fecha" value="${nota.fecha}"><br>

                                <input type="submit" value="Modificar">
                            </form>
                        </div>
                    </c:forEach>
            </body>

            </html>