<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
        <%@ page isELIgnored="false" %>
            <!DOCTYPE html>
            <html>

            <head>
                <meta charset="UTF-8">
                <link rel="stylesheet" href="/colegio/css/index.css">
                <link rel="stylesheet" href="/colegio/css/formularios.css">
                <title>Insertar Matriculación</title>
            </head>

            <body>
                <h1>Insertar Matriculación</h1>
                <%@include file="/menu.html" %>
                    <div class="container">
                        <h2>Insertar Matriculación</h2>
                        <div class="form">
                            <form action="http://localhost:8080/colegio/matriculaciones/insertarMatriculacion"
                                method="post">
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
                                        <option value="${asignatura.id}">
                                            ${asignatura.descripcion}
                                        </option>
                                    </c:forEach>
                                </select><br>

                                <label for="tasa">Tasa a pagar (€)</label>
                                <input type="number" id="tasa" name="tasa" readonly required><br>

                                <label for="fecha">Fecha (dejar vacío para fecha actual)</label>
                                <input type="date" id="fecha" name="fecha"><br>

                                <input type="submit" value="Insertar">
                            </form>
                        </div>
                    </div>

                    <c:if test="${not empty resultado}">
                        <c:if test="${resultado > 0}">
                            <h2 style="color: green;">Matriculación insertada correctamente</h2>
                        </c:if>
                        <c:if test="${resultado == 0}">
                            <h2 style="color: red;">Error al insertar la matriculación</h2>
                        </c:if>
                    </c:if>
            </body>
            
            <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
             <script>
    $(document).ready(function () {
        $(document).on('change', '#alumno, #asignatura', function () {
            $.ajax({
                type: "POST",
                url: "http://localhost:8080/colegio/tasa",
                data: {
                    alumnos: $("#alumno").val(),
                    asignaturas: $("#asignatura").val()
                },
                success: function (data) {
                    $("#tasa").val(data);
                }
            });
        });
    });
</script>

            </html>