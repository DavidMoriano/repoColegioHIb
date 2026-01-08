package daoImp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dao.IAlumnosDAO;
import dto.AlumnoDTO;
import utils.DBUtils;

public class AlumnosDAOImpl implements IAlumnosDAO {
	private static Logger logger = LoggerFactory.getLogger(AlumnosDAOImpl.class);

	@Override
	public ArrayList<AlumnoDTO> obtenerTodosAlumnos() {
		Connection connection = DBUtils.conexion();
		ResultSet alumnos = null;
		ArrayList<AlumnoDTO> listaAlumnos = new ArrayList<>();
		Statement statement;

		try {

			statement = connection.createStatement();
			alumnos = statement.executeQuery("SELECT * FROM alumnos");

			while (alumnos.next()) {
				AlumnoDTO a = new AlumnoDTO(alumnos.getInt(1), alumnos.getString(2), alumnos.getString(3));
				logger.debug("Contenido de alumno " + a.getNombre() + " " + a.getId());
				listaAlumnos.add(a);
			}
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listaAlumnos;
	}

	@Override
	public ArrayList<AlumnoDTO> obtenerAlumnosPorIdNombreApellido(String id, String nombre, String apellido,
			int familiaNumerosa, int activo) {
		String sql = "SELECT a.id, a.nombre, a.apellidos, m.nombre, m.id_municipio, a.familia_numerosa, a.activo "
				+ "FROM alumnos a, municipios m " + "WHERE a.id_municipio = m.id_municipio "
				+ "AND a.id LIKE ? AND a.nombre LIKE ? AND a.apellidos LIKE ? "
				+ "AND familia_numerosa = ? AND activo = ?";

		ResultSet alumnoResultSet = null;
		Connection connection = DBUtils.conexion();
		ArrayList<AlumnoDTO> listaAlumnos = new ArrayList<>();

		try {
			PreparedStatement ps = connection.prepareStatement(sql);

			ps.setString(1, "%" + id + "%");
			ps.setString(2, "%" + nombre + "%");
			ps.setString(3, "%" + apellido + "%");
			ps.setInt(4, familiaNumerosa);
			ps.setInt(5, activo);

			logger.debug("Query a ejecutar: " + ps);

			alumnoResultSet = ps.executeQuery();

			while (alumnoResultSet.next()) {
				AlumnoDTO a = new AlumnoDTO(alumnoResultSet.getInt(1), alumnoResultSet.getString(2),
						alumnoResultSet.getString(3), alumnoResultSet.getString(4), alumnoResultSet.getInt(5),
						alumnoResultSet.getInt(6), alumnoResultSet.getInt(7));
				listaAlumnos.add(a);
			}
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listaAlumnos;
	}

	@Override
	public int insertarAlumno(String id, String nombre, String apellido, String idMunicipio, int familiaNumerosa,
			int activo) {
		String sql = "INSERT INTO alumnos (id, nombre, apellidos, id_municipio, familia_numerosa, activo) VALUES (?, ?, ?, ?, ?, ?)";
		PreparedStatement ps = null;
		int resultado = 0;

		try {
			Connection connection = DBUtils.conexion();
			ps = connection.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, nombre);
			ps.setString(3, apellido);
			ps.setString(4, idMunicipio);
			ps.setInt(5, familiaNumerosa);
			ps.setInt(6, activo);

			resultado = ps.executeUpdate();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return resultado;
	}

	@Override
	public int actualizarAlumno(String id, String nombre, String apellido, String idMunicipio, int familiaNumerosa,
			int activo) {
		String sql = "UPDATE alumnos SET nombre = ?, apellidos = ?, id_municipio = ?, familia_numerosa = ?, activo = ? "
				+ "WHERE id =  ? ";
		PreparedStatement ps = null;
		int resultado = 0;

		try {
			Connection connection = DBUtils.conexion();
			ps = connection.prepareStatement(sql);
			ps.setString(1, nombre);
			ps.setString(2, apellido);
			ps.setString(3, idMunicipio);
			ps.setInt(4, familiaNumerosa);
			ps.setInt(5, activo);
			ps.setString(6, id);
			logger.debug("Query a ejecutar: " + ps);
			resultado = ps.executeUpdate();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultado;
	}

	@Override
	public int borrarAlumno(String id) {
		String sql = "UPDATE alumnos SET activo = 0 "
				+ "WHERE id = ? ";
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}

	@Override
	public boolean esFamiliaNumerosa(String idAlumno) {
		String sql = "SELECT familia_numerosa FROM alumnos WHERE id = ?";
		boolean esFamiliaNumerosa = false;

		try {
			Connection connection = DBUtils.conexion();
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, idAlumno);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				esFamiliaNumerosa = rs.getBoolean("familia_numerosa");
			}

			connection.close();
			logger.debug("Alumno " + idAlumno + " es familia numerosa: " + esFamiliaNumerosa);
		} catch (SQLException e) {
			logger.error("Error al verificar si es familia numerosa", e);
			e.printStackTrace();
		}

		return esFamiliaNumerosa;
	}

	@Override
	public int contarAsignaturasMatriculadas(String idAlumno) {
		String sql = "SELECT COUNT(*) as total FROM matriculaciones WHERE id_alumnos = ?";
		int count = 0;

		try {
			Connection connection = DBUtils.conexion();
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, idAlumno);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				count = rs.getInt("total");
			}

			connection.close();
			logger.debug("Alumno " + idAlumno + " tiene " + count + " asignaturas matriculadas");
		} catch (SQLException e) {
			logger.error("Error al contar asignaturas matriculadas", e);
			e.printStackTrace();
		}

		return count;
	}

}
