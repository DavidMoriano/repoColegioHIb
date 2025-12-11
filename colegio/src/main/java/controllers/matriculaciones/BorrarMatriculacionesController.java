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
 * Servlet implementation class BorrarMatriculacionesController
 */
@WebServlet("/matriculaciones/borrarMatriculacion")
public class BorrarMatriculacionesController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public BorrarMatriculacionesController() {
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

        IMatriculacionesService m = new MatriculacionesServiceImp();
        m.borrarMatriculacion(id);

        RequestDispatcher d = getServletContext()
                .getRequestDispatcher("/WEB-INF/vistas/matriculaciones/borrarMatriculaciones.jsp");
        d.forward(request, response);
    }

}
