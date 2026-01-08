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
 * Servlet implementation class BorrarAlumnosController
 */
@WebServlet("/alumnos/borrarAlumno")
public class BorrarAlumnosController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BorrarAlumnosController() {
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
	    
	    IAlumnosService a = new AlumnosServiceImp();
	    a.borrarAlumno(id);
	    
        RequestDispatcher d = getServletContext().getRequestDispatcher("/WEB-INF/vistas/alumnos/borrarAlumnos.jsp");
        d.forward(request, response);
	}

}
