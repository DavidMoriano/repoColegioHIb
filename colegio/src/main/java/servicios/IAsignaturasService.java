package servicios;

import java.sql.SQLException;
import java.util.ArrayList;

import dto.AsignaturaDTO;

public interface IAsignaturasService {
    public ArrayList<AsignaturaDTO> obtenerAsignaturas() throws SQLException;

    public ArrayList<AsignaturaDTO> obtenerAsignaturasPorFiltros(String id, String nombre, String curso, String tasa,
            int activo);

    public int insertarAsignatura(String id, String nombre, String curso, String tasa, int activo);

    public int actualizarAsignatura(String id, String nombre, String curso, String tasa, int activo);

    public int borrarAsignatura(String id);
}
