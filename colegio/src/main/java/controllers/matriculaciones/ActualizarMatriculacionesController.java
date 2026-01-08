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

/**
 * Servlet implementation class ActualizarMatriculacionesController
 */
@WebServlet("/matriculaciones/actualizarMatriculacion")
public class ActualizarMatriculacionesController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ActualizarMatriculacionesController() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        String idAlumno = request.getParameter("alumno");
        String idAsignatura = request.getParameter("asignatura");
        String tasa = request.getParameter("tasa");
        String fecha = request.getParameter("fecha");

        IMatriculacionesService m = new MatriculacionesServiceImp();
        m.actualizarMatriculacion(id, idAsignatura, idAlumno, fecha, tasa);

        RequestDispatcher d = getServletContext()
                .getRequestDispatcher("/WEB-INF/vistas/matriculaciones/actualizarMatriculaciones.jsp");
        d.forward(request, response);
    }

}
