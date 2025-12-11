package controllers.faltas;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import servicios.IFaltasService;
import serviciosImp.FaltasServiceImp;
import utils.DesplegableUtils;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Servlet implementation class InsertarFaltasController
 */
@WebServlet("/faltas/insertarFalta")
public class InsertarFaltasController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static Logger logger = LoggerFactory.getLogger(InsertarFaltasController.class);

    public InsertarFaltasController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Cargar desplegables de alumnos y asignaturas
        DesplegableUtils.recuperarDesplegableAlumnos(request);
        DesplegableUtils.recuperarDesplegableAsignaturas(request);

        RequestDispatcher d = getServletContext().getRequestDispatcher("/WEB-INF/vistas/faltas/insertarFalta.jsp");
        d.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Recuperamos los datos del formulario
        String idAlumno = request.getParameter("alumno");
        String idAsignatura = request.getParameter("asignatura");
        String fecha = request.getParameter("fecha");
        String justificada = request.getParameter("justificada");

        // Convertir justificada checkbox a int
        int justificadaInt;
        if (justificada != null)
            justificadaInt = 1;
        else
            justificadaInt = 0;

        // Si no se introduce fecha, usar fecha actual en formato YYYYMMDD
        String fechaFormateada;
        if (fecha == null || fecha.trim().isEmpty()) {
            fechaFormateada = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
            logger.info("Fecha no introducida, usando fecha actual: " + fechaFormateada);
        } else {
            // Convertir de YYYY-MM-DD a YYYYMMDD
            fechaFormateada = fecha.replace("-", "");
        }

        IFaltasService f = new FaltasServiceImp();
        Integer resultado = f.insertarFalta(idAlumno, idAsignatura, fechaFormateada, justificadaInt);

        request.setAttribute("resultado", resultado);

        // Recargar desplegables
        DesplegableUtils.recuperarDesplegableAlumnos(request);
        DesplegableUtils.recuperarDesplegableAsignaturas(request);

        RequestDispatcher d = getServletContext().getRequestDispatcher("/WEB-INF/vistas/faltas/insertarFalta.jsp");
        d.forward(request, response);
    }

}
