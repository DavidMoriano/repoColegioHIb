package serviciosImp;

import java.sql.SQLException;
import java.util.ArrayList;

import dao.INotasDAO;
import daoImp.NotasDAOImpl;
import dto.NotaDTO;
import servicios.INotasService;

public class NotasServiceImp implements INotasService {

    @Override
    public ArrayList<NotaDTO> obtenerNotas() throws SQLException {
        INotasDAO notas = new NotasDAOImpl();

        return notas.obtenerTodasNotas();
    }

    @Override
    public ArrayList<NotaDTO> obtenerNotasPorFiltros(String idAlumno, String nombreAlumno, String asignatura,
            String nota, String fecha, int activo) {
        INotasDAO notas = new NotasDAOImpl();
        return notas.obtenerNotasPorFiltros(idAlumno, nombreAlumno, asignatura, nota, fecha, activo);
    }

    @Override
    public ArrayList<NotaDTO> obtenerNotasPorFiltrosSinFecha(String idAlumno, String nombreAlumno, String asignatura,
            String nota, int activo) {
        INotasDAO notas = new NotasDAOImpl();
        return notas.obtenerNotasPorFiltrosSinFecha(idAlumno, nombreAlumno, asignatura, nota, activo);
    }

    @Override
    public int insertarNota(String idAlumno, String idAsignatura, String nota, String fecha) {
        INotasDAO notas = new NotasDAOImpl();
        return notas.insertarNota(idAlumno, idAsignatura, nota, fecha);
    }

    @Override
    public int actualizarNota(String id, String idAlumno, String idAsignatura, String nota, String fecha) {
        INotasDAO notas = new NotasDAOImpl();
        return notas.actualizarNota(id, idAlumno, idAsignatura, nota, fecha);
    }

    @Override
    public int borrarNota(String id) {
        INotasDAO notas = new NotasDAOImpl();
        return notas.borrarNota(id);
    }

}
