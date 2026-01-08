package serviciosImp;

import java.sql.SQLException;
import java.util.ArrayList;

import dao.IAsignaturasDAO;
import daoImp.Hib.AsignaturasDAOImplHib;
import dto.AsignaturaDTO;
import servicios.IAsignaturasService;

public class AsignaturasServiceImp implements IAsignaturasService {

    @Override
    public ArrayList<AsignaturaDTO> obtenerAsignaturas() throws SQLException {
        IAsignaturasDAO asignaturas = new AsignaturasDAOImplHib();
        return asignaturas.obtenerTodasAsignaturas();
    }

    @Override
    public ArrayList<AsignaturaDTO> obtenerAsignaturasPorFiltros(String id, String nombre, String curso, String tasa,
            int activo) {
        IAsignaturasDAO asignaturas = new AsignaturasDAOImplHib();
        return asignaturas.obtenerAsignaturasPorFiltros(id, nombre, curso, tasa, activo);
    }

    @Override
    public int insertarAsignatura(String id, String nombre, String curso, String tasa, int activo) {
        IAsignaturasDAO asignaturas = new AsignaturasDAOImplHib();
        return asignaturas.insertarAsignatura(id, nombre, curso, tasa, activo);
    }

    @Override
    public int actualizarAsignatura(String id, String nombre, String curso, String tasa, int activo) {
        IAsignaturasDAO asignaturas = new AsignaturasDAOImplHib();
        return asignaturas.actualizarAsignatura(id, nombre, curso, tasa, activo);
    }

    @Override
    public int borrarAsignatura(String id) {
        IAsignaturasDAO asignaturas = new AsignaturasDAOImplHib();
        return asignaturas.borrarAsignatura(id);
    }
}
