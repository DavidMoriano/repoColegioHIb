package daoImp.Hib;

import dao.IAsignaturasDAO;
import dto.AsignaturaDTO;
import entities.AsignaturasEntity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class AsignaturasDAOImplHib implements IAsignaturasDAO {

	private static final Logger logger = LoggerFactory.getLogger(AsignaturasDAOImplHib.class);
	private final SessionFactory sessionFactory;

	public AsignaturasDAOImplHib(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<AsignaturaDTO> obtenerTodasAsignaturas() {
		return obtenerTodasAsignaturas();
	}

	@Override
	public List<AsignaturaDTO> obtenerAsignaturasPorFiltros(String id, String nombre, String curso, String tasa,
			int activo) {
		StringBuilder hql = new StringBuilder("FROM Asignatura a WHERE a.activo = :activo");
			
		if (id != null && !id.trim().isEmpty()) {
			hql.append(" AND a.id LIKE :id");
		}
		if (nombre != null && !nombre.trim().isEmpty()) {
			hql.append(" AND a.nombre LIKE :nombre");
		}
		if (curso != null && !curso.trim().isEmpty()) {
			hql.append(" AND a.curso LIKE :curso");
		}
		if (tasa != null && !tasa.trim().isEmpty()) {
			hql.append(" AND a.tasa >= :tasa");
		}

		hql.append(" ORDER BY a.id");

		Session session = sessionFactory.openSession();
		Transaction tx = null;
		List<AsignaturaDTO> lista = new ArrayList<>();

		try {
			tx = session.beginTransaction();
			Query<AsignaturaDTO> query = session.createQuery(hql.toString(), AsignaturaDTO.class);
			query.setParameter("activo", activo);

			if (id != null && !id.toString().trim().isEmpty())
				query.setParameter("id", "%" + id + "%");
			if (nombre != null && !nombre.trim().isEmpty())
				query.setParameter("nombre", "%" + nombre + "%");
			if (curso != null && !curso.trim().isEmpty())
				query.setParameter("curso", "%" + curso + "%");
			if (tasa != null && !tasa.trim().isEmpty())
				query.setParameter("tasa", Double.parseDouble(tasa));

			logger.debug("HQL ejecutado: {}", hql);
			lista = query.getResultList();

			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			logger.error("Error al listar asignaturas con filtros", e);
		} finally {
			session.close();
		}
		return lista;
	}

	@Override
	public int insertarAsignatura(int id, String nombre, String curso, String tasa, int activo) {
		AsignaturaDTO asignatura = new AsignaturaDTO(id, nombre, curso, Double.parseDouble(tasa), activo);

		Session session = sessionFactory.openSession();
		Transaction tx = null;
		int resultado = 0;

		try {
			tx = session.beginTransaction();
			session.persist(asignatura);
			tx.commit();
			resultado = 1;
			logger.debug("Asignatura insertada: {}", asignatura.getId());
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			logger.error("Error al insertar asignatura", e);
		} finally {
			session.close();
		}
		return resultado;
	}

	@Override
	public int actualizarAsignatura(String id, String nombre, String curso, String tasa, int activo) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		int resultado = 0;

		try {
			tx = session.beginTransaction();
			AsignaturasEntity asignatura = session.get(AsignaturasEntity.class, id);
			if (asignatura != null) {
				asignatura.setNombre(nombre);
				asignatura.setCurso(curso);
				asignatura.setTasa(Double.parseDouble(tasa));
				asignatura.setActivo(activo);
				session.merge(asignatura);
				resultado = 1;
				logger.debug("Asignatura actualizada: {}", id);
			}
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			logger.error("Error al actualizar asignatura", e);
		} finally {
			session.close();
		}
		return resultado;
	}

	@Override
	public int borrarAsignatura(String id) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		int resultado = 0;

		try {
			tx = session.beginTransaction();
			AsignaturasEntity asignatura = session.get(AsignaturasEntity.class, id);
			if (asignatura != null) {
				asignatura.setActivo(0);
				session.merge(asignatura);
				resultado = 1;
				logger.debug("Asignatura borrada lógicamente: {}", id);
			}
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			logger.error("Error al borrar asignatura", e);
		} finally {
			session.close();
		}
		return resultado;
	}

	@Override
	public double obtenerTasaAsignatura(String idAsignatura) {
		Session session = sessionFactory.openSession();
		double tasa = 0.0;

		try {
			Query<Double> query = session
					.createQuery("SELECT a.tasa FROM Asignatura a WHERE a.id = :id AND a.activo = 1", Double.class);
			query.setParameter("id", idAsignatura);
			Double result = query.uniqueResult();
			if (result != null) {
				tasa = result;
			}
		} catch (Exception e) {
			logger.error("Error al obtener tasa de asignatura", e);
		} finally {
			session.close();
		}
		return tasa;
	}
}