package serviciosImp;

import java.sql.SQLException;
import java.util.ArrayList;

import dao.IAlumnosDAO;
import daoImp.AlumnosDAOImpl;
import daoImp.Hib.AlumnosDAOImplHib;
import dto.AlumnoDTO;
import servicios.IAlumnosService;

public class AlumnosServiceImp implements IAlumnosService {

	@Override
	public ArrayList<AlumnoDTO> obtenerAlumnos() throws SQLException {
		IAlumnosDAO alumnos = new AlumnosDAOImpl();
		
		return alumnos.obtenerTodosAlumnos();
	}

	@Override
	public ArrayList<AlumnoDTO> obtenerAlumnosPorIdNombreApellido(String id, String nombre, String apellido,int familiaNumerosa,  int activo) {
		IAlumnosDAO alumnos = new AlumnosDAOImplHib();
		return alumnos.obtenerAlumnosPorIdNombreApellido(id, nombre, apellido,   familiaNumerosa,  activo);
	}

	@Override
	public int insertarAlumno(String id, String nombre, String apellido, String idMunicipio, int familiaNumerosa,
			int activo) {
		IAlumnosDAO alumnos = new AlumnosDAOImplHib();
		return alumnos.insertarAlumno(id, nombre, apellido, idMunicipio, familiaNumerosa, activo);
	}

	@Override
	public int actualizarAlumno(String id, String nombre, String apellido, String idMunicipio, int familiaNumerosa,
			int activo) {
		IAlumnosDAO alumnos = new AlumnosDAOImplHib();
		return alumnos.actualizarAlumno(id, nombre, apellido, idMunicipio, familiaNumerosa, activo);
	}

	@Override
	public int borrarAlumno(String id) {
		IAlumnosDAO alumnos = new AlumnosDAOImplHib();
		return alumnos.borrarAlumno(id);
	}

}
