package controllers.notas;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import serviciosImp.NotasServiceImp;

import java.io.IOException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dto.NotaDTO;
import servicios.INotasService;

/**
 * Servlet implementation class ListadoNotasController
 */
@WebServlet("/notas/listadoNotas")
public class ListadoNotasController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static Logger logger = LoggerFactory.getLogger(ListadoNotasController.class);

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListadoNotasController() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher d = getServletContext().getRequestDispatcher("/WEB-INF/vistas/notas/listadoNotas.jsp");
        d.forward(request, response);

    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idAlumno = request.getParameter("idAlumno");
        String nombreAlumno = request.getParameter("nombreAlumno");
        String asignatura = request.getParameter("asignatura");
        String nota = request.getParameter("nota");
        String fecha = request.getParameter("fecha");
        String activo = request.getParameter("activo");

        logger.info("Fecha recibida: " + fecha);

        if (activo != null)
            activo = "1";
        else
            activo = "0";

        INotasService n = new NotasServiceImp();
        ArrayList<NotaDTO> listaNotas = new ArrayList<>();

        // Si no se introduce fecha, usar método sin filtro de fecha
        if (fecha == null || fecha.trim().isEmpty()) {
            listaNotas = n.obtenerNotasPorFiltrosSinFecha(idAlumno, nombreAlumno, asignatura, nota,
                    Integer.parseInt(activo));
        } else {
            listaNotas = n.obtenerNotasPorFiltros(idAlumno, nombreAlumno, asignatura, nota, fecha,
                    Integer.parseInt(activo));
        }

        request.setAttribute("lista", listaNotas);
        RequestDispatcher d = getServletContext().getRequestDispatcher("/WEB-INF/vistas/notas/listadoNotas.jsp");
        d.forward(request, response);
    }

}
