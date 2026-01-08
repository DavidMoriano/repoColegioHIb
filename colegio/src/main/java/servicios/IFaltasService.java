package servicios;

import java.sql.SQLException;
import java.util.ArrayList;

import dto.FaltaDTO;

public interface IFaltasService {
    public ArrayList<FaltaDTO> obtenerFaltas() throws SQLException;

    public ArrayList<FaltaDTO> obtenerFaltasPorFiltros(String nombreAlumno, String asignatura, String fecha,
            int justificada);

    public ArrayList<FaltaDTO> obtenerFaltasPorFiltrosSinFecha(String nombreAlumno, String asignatura, int justificada);

    public int insertarFalta(String idAlumno, String idAsignatura, String fecha, int justificada);

    public int actualizarFalta(String idFalta, String idAlumno, String idAsignatura, String fecha, int justificada);

    public int borrarFalta(String idFalta);
}
