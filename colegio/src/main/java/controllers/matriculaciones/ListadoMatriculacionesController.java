package controllers.matriculaciones;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import serviciosImp.MatriculacionesServiceImp;

import java.io.IOException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dto.MatriculacionDTO;
import servicios.IMatriculacionesService;

/**
 * Servlet implementation class ListadoMatriculacionesController
 */
@WebServlet("/matriculaciones/listadoMatriculaciones")
public class ListadoMatriculacionesController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static Logger logger = LoggerFactory.getLogger(ListadoMatriculacionesController.class);

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListadoMatriculacionesController() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher d = getServletContext()
                .getRequestDispatcher("/WEB-INF/vistas/matriculaciones/listadoMatriculaciones.jsp");
        d.forward(request, response);

    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nombreAsignatura = request.getParameter("nombreAsignatura");
        String nombreAlumno = request.getParameter("nombreAlumno");
        String fecha = request.getParameter("fecha");
        String activo = request.getParameter("activo");

        logger.info("Fecha recibida: " + fecha);

        if (activo != null)
            activo = "1";
        else
            activo = "0";

        IMatriculacionesService m = new MatriculacionesServiceImp();
        ArrayList<MatriculacionDTO> listaMatriculaciones = new ArrayList<>();

        // Si no se introduce fecha, usar método sin filtro de fecha
        if (fecha == null || fecha.trim().isEmpty()) {
            listaMatriculaciones = m.obtenerMatriculacionesPorFiltrosSinFecha(nombreAsignatura, nombreAlumno,
                    Integer.parseInt(activo));
        } else {
            listaMatriculaciones = m.obtenerMatriculacionesPorFiltros(nombreAsignatura, nombreAlumno, fecha,
                    Integer.parseInt(activo));
        }

        request.setAttribute("lista", listaMatriculaciones);
        RequestDispatcher d = getServletContext()
                .getRequestDispatcher("/WEB-INF/vistas/matriculaciones/listadoMatriculaciones.jsp");
        d.forward(request, response);
    }

}
