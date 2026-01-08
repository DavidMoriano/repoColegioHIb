package controllers.matriculaciones;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import servicios.IMatriculacionesService;
import serviciosImp.MatriculacionesServiceImp;

import java.io.IOException;
import java.io.PrintWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Servlet implementation class CalcularTasaController
 */
@WebServlet("/tasa")
public class CalcularTasaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = LoggerFactory.getLogger(CalcularTasaController.class);
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CalcularTasaController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 // Obtener parámetros
        String idAlumno = request.getParameter("alumnos"); // idAlumno
        String idAsignatura = request.getParameter("asignaturas"); // idAsignatura

        logger.debug("Calculando tasa para alumno " + idAlumno + " en asignatura " + idAsignatura);

        // Calcular tasa con lógica de negocio (Service)
        IMatriculacionesService service = new MatriculacionesServiceImp();
        double tasaFinal = service.calcularTasa(idAlumno, idAsignatura);

        logger.info("Tasa calculada: " + tasaFinal);

        PrintWriter out = response.getWriter();
        out.print(tasaFinal);
	}

}
