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
import java.util.ArrayList;

import dto.NotaDTO;

/**
 * Servlet implementation class FormularioBorrarNotasController
 */
@WebServlet("/notas/formularioBorrarNotas")
public class FormularioBorrarNotasController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public FormularioBorrarNotasController() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher d = getServletContext().getRequestDispatcher("/WEB-INF/vistas/notas/borrarNotas.jsp");
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

        ArrayList<NotaDTO> listaNotas = new ArrayList<>();

        INotasService n = new NotasServiceImp();

        // Buscar sin filtros de id, nota y activo
        if (fecha == null || fecha.trim().isEmpty()) {
            listaNotas = n.obtenerNotasPorFiltrosSinFecha("", nombreAlumno, asignatura, "", 1);
        } else {
            listaNotas = n.obtenerNotasPorFiltros("", nombreAlumno, asignatura, "", fecha, 1);
        }

        request.setAttribute("lista", listaNotas);

        RequestDispatcher d = getServletContext().getRequestDispatcher("/WEB-INF/vistas/notas/borrarNotas.jsp");
        d.forward(request, response);
    }

}
