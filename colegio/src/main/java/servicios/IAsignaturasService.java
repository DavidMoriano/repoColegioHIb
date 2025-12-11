package servicios;

import java.sql.SQLException;
import java.util.List;

import dto.AsignaturaDTO;

public interface IAsignaturasService {
	public List<AsignaturaDTO> obtenerAsignaturas() throws SQLException;

	public int actualizarAsignatura(String id, String nombre, String curso, String tasa, int activo);

	public int borrarAsignatura(String id);

	int insertarAsignatura(int id, String nombre, String curso, String tasa, int activo);

	public List<AsignaturaDTO> obtenerAsignaturasPorFiltros(String id, String nombre, String curso, String tasa,
			int int1);
}
