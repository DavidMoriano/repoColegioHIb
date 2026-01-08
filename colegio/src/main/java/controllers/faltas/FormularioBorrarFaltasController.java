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
import java.util.ArrayList;

import dto.FaltaDTO;

/**
 * Servlet implementation class FormularioBorrarFaltasController
 */
@WebServlet("/faltas/formularioBorrarFaltas")
public class FormularioBorrarFaltasController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public FormularioBorrarFaltasController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher d = getServletContext().getRequestDispatcher("/WEB-INF/vistas/faltas/borrarFaltas.jsp");
        d.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nombreAlumno = request.getParameter("nombreAlumno");
        String asignatura = request.getParameter("asignatura");
        String fecha = request.getParameter("fecha");
        String justificada = request.getParameter("justificada");

        // Convertir justificada checkbox a int
        int justificadaInt;
        if (justificada != null)
            justificadaInt = 1;
        else
            justificadaInt = 0;

        ArrayList<FaltaDTO> listaFaltas = new ArrayList<>();

        IFaltasService f = new FaltasServiceImp();

        // Buscar con o sin fecha
        if (fecha == null || fecha.trim().isEmpty()) {
            listaFaltas = f.obtenerFaltasPorFiltrosSinFecha(nombreAlumno, asignatura, justificadaInt);
        } else {
            String fechaFormateada = fecha.replace("-", "");
            listaFaltas = f.obtenerFaltasPorFiltros(nombreAlumno, asignatura, fechaFormateada, justificadaInt);
        }

        request.setAttribute("lista", listaFaltas);

        RequestDispatcher d = getServletContext().getRequestDispatcher("/WEB-INF/vistas/faltas/borrarFaltas.jsp");
        d.forward(request, response);
    }

}
