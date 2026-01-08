package controllers.notas;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import servicios.INotasService;
import serviciosImp.NotasServiceImp;

import java.io.IOException;

/**
 * Servlet implementation class ActualizarNotasController
 */
@WebServlet("/notas/actualizarNota")
public class ActualizarNotasController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ActualizarNotasController() {
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
        String nota = request.getParameter("nota");
        String fecha = request.getParameter("fecha");

        INotasService n = new NotasServiceImp();
        n.actualizarNota(id, idAlumno, idAsignatura, nota, fecha);

        RequestDispatcher d = getServletContext().getRequestDispatcher("/WEB-INF/vistas/notas/actualizarNotas.jsp");
        d.forward(request, response);
    }

}
