package servicios;

import java.sql.SQLException;
import java.util.ArrayList;

import dto.AlumnoDTO;

public interface IAlumnosService {
	public ArrayList<AlumnoDTO> obtenerAlumnos() throws SQLException;
	public  ArrayList<AlumnoDTO> obtenerAlumnosPorIdNombreApellido(String id, String nombre, String apellido, int familiaNumerosa, int activo);
	public int insertarAlumno(String id, String nombre, String apellido, String idMunicipio, int familiaNumerosa, int activo);
	public int actualizarAlumno(String id, String nombre, String apellido, String idMunicipio, int familiaNumerosa, int activo);
	public int borrarAlumno(String id);
}
