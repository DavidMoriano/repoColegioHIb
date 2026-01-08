package controllers.alumnos;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import serviciosImp.AlumnosServiceImp;

import java.io.IOException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dto.AlumnoDTO;
import servicios.IAlumnosService;

/**
 * Servlet implementation class ListadoAlumnosController
 */
@WebServlet("/alumnos/listadoAlumnos")
public class ListadoAlumnosController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = LoggerFactory.getLogger(ListadoAlumnosController.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListadoAlumnosController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher d = getServletContext().getRequestDispatcher("/WEB-INF/vistas/alumnos/listadoAlumnos.jsp");
		d.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String familiaNumerosa = request.getParameter("famNumerosa");
        String activo =  request.getParameter("activo");
        
        logger.info(activo);
        
        if(familiaNumerosa != null)
        	familiaNumerosa = "1";
        else
        	familiaNumerosa = "0";
        
        if(activo != null)
        	activo = "1";
        else
        	activo = "0";
        
        IAlumnosService a = new AlumnosServiceImp();
        ArrayList<AlumnoDTO> listaAlumnos = new ArrayList<>();
        
        listaAlumnos = a.obtenerAlumnosPorIdNombreApellido(id, nombre, apellido, Integer.parseInt(familiaNumerosa), Integer.parseInt(activo));
        
        request.setAttribute("lista", listaAlumnos);
        RequestDispatcher d = getServletContext().getRequestDispatcher("/WEB-INF/vistas/alumnos/listadoAlumnos.jsp");
        d.forward(request, response);
	}

}
