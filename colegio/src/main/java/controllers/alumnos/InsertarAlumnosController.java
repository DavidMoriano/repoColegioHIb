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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dao.IDesplegableDAO;
import daoImp.DesplegableDAOImp;
import dto.DesplegableDTO;

/**
 * Servlet implementation class InsertarAlumnosController
 */
@WebServlet("/alumnos/insertarAlumno")
public class InsertarAlumnosController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = LoggerFactory.getLogger(InsertarAlumnosController.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertarAlumnosController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DesplegableUtils.recuperarDesplegableMunicipios(request);
        
        RequestDispatcher d = getServletContext().getRequestDispatcher("/WEB-INF/vistas/alumnos/insertarAlumno.jsp");
        d.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Recuperamos los datos del formulario
	    String id = request.getParameter("id");
	    String nombre = request.getParameter("nombre");
	    String apellido = request.getParameter("apellido");
	    String idMunicipio = request.getParameter("municipios");
	    String familiaNumerosa = request.getParameter("famNumerosa");
	    String activo = request.getParameter("activo");
	    
	    IAlumnosService a = new AlumnosServiceImp();
	    Integer resultado = a.insertarAlumno(id, nombre, apellido, idMunicipio, Integer.parseInt(familiaNumerosa), Integer.parseInt(activo));
	    
	    request.setAttribute("resultado", resultado);
	    
	    DesplegableUtils.recuperarDesplegableMunicipios(request);
        
	    
	    RequestDispatcher d = getServletContext().getRequestDispatcher("/WEB-INF/vistas/alumnos/insertarAlumno.jsp");
	    d.forward(request, response);
	}
	


}
