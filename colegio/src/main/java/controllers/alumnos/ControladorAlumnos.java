package controllers.alumnos;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.DBUtils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dao.IAlumnosDAO;
import daoImp.AlumnosDAOImpl;
import dto.AlumnoDTO;

/**
 * Servlet implementation class pruebaConexion
 */
@WebServlet("/alumnos/obtenertodosalumnos")
public class ControladorAlumnos extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = LoggerFactory.getLogger(ControladorAlumnos.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControladorAlumnos() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		IAlumnosDAO alumnosDao = new AlumnosDAOImpl();
		ArrayList<AlumnoDTO> listaAlumnos = alumnosDao.obtenerTodosAlumnos();
		request.setAttribute("listaAlumnos", listaAlumnos);
		
		RequestDispatcher d = getServletContext().getRequestDispatcher("/WEB-INF/listadoAlumnos.jsp");
		d.forward(request, response);
		

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
