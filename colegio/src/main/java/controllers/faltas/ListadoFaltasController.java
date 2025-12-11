package controllers.faltas;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import serviciosImp.FaltasServiceImp;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dto.FaltaDTO;
import servicios.IFaltasService;

/**
 * Servlet implementation class ListadoFaltasController
 */
@WebServlet("/faltas/listadoFaltas")
public class ListadoFaltasController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static Logger logger = LoggerFactory.getLogger(ListadoFaltasController.class);

    public ListadoFaltasController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher d = getServletContext().getRequestDispatcher("/WEB-INF/vistas/faltas/listadoFaltas.jsp");
        d.forward(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nombreAlumno = request.getParameter("nombreAlumno");
        String asignatura = request.getParameter("asignatura");
        String fecha = request.getParameter("fecha");
        String justificada = request.getParameter("justificada");

        logger.info("Fecha recibida: " + fecha);

        // Convertir justificada checkbox a int
        int justificadaInt;
        if (justificada != null)
            justificadaInt = 1;
        else
            justificadaInt = 0;

        IFaltasService f = new FaltasServiceImp();
        ArrayList<FaltaDTO> listaFaltas = new ArrayList<>();

        // Si no se introduce fecha, usar fecha del día actual en formato YYYY-MM-DD
        if (fecha == null || fecha.trim().isEmpty()) {
            String fechaActual = LocalDate.now().toString(); // Formato YYYY-MM-DD
            listaFaltas = f.obtenerFaltasPorFiltros(nombreAlumno, asignatura, fechaActual, justificadaInt);
        } else {
            // Usar fecha directamente (ya viene en formato YYYY-MM-DD del input
            // type="date")
            listaFaltas = f.obtenerFaltasPorFiltros(nombreAlumno, asignatura, fecha, justificadaInt);
        }

        request.setAttribute("lista", listaFaltas);
        RequestDispatcher d = getServletContext().getRequestDispatcher("/WEB-INF/vistas/faltas/listadoFaltas.jsp");
        d.forward(request, response);
    }

}
