package utils;

import java.util.ArrayList;

import dao.IDesplegableDAO;
import daoImp.DesplegableDAOImp;
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
		IDesplegableDAO desplegableAlumnos = new DesplegableDAOImp();
		ArrayList<DesplegableDTO> listaAlumnos = desplegableAlumnos.desplegableAlumnos();
		request.setAttribute("desplegableAlumnos", listaAlumnos);
	}

	public static void recuperarDesplegableAsignaturas(HttpServletRequest request) {
		IDesplegableDAO desplegableAsignaturas = new DesplegableDAOImp();
		ArrayList<DesplegableDTO> listaAsignaturas = desplegableAsignaturas.desplegableAsignaturas();
		request.setAttribute("desplegableAsignaturas", listaAsignaturas);
	}

}
