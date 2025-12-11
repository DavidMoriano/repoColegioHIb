package serviciosImp;

import java.sql.SQLException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dao.IAlumnosDAO;
import dao.IAsignaturasDAO;
import dao.IMatriculacionesDAO;
import daoImp.AlumnosDAOImpl;
import daoImp.AsignaturasDAOImpl;
import daoImp.MatriculacionesDAOImpl;
import dto.MatriculacionDTO;
import servicios.IMatriculacionesService;

public class MatriculacionesServiceImp implements IMatriculacionesService {
	private static Logger logger = LoggerFactory.getLogger(MatriculacionesServiceImp.class);

	@Override
	public double calcularTasa(String idAlumno, String idAsignatura) {
		IAsignaturasDAO asignaturasDAO = new AsignaturasDAOImpl();
		IAlumnosDAO alumnosDAO = new AlumnosDAOImpl();

		// Obtener tasa base de la asignatura
		double tasaBase = asignaturasDAO.obtenerTasaAsignatura(idAsignatura);

		// Ver cuántas asignaturas tiene matriculadas el alumno
		int numAsignaturasMatriculadas = alumnosDAO.contarAsignaturasMatriculadas(idAlumno);

		// Verificar si el alumno es familia numerosa
		boolean esFamiliaNumerosa = alumnosDAO.esFamiliaNumerosa(idAlumno);
		logger.debug("Alumno " + idAlumno + " es familia numerosa: " + esFamiliaNumerosa);

		// Aplicar lógica de negocio de descuentos
		double tasaFinal = tasaBase;

		// Descuento por número de asignaturas matriculadas
		if (numAsignaturasMatriculadas >= 3 && numAsignaturasMatriculadas <= 5) {
			tasaFinal = tasaFinal * 0.70; // 30% descuento
			logger.debug("Aplicado descuento 30% (3-5 asignaturas): " + tasaFinal);
		} else if (numAsignaturasMatriculadas >= 6) {
			tasaFinal = tasaFinal * 0.50; // 50% descuento
			logger.debug("Aplicado descuento 50% (6+ asignaturas): " + tasaFinal);
		}

		// Descuento adicional por familia numerosa
		if (esFamiliaNumerosa) {
			tasaFinal = tasaFinal * 0.50; // 50% adicional
		}

		logger.info(
				"Tasa final calculada para alumno " + idAlumno + " en asignatura " + idAsignatura + ": " + tasaFinal);

		return tasaFinal;
	}

	@Override
	public int insertarMatriculacion(String idAsignatura, String idAlumno, String fecha, String tasa) {
		IMatriculacionesDAO matriculaciones = new MatriculacionesDAOImpl();
		try {
			return matriculaciones.insertarMatriculacion(idAsignatura, idAlumno, fecha, tasa);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public ArrayList<MatriculacionDTO> obtenerMatriculacionesPorFiltros(String nombreAsignatura, String nombreAlumno,
			String fecha, int activo) {
		IMatriculacionesDAO matriculaciones = new MatriculacionesDAOImpl();
		return matriculaciones.obtenerMatriculacionesPorFiltros(nombreAsignatura, nombreAlumno, fecha, activo);
	}

	@Override
	public ArrayList<MatriculacionDTO> obtenerMatriculacionesPorFiltrosSinFecha(String nombreAsignatura,
			String nombreAlumno, int activo) {
		IMatriculacionesDAO matriculaciones = new MatriculacionesDAOImpl();
		return matriculaciones.obtenerMatriculacionesPorFiltrosSinFecha(nombreAsignatura, nombreAlumno, activo);
	}

	@Override
	public int actualizarMatriculacion(String id, String idAsignatura, String idAlumno, String fecha, String tasa) {
		IMatriculacionesDAO matriculaciones = new MatriculacionesDAOImpl();
		try {
			return matriculaciones.actualizarMatriculacion(id, idAsignatura, idAlumno, fecha, tasa);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int borrarMatriculacion(String id) {
		IMatriculacionesDAO matriculaciones = new MatriculacionesDAOImpl();
		try {
			return matriculaciones.borrarMatriculacion(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
}
