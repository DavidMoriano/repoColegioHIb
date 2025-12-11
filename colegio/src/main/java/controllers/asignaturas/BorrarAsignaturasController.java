package controllers.asignaturas;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import servicios.IAsignaturasService;
import serviciosImp.AsignaturasServiceImp;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Servlet implementation class BorrarAsignaturasController
 */
@WebServlet("/asignaturas/borrarAsignatura")
public class BorrarAsignaturasController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static Logger logger = LoggerFactory.getLogger(BorrarAsignaturasController.class);

    /**
     * @see HttpServlet#HttpServlet()
     */
    public BorrarAsignaturasController() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");

        IAsignaturasService a = new AsignaturasServiceImp();
        Integer resultado = a.borrarAsignatura(id);

        logger.debug("Asignatura borrada: " + id + ", resultado: " + resultado);

        RequestDispatcher d = getServletContext()
                .getRequestDispatcher("/WEB-INF/vistas/asignaturas/borrarAsignaturas.jsp");
        d.forward(request, response);
    }

}
