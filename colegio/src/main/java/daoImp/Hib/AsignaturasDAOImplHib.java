package daoImp.Hib;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import dao.IAsignaturasDAO;
import dto.AsignaturaDTO;
import entities.AsignaturaEntity;
import utils.DBUtils;

public class AsignaturasDAOImplHib implements IAsignaturasDAO {

	@Override
	public int insertarAsignatura(String id, String nombre, String curso, String tasa, int activo) {
		SessionFactory factory = DBUtils.creadorSessionFactory();
		Session s = factory.getCurrentSession();
		s.beginTransaction();

		AsignaturaEntity a = new AsignaturaEntity(Integer.parseInt(id), nombre, Integer.parseInt(curso),
				Double.parseDouble(tasa), activo);

		Integer idPk = (Integer) s.save(a);
		s.getTransaction().commit();
		s.close();
		return idPk;
	}

	@Override
	public ArrayList<AsignaturaDTO> obtenerTodasAsignaturas() {
		String hql = "SELECT new dto.AsignaturaDTO(a.id, a.nombre, a.curso, a.tasa, a.activo) "
				+ "FROM AsignaturaEntity a";

		SessionFactory factory = DBUtils.creadorSessionFactory();
		Session s = factory.getCurrentSession();
		s.beginTransaction();

		Query<AsignaturaDTO> query = s.createQuery(hql, AsignaturaDTO.class);
		List<AsignaturaDTO> lista = query.getResultList();

		s.close();
		return new ArrayList<>(lista);
	}

	@Override
	public ArrayList<AsignaturaDTO> obtenerAsignaturasPorFiltros(String id, String nombre, String curso, String tasa,
			int activo) {

		String hql = "SELECT new dto.AsignaturaDTO(a.id, a.nombre, a.curso, a.tasa, a.activo) "
				+ "FROM AsignaturaEntity a " + "WHERE CAST(a.id AS string) LIKE :id " + "AND a.nombre LIKE :nombre "
				+ "AND CAST(a.curso AS string) LIKE :curso " + "AND a.tasa >= :tasa " + "AND a.activo = :activo";

		double tasaMin = (tasa == null || tasa.trim().isEmpty()) ? 0.0 : Double.parseDouble(tasa);

		SessionFactory factory = DBUtils.creadorSessionFactory();
		Session s = factory.getCurrentSession();
		s.beginTransaction();

		Query<AsignaturaDTO> query = s.createQuery(hql, AsignaturaDTO.class).setParameter("id", "%" + id + "%")
				.setParameter("nombre", "%" + nombre + "%").setParameter("curso", "%" + curso + "%")
				.setParameter("tasa", tasaMin).setParameter("activo", activo);

		List<AsignaturaDTO> lista = query.getResultList();
		s.close();
		return new ArrayList<>(lista);
	}

	@Override
	public int actualizarAsignatura(String id, String nombre, String curso, String tasa, int activo) {
		SessionFactory factory = DBUtils.creadorSessionFactory();
		Session s = factory.getCurrentSession();
		s.beginTransaction();

		AsignaturaEntity a = new AsignaturaEntity(Integer.parseInt(id), nombre, Integer.parseInt(curso),
				Double.parseDouble(tasa), activo);

		s.update(a);
		s.getTransaction().commit();
		s.close();
		return a.getId();
	}

	@Override
	public int borrarAsignatura(String id) {
		SessionFactory factory = DBUtils.creadorSessionFactory();
		Session s = factory.getCurrentSession();
		s.beginTransaction();

		AsignaturaEntity a = s.get(AsignaturaEntity.class, Integer.parseInt(id));
		a.setActivo(0);
		s.update(a);

		s.getTransaction().commit();
		s.close();
		return a.getId();
	}

	@Override
	public double obtenerTasaAsignatura(String idAsignatura) {
		Session session = DBUtils.creadorSessionFactory().openSession();
		try {
			AsignaturaEntity a = session.get(
					AsignaturaEntity.class, Integer.parseInt(idAsignatura));
			return a != null && a.getTasa() != null ? a.getTasa(): 0.0;
		} finally {
			session.close();
		}
	}
}
