package serviciosImp;

import java.sql.SQLException;
import java.util.ArrayList;

import dao.IFaltasDAO;
import daoImp.FaltasDAOImpl;
import dto.FaltaDTO;
import servicios.IFaltasService;

public class FaltasServiceImp implements IFaltasService {

    @Override
    public ArrayList<FaltaDTO> obtenerFaltas() throws SQLException {
        IFaltasDAO faltas = new FaltasDAOImpl();

        return faltas.obtenerTodasFaltas();
    }

    @Override
    public ArrayList<FaltaDTO> obtenerFaltasPorFiltros(String nombreAlumno, String asignatura, String fecha,
            int justificada) {
        IFaltasDAO faltas = new FaltasDAOImpl();
        return faltas.obtenerFaltasPorFiltros(nombreAlumno, asignatura, fecha, justificada);
    }

    @Override
    public ArrayList<FaltaDTO> obtenerFaltasPorFiltrosSinFecha(String nombreAlumno, String asignatura,
            int justificada) {
        IFaltasDAO faltas = new FaltasDAOImpl();
        return faltas.obtenerFaltasPorFiltrosSinFecha(nombreAlumno, asignatura, justificada);
    }

    @Override
    public int insertarFalta(String idAlumno, String idAsignatura, String fecha, int justificada) {
        IFaltasDAO faltas = new FaltasDAOImpl();
        return faltas.insertarFalta(idAlumno, idAsignatura, fecha, justificada);
    }

    @Override
    public int actualizarFalta(String idFalta, String idAlumno, String idAsignatura, String fecha, int justificada) {
        IFaltasDAO faltas = new FaltasDAOImpl();
        return faltas.actualizarFalta(idFalta, idAlumno, idAsignatura, fecha, justificada);
    }

    @Override
    public int borrarFalta(String idFalta) {
        IFaltasDAO faltas = new FaltasDAOImpl();
        return faltas.borrarFalta(idFalta);
    }

}
