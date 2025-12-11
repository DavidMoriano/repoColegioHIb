package daoImp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dao.IFaltasDAO;
import dto.FaltaDTO;
import utils.DBUtils;

public class FaltasDAOImpl implements IFaltasDAO {
    private static Logger logger = LoggerFactory.getLogger(FaltasDAOImpl.class);

    @Override
    public ArrayList<FaltaDTO> obtenerTodasFaltas() {
        Connection connection = DBUtils.conexion();
        ResultSet faltas = null;
        ArrayList<FaltaDTO> listaFaltas = new ArrayList<>();
        Statement statement;

        try {

            statement = connection.createStatement();
            faltas = statement.executeQuery("SELECT * FROM faltas");

            while (faltas.next()) {
                FaltaDTO f = new FaltaDTO(faltas.getInt(1), faltas.getString(5));
                logger.debug("Contenido de falta " + f.getIdFalta());
                listaFaltas.add(f);
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaFaltas;
    }

    @Override
    public ArrayList<FaltaDTO> obtenerFaltasPorFiltros(String nombreAlumno, String asignatura, String fecha,
            int justificada) {
        String sql = "SELECT f.idfaltas, f.alumno, al.nombre AS nombre_alumno, "
                + "f.asignatura, a.nombre AS nombre_asignatura, f.fecha, f.justificada "
                + "FROM faltas f "
                + "JOIN asignaturas a ON f.asignatura = a.id "
                + "JOIN alumnos al ON f.alumno = al.id "
                + "WHERE al.nombre LIKE ? AND a.nombre LIKE ? "
                + "AND f.fecha >= ? AND f.justificada = ?";

        ResultSet faltaResultSet = null;
        Connection connection = DBUtils.conexion();
        ArrayList<FaltaDTO> listaFaltas = new ArrayList<>();

        try {
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, "%" + nombreAlumno + "%");
            ps.setString(2, "%" + asignatura + "%");
            ps.setString(3, fecha); // Fecha en formato YYYY-MM-DD (tipo DATE en MySQL)
            ps.setInt(4, justificada);

            logger.debug("Query a ejecutar: " + ps);

            faltaResultSet = ps.executeQuery();

            while (faltaResultSet.next()) {
                FaltaDTO f = new FaltaDTO(
                        faltaResultSet.getInt(1),
                        faltaResultSet.getInt(2),
                        faltaResultSet.getString(3),
                        faltaResultSet.getInt(4),
                        faltaResultSet.getString(5),
                        faltaResultSet.getString(6),
                        faltaResultSet.getInt(7));
                listaFaltas.add(f);
            }
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaFaltas;
    }

    @Override
    public ArrayList<FaltaDTO> obtenerFaltasPorFiltrosSinFecha(String nombreAlumno, String asignatura,
            int justificada) {
        String sql = "SELECT f.idfaltas, f.alumno, al.nombre AS nombre_alumno, "
                + "f.asignatura, a.nombre AS nombre_asignatura, f.fecha, f.justificada "
                + "FROM faltas f "
                + "JOIN asignaturas a ON f.asignatura = a.id "
                + "JOIN alumnos al ON f.alumno = al.id "
                + "WHERE al.nombre LIKE ? AND a.nombre LIKE ? AND f.justificada = ?";

        ResultSet faltaResultSet = null;
        Connection connection = DBUtils.conexion();
        ArrayList<FaltaDTO> listaFaltas = new ArrayList<>();

        try {
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, "%" + nombreAlumno + "%");
            ps.setString(2, "%" + asignatura + "%");
            ps.setInt(3, justificada);

            logger.debug("Query a ejecutar: " + ps);

            faltaResultSet = ps.executeQuery();

            while (faltaResultSet.next()) {
                FaltaDTO f = new FaltaDTO(
                        faltaResultSet.getInt(1),
                        faltaResultSet.getInt(2),
                        faltaResultSet.getString(3),
                        faltaResultSet.getInt(4),
                        faltaResultSet.getString(5),
                        faltaResultSet.getString(6),
                        faltaResultSet.getInt(7));
                listaFaltas.add(f);
            }
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaFaltas;
    }

    @Override
    public int insertarFalta(String idAlumno, String idAsignatura, String fecha, int justificada) {
        String sql = "INSERT INTO faltas (alumno, asignatura, fecha, justificada) VALUES (?, ?, ?, ?)";
        PreparedStatement ps = null;
        int resultado = 0;

        try {
            Connection connection = DBUtils.conexion();
            ps = connection.prepareStatement(sql);
            ps.setString(1, idAlumno);
            ps.setString(2, idAsignatura);
            ps.setString(3, fecha); // Fecha en formato YYYY-MM-DD (tipo DATE en MySQL)
            ps.setInt(4, justificada);

            resultado = ps.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultado;
    }

    @Override
    public int actualizarFalta(String idFalta, String idAlumno, String idAsignatura, String fecha, int justificada) {
        String sql = "UPDATE faltas SET alumno = ?, asignatura = ?, fecha = ?, justificada = ? "
                + "WHERE idfaltas = ? ";
        PreparedStatement ps = null;
        int resultado = 0;

        try {
            Connection connection = DBUtils.conexion();
            ps = connection.prepareStatement(sql);
            ps.setString(1, idAlumno);
            ps.setString(2, idAsignatura);
            ps.setString(3, fecha); // Fecha en formato YYYY-MM-DD (tipo DATE en MySQL)
            ps.setInt(4, justificada);
            ps.setString(5, idFalta);
            logger.debug("Query a ejecutar: " + ps);
            resultado = ps.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultado;
    }

    @Override
    public int borrarFalta(String idFalta) {
        String sql = "DELETE FROM faltas WHERE idfaltas = ? ";
        Connection connection = DBUtils.conexion();
        PreparedStatement ps;
        Integer resultado = 0;
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, idFalta);
            logger.debug("Query a ejecutar: " + ps);
            resultado = ps.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultado;
    }

}
