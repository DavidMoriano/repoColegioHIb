package servicios;

import java.util.ArrayList;

import dto.MatriculacionDTO;

public interface IMatriculacionesService {
	public double calcularTasa(String idAlumno, String idAsignatura);

	int insertarMatriculacion(String idAsignatura, String idAlumno,
			String fecha, String tasa);

	ArrayList<MatriculacionDTO> obtenerMatriculacionesPorFiltros(String nombreAsignatura, String nombreAlumno,
			String fecha, int activo);

	ArrayList<MatriculacionDTO> obtenerMatriculacionesPorFiltrosSinFecha(String nombreAsignatura, String nombreAlumno,
			int activo);

	int actualizarMatriculacion(String id, String idAsignatura, String idAlumno,
			String fecha, String tasa);

	int borrarMatriculacion(String id);
}
