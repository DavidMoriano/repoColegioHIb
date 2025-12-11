package servicios;

import java.sql.SQLException;
import java.util.ArrayList;

import dto.NotaDTO;

public interface INotasService {
    public ArrayList<NotaDTO> obtenerNotas() throws SQLException;

    public ArrayList<NotaDTO> obtenerNotasPorFiltros(String idAlumno, String nombreAlumno, String asignatura,
            String nota, String fecha, int activo);

    public ArrayList<NotaDTO> obtenerNotasPorFiltrosSinFecha(String idAlumno, String nombreAlumno, String asignatura,
            String nota, int activo);

    public int insertarNota(String idAlumno, String idAsignatura, String nota, String fecha);

    public int actualizarNota(String id, String idAlumno, String idAsignatura, String nota, String fecha);

    public int borrarNota(String id);
}
