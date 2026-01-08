package utils;

import java.util.ArrayList;

import dao.IDesplegableDAO;
import daoImp.Hib.DesplegablesDAOImplHib;
import dto.DesplegableDTO;
import jakarta.servlet.http.HttpServletRequest;

public class DesplegableUtils {

	public static void recuperarDesplegableMunicipios(HttpServletRequest request) {
		IDesplegableDAO desplegableMunicipios = new DesplegablesDAOImplHib();
		ArrayList<DesplegableDTO> listaMunicipios = desplegableMunicipios.desplegableMunicipios();
		request.setAttribute("desplegableMunicipios", listaMunicipios);
	}

	public static void recuperarDesplegableAlumnos(HttpServletRequest request) {
		IDesplegableDAO desplegableAlumnos = new DesplegablesDAOImplHib();
		ArrayList<DesplegableDTO> listaAlumnos = desplegableAlumnos.desplegableAlumnos();
		request.setAttribute("desplegableAlumnos", listaAlumnos);
	}

	public static void recuperarDesplegableAsignaturas(HttpServletRequest request) {
		IDesplegableDAO desplegableAsignaturas = new DesplegablesDAOImplHib();
		ArrayList<DesplegableDTO> listaAsignaturas = desplegableAsignaturas.desplegableAsignaturas();
		request.setAttribute("desplegableAsignaturas", listaAsignaturas);
	}
	
	public static void recuperarDesplegableMatriculaciones(HttpServletRequest request) {
		IDesplegableDAO desplegableMatriculaciones = new DesplegablesDAOImplHib();
		ArrayList<DesplegableDTO> listaMatriculaciones = desplegableMatriculaciones.desplegableMatriculaciones();
		request.setAttribute("desplegableMatriculaicones", listaMatriculaciones);
	}

}
