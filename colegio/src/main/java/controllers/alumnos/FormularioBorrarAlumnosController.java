package controllers.alumnos;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import servicios.IAlumnosService;
import serviciosImp.AlumnosServiceImp;
import utils.DesplegableUtils;

import java.io.IOException;
import java.util.ArrayList;

import dto.AlumnoDTO;

/**
 * Servlet implementation class FormularioBorrarAlumnosController
 */
@WebServlet("/alumnos/formularioBorrarAlumnos")
public class FormularioBorrarAlumnosController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FormularioBorrarAlumnosController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		RequestDispatcher d = getServletContext().getRequestDispatcher("/WEB-INF/vistas/alumnos/borrarAlumnos.jsp");
        d.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DesplegableUtils.recuperarDesplegableMunicipios(request);
		String id = request.getParameter("id");
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String familiaNumerosa = request.getParameter("familiaNumerosa");
        String activo =  request.getParameter("activo");
        
        if(familiaNumerosa != null)
        	familiaNumerosa = "1";
        else
        	familiaNumerosa = "0";
        
        if(activo != null)
        	activo = "1";
        else
        	activo = "0";
        
        ArrayList<AlumnoDTO> listaAlumnos = new ArrayList<>();
        
        IAlumnosService a = new AlumnosServiceImp();
        listaAlumnos = a.obtenerAlumnosPorIdNombreApellido(id, nombre, apellido, Integer.parseInt(familiaNumerosa), Integer.parseInt(activo));
        request.setAttribute("lista", listaAlumnos);
        
        RequestDispatcher d = getServletContext().getRequestDispatcher("/WEB-INF/vistas/alumnos/borrarAlumnos.jsp");
        d.forward(request, response);
	}

}
