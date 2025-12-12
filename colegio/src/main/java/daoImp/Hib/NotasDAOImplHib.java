package daoImp.Hib;

import dao.INotasDAO;
import dto.NotaDTO;
import entities.NotasEntity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class NotasDAOImplHib implements INotasDAO {

	private final SessionFactory sessionFactory;

	public NotasDAOImplHib(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public ArrayList<NotaDTO> obtenerTodasNotas() {
		Session session = sessionFactory.openSession();
		ArrayList<NotaDTO> lista = new ArrayList<>();

		try {
			String hql = "SELECT new dto.NotaDTO(n.id, n.nota, n.id_asignaturas, a.nombre, n.id_alumnos, al.nombre, n.fecha) "
					+ "FROM NotasEntity n " + "JOIN AlumnosEntity al ON n.id_alumnos = al.id "
					+ "JOIN AsignaturasEntity a ON n.id_asignaturas = a.id " + "WHERE al.activo = 1 " + "ORDER BY n.id";

			Query<NotaDTO> query = session.createQuery(hql, NotaDTO.class);
			List<NotaDTO> result = query.getResultList();
			lista.addAll(result);

		} catch (Exception e) {
		} finally {
			session.close();
		}
		return lista;
	}

	@Override
	public ArrayList<NotaDTO> obtenerNotasPorFiltros(String idAlumno, String nombreAlumno, String asignatura,
			String nota, String fecha, int activo) {

		StringBuilder hql = new StringBuilder(
				"SELECT new dto.NotaDTO(n.id, n.nota, n.id_asignaturas, a.nombre, n.id_alumnos, al.nombre, n.fecha) "
						+ "FROM NotasEntity n " + "JOIN AlumnosEntity al ON n.id_alumnos = al.id "
						+ "JOIN AsignaturasEntity a ON n.id_asignaturas = a.id " + "WHERE al.activo = :activo");

		if (idAlumno != null && !idAlumno.trim().isEmpty()) {
			hql.append(" AND n.id_alumnos LIKE :idAlumno");
		}
		if (nombreAlumno != null && !nombreAlumno.trim().isEmpty()) {
			hql.append(" AND al.nombre LIKE :nombreAlumno");
		}
		if (asignatura != null && !asignatura.trim().isEmpty()) {
			hql.append(" AND a.nombre LIKE :asignatura");
		}
		if (nota != null && !nota.trim().isEmpty()) {
			hql.append(" AND CAST(n.nota AS string) LIKE :nota");
		}
		if (fecha != null && !fecha.trim().isEmpty()) {
			hql.append(" AND n.fecha >= :fecha");
		}

		hql.append(" ORDER BY n.id");

		Session session = sessionFactory.openSession();
		Transaction tx = null;
		ArrayList<NotaDTO> lista = new ArrayList<>();

		try {
			tx = session.beginTransaction();
			Query<NotaDTO> query = session.createQuery(hql.toString(), NotaDTO.class);
			query.setParameter("activo", activo);

			if (idAlumno != null && !idAlumno.trim().isEmpty())
				query.setParameter("idAlumno", "%" + idAlumno + "%");
			if (nombreAlumno != null && !nombreAlumno.trim().isEmpty())
				query.setParameter("nombreAlumno", "%" + nombreAlumno + "%");
			if (asignatura != null && !asignatura.trim().isEmpty())
				query.setParameter("asignatura", "%" + asignatura + "%");
			if (nota != null && !nota.trim().isEmpty())
				query.setParameter("nota", "%" + nota + "%");
			if (fecha != null && !fecha.trim().isEmpty())
				query.setParameter("fecha", fecha);

			List<NotaDTO> result = query.getResultList();
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
	public ArrayList<NotaDTO> obtenerNotasPorFiltrosSinFecha(String idAlumno, String nombreAlumno, String asignatura,
			String nota, int activo) {
		return obtenerNotasPorFiltros(idAlumno, nombreAlumno, asignatura, nota, null, activo);
	}

	@Override
	public int insertarNota(String idAlumno, String idAsignatura, String nota, String fecha) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		int resultado = 0;

		try {
			tx = session.beginTransaction();

			NotasEntity entity = new NotasEntity();
			entity.setId_alumnos(idAlumno);
			entity.setId_asignaturas(idAsignatura);
			entity.setNota(Double.parseDouble(nota));
			entity.setFecha(fecha);

			session.persist(entity);
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
	public int actualizarNota(String id, String idAlumno, String idAsignatura, String nota, String fecha) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		int resultado = 0;

		try {
			tx = session.beginTransaction();
			NotasEntity entity = session.get(NotasEntity.class, id);

			if (entity != null) {
				entity.setId_alumnos(idAlumno);
				entity.setId_asignaturas(idAsignatura);
				entity.setNota(Double.parseDouble(nota));
				entity.setFecha(fecha);
				session.merge(entity);
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

	@Override
	public int borrarNota(String id) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		int resultado = 0;

		try {
			tx = session.beginTransaction();
			NotasEntity entity = session.get(NotasEntity.class, id);
			if (entity != null) {
				session.remove(entity);
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