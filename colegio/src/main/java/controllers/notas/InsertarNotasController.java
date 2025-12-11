package controllers.notas;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import servicios.INotasService;
import serviciosImp.NotasServiceImp;
import utils.DesplegableUtils;

import java.io.IOException;
import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Servlet implementation class InsertarNotasController
 */
@WebServlet("/notas/insertarNota")
public class InsertarNotasController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static Logger logger = LoggerFactory.getLogger(InsertarNotasController.class);

    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertarNotasController() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Cargar desplegables de alumnos y asignaturas
        DesplegableUtils.recuperarDesplegableAlumnos(request);
        DesplegableUtils.recuperarDesplegableAsignaturas(request);

        RequestDispatcher d = getServletContext().getRequestDispatcher("/WEB-INF/vistas/notas/insertarNota.jsp");
        d.forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Recuperamos los datos del formulario
        String idAlumno = request.getParameter("alumno");
        String idAsignatura = request.getParameter("asignatura");
        String nota = request.getParameter("nota");
        String fecha = request.getParameter("fecha");

        // Si no se introduce fecha, usar fecha actual
        if (fecha == null || fecha.trim().isEmpty()) {
            fecha = LocalDate.now().toString();
            logger.info("Fecha no introducida, usando fecha actual: " + fecha);
        }

        INotasService n = new NotasServiceImp();
        Integer resultado = n.insertarNota(idAlumno, idAsignatura, nota, fecha);

        request.setAttribute("resultado", resultado);

        // Recargar desplegables
        DesplegableUtils.recuperarDesplegableAlumnos(request);
        DesplegableUtils.recuperarDesplegableAsignaturas(request);

        RequestDispatcher d = getServletContext().getRequestDispatcher("/WEB-INF/vistas/notas/insertarNota.jsp");
        d.forward(request, response);
    }

}
