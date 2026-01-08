package dao;

import java.util.ArrayList;

import dto.AsignaturaDTO;

public interface IAsignaturasDAO {

    ArrayList<AsignaturaDTO> obtenerTodasAsignaturas();

    ArrayList<AsignaturaDTO> obtenerAsignaturasPorFiltros(String id, String nombre, String curso, String tasa,
            int activo);

    int insertarAsignatura(String id, String nombre, String curso, String tasa, int activo);

    int actualizarAsignatura(String id, String nombre, String curso, String tasa, int activo);

    int borrarAsignatura(String id);

    
    double obtenerTasaAsignatura(String idAsignatura);

}
