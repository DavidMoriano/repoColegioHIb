package controllers.faltas;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import servicios.IFaltasService;
import serviciosImp.FaltasServiceImp;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Servlet implementation class BorrarFaltasController
 */
@WebServlet("/faltas/borrarFalta")
public class BorrarFaltasController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static Logger logger = LoggerFactory.getLogger(BorrarFaltasController.class);

    public BorrarFaltasController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idFalta = request.getParameter("idFalta");

        IFaltasService f = new FaltasServiceImp();
        Integer resultado = f.borrarFalta(idFalta);

        logger.debug("Falta borrada: " + idFalta + ", resultado: " + resultado);

        RequestDispatcher d = getServletContext().getRequestDispatcher("/WEB-INF/vistas/faltas/borrarFaltas.jsp");
        d.forward(request, response);
    }

}
