package serviciosImp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.IAsignaturasDAO;
import daoImp.AsignaturasDAOImpl;
import dto.AsignaturaDTO;
import servicios.IAsignaturasService;

public class AsignaturasServiceImp implements IAsignaturasService {

    @Override
    public List<AsignaturaDTO> obtenerAsignaturas() throws SQLException {
        IAsignaturasDAO asignaturas = new AsignaturasDAOImpl();
        return asignaturas.obtenerTodasAsignaturas();
    }

    @Override
    public List<AsignaturaDTO> obtenerAsignaturasPorFiltros(String id, String nombre, String curso, String tasa,
            int activo) {
        IAsignaturasDAO asignaturas = new AsignaturasDAOImpl();
        return asignaturas.obtenerAsignaturasPorFiltros(id, nombre, curso, tasa, activo);
    }

    @Override
    public int insertarAsignatura(int id, String nombre, String curso, String tasa, int activo) {
        IAsignaturasDAO asignaturas = new AsignaturasDAOImpl();
        return asignaturas.insertarAsignatura(id, nombre, curso, tasa, activo);
    }

    @Override
    public int actualizarAsignatura(String id, String nombre, String curso, String tasa, int activo) {
        IAsignaturasDAO asignaturas = new AsignaturasDAOImpl();
        return asignaturas.actualizarAsignatura(id, nombre, curso, tasa, activo);
    }

    @Override
    public int borrarAsignatura(String id) {
        IAsignaturasDAO asignaturas = new AsignaturasDAOImpl();
        return asignaturas.borrarAsignatura(id);
    }

}
