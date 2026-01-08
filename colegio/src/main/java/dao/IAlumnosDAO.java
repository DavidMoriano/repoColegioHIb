package dao;

import java.util.ArrayList;

import dto.AlumnoDTO;

public interface IAlumnosDAO {
	
	ArrayList<AlumnoDTO> obtenerTodosAlumnos();
	ArrayList<AlumnoDTO> obtenerAlumnosPorIdNombreApellido(String id, String nombre, String apellido,  int familiaNumerosa, int activo);
	int insertarAlumno(String id, String nombre, String apellido, String idMunicipio, int familiaNumerosa, int activo);
	int actualizarAlumno(String id, String nombre, String apellido, String idMunicipio, int familiaNumerosa, int activo);
	int borrarAlumno(String id);
	
    boolean esFamiliaNumerosa(String idAlumno);
	int contarAsignaturasMatriculadas(String idAlumno);

}
