package dao;

import java.util.List;

import dto.AsignaturaDTO;

public interface IAsignaturasDAO {

    List<AsignaturaDTO> obtenerTodasAsignaturas();

    int actualizarAsignatura(String id, String nombre, String curso, String tasa, int activo);

    int borrarAsignatura(String id);
    
    double obtenerTasaAsignatura(String idAsignatura);

	int insertarAsignatura(int id, String nombre, String curso, String tasa, int activo);

	List<AsignaturaDTO> obtenerAsignaturasPorFiltros(String id, String nombre, String curso, String tasa, int activo);
}
