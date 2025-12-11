package controllers.alumnos;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import servicios.IAlumnosService;
import serviciosImp.AlumnosServiceImp;

import java.io.IOException;

/**
 * Servlet implementation class ActualizarAlumnosController
 */
@WebServlet("/alumnos/actualizarAlumno")
public class ActualizarAlumnosController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ActualizarAlumnosController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		   String id = request.getParameter("id");
		    String nombre = request.getParameter("nombre");
		    String apellido = request.getParameter("apellido");
		    String idMunicipio = request.getParameter("municipio");
		    String familiaNumerosa = request.getParameter("famNumerosa");
		    String activo = request.getParameter("activo");
		    
			System.out.print(idMunicipio);
		    
		    if(familiaNumerosa != null)
	        	familiaNumerosa = "1";
	        else
	        	familiaNumerosa = "0";
	        
	        if(activo != null)
	        	activo = "1";
	        else
	        	activo = "0";
	        
		    
		    IAlumnosService a = new AlumnosServiceImp();
		    a.actualizarAlumno(id, nombre, apellido, idMunicipio, Integer.parseInt(familiaNumerosa), Integer.parseInt(activo));
		    
	        RequestDispatcher d = getServletContext().getRequestDispatcher("/WEB-INF/vistas/alumnos/actualizarAlumnos.jsp");
	        d.forward(request, response);
	}

}
