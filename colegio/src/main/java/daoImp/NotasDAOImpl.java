package daoImp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dao.INotasDAO;
import dto.NotaDTO;
import utils.DBUtils;

public class NotasDAOImpl implements INotasDAO {
    private static Logger logger = LoggerFactory.getLogger(NotasDAOImpl.class);

    @Override
    public ArrayList<NotaDTO> obtenerTodasNotas() {
        Connection connection = DBUtils.conexion();
        ResultSet notas = null;
        ArrayList<NotaDTO> listaNotas = new ArrayList<>();
        Statement statement;

        try {

            statement = connection.createStatement();
            notas = statement.executeQuery("SELECT * FROM notas");

            while (notas.next()) {
                NotaDTO n = new NotaDTO(notas.getInt(4), notas.getString(1));
                logger.debug("Contenido de nota " + n.getId());
                listaNotas.add(n);
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaNotas;
    }

    @Override
    public ArrayList<NotaDTO> obtenerNotasPorFiltros(String idAlumno, String nombreAlumno, String asignatura,
            String nota, String fecha, int activo) {
        String sql = "SELECT n.id, n.nota, n.id_asignaturas, a.nombre AS nombre_asignatura, "
                + "n.id_alumnos, al.nombre AS nombre_alumno, n.fecha "
                + "FROM notas n "
                + "JOIN asignaturas a ON n.id_asignaturas = a.id "
                + "JOIN alumnos al ON n.id_alumnos = al.id "
                + "WHERE n.id_alumnos LIKE ? AND al.nombre LIKE ? AND a.nombre LIKE ? "
                + "AND n.nota LIKE ? AND n.fecha >= ? AND al.activo = ?";

        ResultSet notaResultSet = null;
        Connection connection = DBUtils.conexion();
        ArrayList<NotaDTO> listaNotas = new ArrayList<>();

        try {
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, "%" + idAlumno + "%");
            ps.setString(2, "%" + nombreAlumno + "%");
            ps.setString(3, "%" + asignatura + "%");
            ps.setString(4, "%" + nota + "%");
            ps.setString(5, fecha);
            ps.setInt(6, activo);

            logger.debug("Query a ejecutar: " + ps);

            notaResultSet = ps.executeQuery();

            while (notaResultSet.next()) {
                NotaDTO n = new NotaDTO(
                        notaResultSet.getInt(1),
                        notaResultSet.getString(2),
                        notaResultSet.getInt(3),
                        notaResultSet.getString(4),
                        notaResultSet.getInt(5),
                        notaResultSet.getString(6),
                        notaResultSet.getString(7));
                listaNotas.add(n);
            }
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaNotas;
    }

    @Override
    public ArrayList<NotaDTO> obtenerNotasPorFiltrosSinFecha(String idAlumno, String nombreAlumno, String asignatura,
            String nota, int activo) {
        String sql = "SELECT n.id, n.nota, n.id_asignaturas, a.nombre AS nombre_asignatura, "
                + "n.id_alumnos, al.nombre AS nombre_alumno, n.fecha "
                + "FROM notas n "
                + "JOIN asignaturas a ON n.id_asignaturas = a.id "
                + "JOIN alumnos al ON n.id_alumnos = al.id "
                + "WHERE n.id_alumnos LIKE ? AND al.nombre LIKE ? AND a.nombre LIKE ? "
                + "AND n.nota LIKE ? AND al.activo = ?";

        ResultSet notaResultSet = null;
        Connection connection = DBUtils.conexion();
        ArrayList<NotaDTO> listaNotas = new ArrayList<>();

        try {
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, "%" + idAlumno + "%");
            ps.setString(2, "%" + nombreAlumno + "%");
            ps.setString(3, "%" + asignatura + "%");
            ps.setString(4, "%" + nota + "%");
            ps.setInt(5, activo);

            logger.debug("Query a ejecutar: " + ps);

            notaResultSet = ps.executeQuery();

            while (notaResultSet.next()) {
                NotaDTO n = new NotaDTO(
                        notaResultSet.getInt(1),
                        notaResultSet.getString(2),
                        notaResultSet.getInt(3),
                        notaResultSet.getString(4),
                        notaResultSet.getInt(5),
                        notaResultSet.getString(6),
                        notaResultSet.getString(7));
                listaNotas.add(n);
            }
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaNotas;
    }

    @Override
    public int insertarNota(String idAlumno, String idAsignatura, String nota, String fecha) {
        String sql = "INSERT INTO notas (id_alumnos, id_asignaturas, nota, fecha) VALUES (?, ?, ?, ?)";
        PreparedStatement ps = null;
        int resultado = 0;

        try {
            Connection connection = DBUtils.conexion();
            ps = connection.prepareStatement(sql);
            ps.setString(1, idAlumno);
            ps.setString(2, idAsignatura);
            ps.setString(3, nota);
            ps.setString(4, fecha);

            resultado = ps.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultado;
    }

    @Override
    public int actualizarNota(String id, String idAlumno, String idAsignatura, String nota, String fecha) {
        String sql = "UPDATE notas SET id_alumnos = ?, id_asignaturas = ?, nota = ?, fecha = ? "
                + "WHERE id = ? ";
        PreparedStatement ps = null;
        int resultado = 0;

        try {
            Connection connection = DBUtils.conexion();
            ps = connection.prepareStatement(sql);
            ps.setString(1, idAlumno);
            ps.setString(2, idAsignatura);
            ps.setString(3, nota);
            ps.setString(4, fecha);
            ps.setString(5, id);
            logger.debug("Query a ejecutar: " + ps);
            resultado = ps.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultado;
    }

    @Override
    public int borrarNota(String id) {
        String sql = "DELETE FROM notas WHERE id = ? ";
        Connection connection = DBUtils.conexion();
        PreparedStatement ps;
        Integer resultado = 0;
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, id);
            logger.debug("Query a ejecutar: " + ps);
            resultado = ps.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultado;
    }

}
