package controllers.matriculaciones;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import servicios.IMatriculacionesService;
import serviciosImp.MatriculacionesServiceImp;

import java.io.IOException;
import java.util.ArrayList;

import dto.MatriculacionDTO;

/**
 * Servlet implementation class FormularioBorrarMatriculacionesController
 */
@WebServlet("/matriculaciones/formularioBorrarMatriculaciones")
public class FormularioBorrarMatriculacionesController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public FormularioBorrarMatriculacionesController() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher d = getServletContext()
                .getRequestDispatcher("/WEB-INF/vistas/matriculaciones/borrarMatriculaciones.jsp");
        d.forward(request, response);

    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nombreAlumno = request.getParameter("nombreAlumno");
        String asignatura = request.getParameter("asignatura");
        String fecha = request.getParameter("fecha");

        ArrayList<MatriculacionDTO> listaMatriculaciones = new ArrayList<>();

        IMatriculacionesService m = new MatriculacionesServiceImp();

        // Buscar matriculaciones activas
        if (fecha == null || fecha.trim().isEmpty()) {
            listaMatriculaciones = m.obtenerMatriculacionesPorFiltrosSinFecha(asignatura, nombreAlumno, 1);
        } else {
            listaMatriculaciones = m.obtenerMatriculacionesPorFiltros(asignatura, nombreAlumno, fecha, 1);
        }

        request.setAttribute("lista", listaMatriculaciones);

        RequestDispatcher d = getServletContext()
                .getRequestDispatcher("/WEB-INF/vistas/matriculaciones/borrarMatriculaciones.jsp");
        d.forward(request, response);
    }

}
