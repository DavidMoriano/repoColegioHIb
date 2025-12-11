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

/**
 * Servlet implementation class ActualizarFaltasController
 */
@WebServlet("/faltas/actualizarFalta")
public class ActualizarFaltasController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ActualizarFaltasController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idFalta = request.getParameter("idFalta");
        String idAlumno = request.getParameter("alumno");
        String idAsignatura = request.getParameter("asignatura");
        String fecha = request.getParameter("fecha");
        String justificada = request.getParameter("justificada");

        // Convertir justificada checkbox a int
        int justificadaInt;
        if (justificada != null)
            justificadaInt = 1;
        else
            justificadaInt = 0;

        // Convertir fecha de YYYY-MM-DD a YYYYMMDD
        String fechaFormateada = fecha.replace("-", "");

        IFaltasService f = new FaltasServiceImp();
        f.actualizarFalta(idFalta, idAlumno, idAsignatura, fechaFormateada, justificadaInt);

        RequestDispatcher d = getServletContext().getRequestDispatcher("/WEB-INF/vistas/faltas/actualizarFaltas.jsp");
        d.forward(request, response);
    }

}
