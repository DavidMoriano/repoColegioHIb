package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import dto.MatriculacionDTO;

public interface IMatriculacionesDAO {
	double obtenerTasaAsignatura(String idAsignatura);

	int insertarMatriculacion(String idAsignatura, String idAlumno,
			String fecha, String tasa) throws SQLException;

	ArrayList<MatriculacionDTO> obtenerMatriculacionesPorFiltros(String nombreAsignatura, String nombreAlumno,
			String fecha, int activo);

	ArrayList<MatriculacionDTO> obtenerMatriculacionesPorFiltrosSinFecha(String nombreAsignatura, String nombreAlumno,
			int activo);

	int actualizarMatriculacion(String id, String idAsignatura, String idAlumno,
			String fecha, String tasa) throws SQLException;

	int borrarMatriculacion(String id) throws SQLException;
}
