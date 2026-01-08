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
 * Servlet implementation class InsertarAsignaturasController
 */
@WebServlet("/asignaturas/insertarAsignatura")
public class InsertarAsignaturasController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static Logger logger = LoggerFactory.getLogger(InsertarAsignaturasController.class);

    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertarAsignaturasController() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher d = getServletContext()
                .getRequestDispatcher("/WEB-INF/vistas/asignaturas/insertarAsignatura.jsp");
        d.forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Recuperamos los datos del formulario
        String id = request.getParameter("id");
        String nombre = request.getParameter("nombre");
        String curso = request.getParameter("curso");
        String tasa = request.getParameter("tasa");
        String activo = request.getParameter("activo");

        if (activo != null)
            activo = "1";
        else
            activo = "0";

        IAsignaturasService a = new AsignaturasServiceImp();
        Integer resultado = a.insertarAsignatura(id, nombre, curso, tasa, Integer.parseInt(activo));

        request.setAttribute("resultado", resultado);

        RequestDispatcher d = getServletContext()
                .getRequestDispatcher("/WEB-INF/vistas/asignaturas/insertarAsignatura.jsp");
        d.forward(request, response);
    }

}
