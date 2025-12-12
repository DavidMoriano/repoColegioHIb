package daoImp.Hib;

import dao.IMatriculacionesDAO;
import dto.MatriculacionDTO;
import entities.AsignaturasEntity;
import entities.AlumnoEntity;
import entities.CajaEntity;
import entities.MatriculacionesEntity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class MatriculacionesDAOImplHib implements IMatriculacionesDAO {
	private final SessionFactory sessionFactory;

	public MatriculacionesDAOImplHib(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public double obtenerTasaAsignatura(String idAsignatura) {
		Session session = sessionFactory.openSession();
		double tasa = 0.0;

		try {
			Query<Double> query = session.createQuery(
					"SELECT a.tasa FROM AsignaturasEntity a WHERE a.id = :id AND a.activo = 1", Double.class);
			query.setParameter("id", idAsignatura);
			Double result = query.uniqueResult();
			if (result != null) {
				tasa = result;
			}
		} catch (Exception e) {
		} finally {
			session.close();
		}
		return tasa;
	}

	@Override
	public int insertarMatriculacion(String idAsignatura, String idAlumno, String fecha, String tasa) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		int resultado = 0;

		try {
			tx = session.beginTransaction();

			AsignaturasEntity asignatura = session.get(AsignaturasEntity.class, idAsignatura);
			AlumnoEntity alumno = session.get(AlumnoEntity.class, idAlumno);

			if (asignatura == null || alumno == null) {
				throw new IllegalArgumentException("Asignatura o alumno no encontrado");
			}

			MatriculacionesEntity matricula = new MatriculacionesEntity();
			matricula.setAsignatura(asignatura);
			matricula.setAlumno(alumno);
			matricula.setFecha(fecha);
			matricula.setActivo(1);

			session.persist(matricula);

			CajaEntity caja = new CajaEntity();
			caja.setMatricula(matricula);
			caja.setImporte(Double.parseDouble(tasa));

			session.persist(caja);

			tx.commit();
			resultado = 1;
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
		} finally {
			session.close();
		}
		return resultado;
	}

	@Override
	public ArrayList<MatriculacionDTO> obtenerMatriculacionesPorFiltros(String nombreAsignatura, String nombreAlumno,
			String fecha, int activo) {

		StringBuilder hql = new StringBuilder("SELECT new dto.MatriculacionDTO("
				+ "m.id, m.asignatura.id, m.asignatura.nombre, "
				+ "m.alumno.id, m.alumno.nombre, m.fecha, m.activo, c.importe) " + "FROM MatriculacionesEntity m "
				+ "JOIN m.asignatura " + "JOIN m.alumno " + "LEFT JOIN m.caja c " + "WHERE m.activo = :activo");

		if (nombreAsignatura != null && !nombreAsignatura.trim().isEmpty()) {
			hql.append(" AND m.asignatura.nombre LIKE :nombreAsignatura");
		}
		if (nombreAlumno != null && !nombreAlumno.trim().isEmpty()) {
			hql.append(" AND m.alumno.nombre LIKE :nombreAlumno");
		}
		if (fecha != null && !fecha.trim().isEmpty()) {
			hql.append(" AND m.fecha >= :fecha");
		}

		hql.append(" ORDER BY m.id");

		Session session = sessionFactory.openSession();
		Transaction tx = null;
		ArrayList<MatriculacionDTO> lista = new ArrayList<>();

		try {
			tx = session.beginTransaction();
			Query<MatriculacionDTO> query = session.createQuery(hql.toString(), MatriculacionDTO.class);
			query.setParameter("activo", activo);

			if (nombreAsignatura != null && !nombreAsignatura.trim().isEmpty())
				query.setParameter("nombreAsignatura", "%" + nombreAsignatura + "%");
			if (nombreAlumno != null && !nombreAlumno.trim().isEmpty())
				query.setParameter("nombreAlumno", "%" + nombreAlumno + "%");
			if (fecha != null && !fecha.trim().isEmpty())
				query.setParameter("fecha", fecha);

			List<MatriculacionDTO> result = query.getResultList();
			lista.addAll(result);

			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
		} finally {
			session.close();
		}
		return lista;
	}

	@Override
	public ArrayList<MatriculacionDTO> obtenerMatriculacionesPorFiltrosSinFecha(String nombreAsignatura,
			String nombreAlumno, int activo) {
		return obtenerMatriculacionesPorFiltros(nombreAsignatura, nombreAlumno, null, activo);
	}

	@Override
	public int actualizarMatriculacion(String id, String idAsignatura, String idAlumno, String fecha, String tasa) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		int resultado = 0;

		try {
			tx = session.beginTransaction();

			MatriculacionesEntity matricula = session.get(MatriculacionesEntity.class, Integer.parseInt(id));
			if (matricula == null) {
				throw new IllegalArgumentException("Matriculación no encontrada");
			}

			AsignaturasEntity asignatura = session.get(AsignaturasEntity.class, idAsignatura);
			AlumnoEntity alumno = session.get(AlumnoEntity.class, idAlumno);

			if (asignatura == null || alumno == null) {
				throw new IllegalArgumentException("Asignatura o alumno no encontrado");
			}

			matricula.setAsignatura(asignatura);
			matricula.setAlumno(alumno);
			matricula.setFecha(fecha);

			CajaEntity caja = matricula.getCaja();
			if (caja != null) {
				caja.setImporte(Double.parseDouble(tasa));
			} else {
				caja = new CajaEntity();
				caja.setMatricula(matricula);
				caja.setImporte(Double.parseDouble(tasa));
				session.persist(caja);
			}

			session.merge(matricula);
			tx.commit();
			resultado = 1;
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
		} finally {
			session.close();
		}
		return resultado;
	}

	@Override
	public int borrarMatriculacion(String id) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		int resultado = 0;

		try {
			tx = session.beginTransaction();

			MatriculacionesEntity matricula = session.get(MatriculacionesEntity.class, Integer.parseInt(id));
			if (matricula != null) {

				session.remove(matricula);
				resultado = 1;
			}

			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
		} finally {
			session.close();
		}
		return resultado;
	}
}