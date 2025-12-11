package daoImp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dao.IMatriculacionesDAO;
import dto.MatriculacionDTO;
import utils.DBUtils;

public class MatriculacionesDAOImpl implements IMatriculacionesDAO {
	private static Logger logger = LoggerFactory.getLogger(MatriculacionesDAOImpl.class);

	@Override
	public double obtenerTasaAsignatura(String idAsignatura) {
		String sql = "SELECT tasa FROM asignaturas WHERE id = ? AND activo = 1";
		double tasa = 0.0;

		try {
			Connection connection = DBUtils.conexion();
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, idAsignatura);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				tasa = rs.getDouble("tasa");
			}
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return tasa;
	}

	@Override
	public int insertarMatriculacion(String idAsignatura, String idAlumno, String fecha, String tasa)
			throws SQLException {
		String sql1 = "INSERT INTO matriculaciones (id_asignatura, id_alumno, fecha, activo) VALUES (?, ?, ?, 1)";
		String sql2 = "INSERT INTO caja (idmatricula, importe) VALUES (?,?)";
		int resultado = 0;

		Connection connection = DBUtils.conexion();
		PreparedStatement statement;
		try {
			connection.setAutoCommit(false); // Empieza la transacción
			statement = connection.prepareStatement(sql1, Statement.RETURN_GENERATED_KEYS);
			// Seteo de los parámetros de la primera SQL (matriculaciones)
			statement.setString(1, idAsignatura);
			statement.setString(2, idAlumno);
			statement.setString(3, fecha);
			int filasInsertadas = statement.executeUpdate();

			if (filasInsertadas == 0)
				throw new SQLException("Ha fallado la inserción de la matriculación");

			// Este método nos devolverá la clave generada dentro un ResultSet
			// El resulset en la primera posición nos encontraremos la clave generada
			ResultSet clavesGeneradas = statement.getGeneratedKeys();

			// Recorremos el resultset para obtener la clave.
			int idMatriculacion;
			if (clavesGeneradas.next()) {
				idMatriculacion = clavesGeneradas.getInt(1);
			} else {
				throw new SQLException("Insert fallido, no se ha obtenido id.");
			}

			// Con el idMatriculacion obtenido hago el insert en la tabla caja
			PreparedStatement statement2 = connection.prepareStatement(sql2);
			statement2.setInt(1, idMatriculacion);
			statement2.setString(2, tasa);

			int filasInsertadasCaja = statement2.executeUpdate();

			if (filasInsertadasCaja == 0)
				throw new SQLException("Ha fallado la inserción en caja");

			connection.commit(); // Si todo va bien, confirmamos la transacción
			resultado = filasInsertadasCaja;

		} catch (SQLException e) {
			connection.rollback(); // Si hay error, deshacemos todos los cambios
			e.printStackTrace();

		} finally {
			if (connection != null) {
				connection.setAutoCommit(true); // Restauramos el autocommit
				connection.close();
			}

		}
		return resultado;

	}

	@Override
	public ArrayList<MatriculacionDTO> obtenerMatriculacionesPorFiltros(String nombreAsignatura, String nombreAlumno,
			String fecha, int activo) {
		String sql = "SELECT m.id, m.id_asignatura, a.nombre AS nombre_asignatura, "
				+ "m.id_alumno, al.nombre AS nombre_alumno, m.fecha, m.activo, c.importe "
				+ "FROM matriculaciones m "
				+ "JOIN asignaturas a ON m.id_asignatura = a.id "
				+ "JOIN alumnos al ON m.id_alumno = al.id "
				+ "LEFT JOIN caja c ON m.id = c.idmatricula "
				+ "WHERE a.nombre LIKE ? AND al.nombre LIKE ? AND m.fecha >= ? AND m.activo = ?";

		ResultSet matriculacionResultSet = null;
		Connection connection = DBUtils.conexion();
		ArrayList<MatriculacionDTO> listaMatriculaciones = new ArrayList<>();

		try {
			PreparedStatement ps = connection.prepareStatement(sql);

			ps.setString(1, "%" + nombreAsignatura + "%");
			ps.setString(2, "%" + nombreAlumno + "%");
			ps.setString(3, fecha);
			ps.setInt(4, activo);

			logger.debug("Query a ejecutar: " + ps);

			matriculacionResultSet = ps.executeQuery();

			while (matriculacionResultSet.next()) {
				MatriculacionDTO m = new MatriculacionDTO(
						matriculacionResultSet.getInt(1),
						matriculacionResultSet.getInt(2),
						matriculacionResultSet.getString(3),
						matriculacionResultSet.getInt(4),
						matriculacionResultSet.getString(5),
						matriculacionResultSet.getString(6),
						matriculacionResultSet.getInt(7),
						matriculacionResultSet.getDouble(8));
				listaMatriculaciones.add(m);
			}
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listaMatriculaciones;
	}

	@Override
	public ArrayList<MatriculacionDTO> obtenerMatriculacionesPorFiltrosSinFecha(String nombreAsignatura,
			String nombreAlumno, int activo) {
		String sql = "SELECT m.id, m.id_asignatura, a.nombre AS nombre_asignatura, "
				+ "m.id_alumno, al.nombre AS nombre_alumno, m.fecha, m.activo, c.importe "
				+ "FROM matriculaciones m "
				+ "JOIN asignaturas a ON m.id_asignatura = a.id "
				+ "JOIN alumnos al ON m.id_alumno = al.id "
				+ "LEFT JOIN caja c ON m.id = c.idmatricula "
				+ "WHERE a.nombre LIKE ? AND al.nombre LIKE ? AND m.activo = ?";

		ResultSet matriculacionResultSet = null;
		Connection connection = DBUtils.conexion();
		ArrayList<MatriculacionDTO> listaMatriculaciones = new ArrayList<>();

		try {
			PreparedStatement ps = connection.prepareStatement(sql);

			ps.setString(1, "%" + nombreAsignatura + "%");
			ps.setString(2, "%" + nombreAlumno + "%");
			ps.setInt(3, activo);

			logger.debug("Query a ejecutar: " + ps);

			matriculacionResultSet = ps.executeQuery();

			while (matriculacionResultSet.next()) {
				MatriculacionDTO m = new MatriculacionDTO(
						matriculacionResultSet.getInt(1),
						matriculacionResultSet.getInt(2),
						matriculacionResultSet.getString(3),
						matriculacionResultSet.getInt(4),
						matriculacionResultSet.getString(5),
						matriculacionResultSet.getString(6),
						matriculacionResultSet.getInt(7),
						matriculacionResultSet.getDouble(8));
				listaMatriculaciones.add(m);
			}
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listaMatriculaciones;
	}

	@Override
	public int actualizarMatriculacion(String id, String idAsignatura, String idAlumno, String fecha, String tasa)
			throws SQLException {
		String sql1 = "UPDATE matriculaciones SET id_asignatura = ?, id_alumno = ?, fecha = ? WHERE id = ?";
		String sql2 = "UPDATE caja SET importe = ? WHERE idmatricula = ?";
		int resultado = 0;

		Connection connection = DBUtils.conexion();
		try {
			connection.setAutoCommit(false); // Empieza la transacción

			// Actualizar matriculaciones
			PreparedStatement statement1 = connection.prepareStatement(sql1);
			statement1.setString(1, idAsignatura);
			statement1.setString(2, idAlumno);
			statement1.setString(3, fecha);
			statement1.setString(4, id);

			int filasActualizadas = statement1.executeUpdate();

			if (filasActualizadas == 0)
				throw new SQLException("Ha fallado la actualización de la matriculación");

			// Actualizar caja
			PreparedStatement statement2 = connection.prepareStatement(sql2);
			statement2.setString(1, tasa);
			statement2.setString(2, id);

			int filasActualizadasCaja = statement2.executeUpdate();

			if (filasActualizadasCaja == 0)
				throw new SQLException("Ha fallado la actualización en caja");

			connection.commit(); // Si todo va bien, confirmamos la transacción
			resultado = filasActualizadas;

		} catch (SQLException e) {
			connection.rollback(); // Si hay error, deshacemos todos los cambios
			e.printStackTrace();

		} finally {
			if (connection != null) {
				connection.setAutoCommit(true); // Restauramos el autocommit
				connection.close();
			}

		}
		return resultado;
	}

	@Override
	public int borrarMatriculacion(String id) throws SQLException {
		String sql1 = "DELETE FROM caja WHERE idmatricula = ?";
		String sql2 = "DELETE FROM matriculaciones WHERE id = ?";
		int resultado = 0;

		Connection connection = DBUtils.conexion();
		try {
			connection.setAutoCommit(false); // Empieza la transacción

			// Primero borrar de caja (por la foreign key)
			PreparedStatement statement1 = connection.prepareStatement(sql1);
			statement1.setString(1, id);
			int filasBorradasCaja = statement1.executeUpdate();

			if (filasBorradasCaja == 0)
				throw new SQLException("Ha fallado el borrado en caja");

			// Luego borrar de matriculaciones
			PreparedStatement statement2 = connection.prepareStatement(sql2);
			statement2.setString(1, id);
			int filasBorradas = statement2.executeUpdate();

			if (filasBorradas == 0)
				throw new SQLException("Ha fallado el borrado de la matriculación");

			connection.commit(); // Si todo va bien, confirmamos la transacción
			resultado = filasBorradas;

		} catch (SQLException e) {
			connection.rollback(); // Si hay error, deshacemos todos los cambios
			e.printStackTrace();

		} finally {
			if (connection != null) {
				connection.setAutoCommit(true); // Restauramos el autocommit
				connection.close();
			}

		}
		return resultado;
	}

}
