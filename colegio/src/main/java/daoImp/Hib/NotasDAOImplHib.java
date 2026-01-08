package daoImp.Hib;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import dao.INotasDAO;
import dto.NotaDTO;
import entities.AlumnoEntity;
import entities.AsignaturaEntity;
import entities.NotaEntity;
import utils.DBUtils;

public class NotasDAOImplHib implements INotasDAO {

	@Override
	public ArrayList<NotaDTO> obtenerTodasNotas() {
		String hql = "FROM NotaEntity";
		SessionFactory factory = DBUtils.creadorSessionFactory();
		Session s = factory.getCurrentSession();
		s.beginTransaction();
		Query<NotaDTO> query = s.createQuery(hql, NotaDTO.class);
		ArrayList<NotaDTO> lista = (ArrayList<NotaDTO>) query.getResultList();
		s.close();
		return lista;
	}
	
	@Override
	public ArrayList<NotaDTO> obtenerNotasPorFiltros(String idAlumno, String nombreAlumno, String asignatura,
			String nota, String fecha, int activo) {
		String hql = "SELECT new dto.NotaDTO(n.id, n.nota, a.id, a.nombre, al.id, al.nombre, n.fecha) "
				+ "FROM NotaEntity n " + "JOIN n.asignatura a " + "JOIN n.alumno al "
				+ "WHERE CAST(al.id AS string) LIKE :idAlumno " + "AND al.nombre LIKE :nombreAlumno "
				+ "AND a.nombre LIKE :asignatura " + "AND n.nota LIKE :nota " + "AND n.fecha >= :fecha "
				+ "AND al.activo = :activo ";

		SessionFactory factory = DBUtils.creadorSessionFactory();
		Session s = factory.getCurrentSession();
		s.beginTransaction();

		Query<NotaDTO> query = s.createQuery(hql, NotaDTO.class).setParameter("idAlumno", "%" + idAlumno + "%")
				.setParameter("nombreAlumno", "%" + nombreAlumno + "%")
				.setParameter("asignatura", "%" + asignatura + "%").setParameter("nota", "%" + nota + "%")
				.setParameter("fecha", fecha).setParameter("activo", activo);

		List<NotaDTO> lista = query.getResultList();
		s.close();

		return new ArrayList<>(lista);
	}
	

	@Override
	public ArrayList<NotaDTO> obtenerNotasPorFiltrosSinFecha(String idAlumno, String nombreAlumno, String asignatura,
			String nota, int activo) {

		String hql = "SELECT new dto.NotaDTO(n.id, n.nota, a.id, a.nombre, al.id, al.nombre, n.fecha) " + "FROM NotaEntity n "
				+ "JOIN n.asignatura a " + "JOIN n.alumno al " + "WHERE CAST(al.id AS string) LIKE :idAlumno "
				+ "AND al.nombre LIKE :nombreAlumno " + "AND a.nombre LIKE :asignatura " + "AND n.nota LIKE :nota "
				+ "AND al.activo = :activo ";

		SessionFactory factory = DBUtils.creadorSessionFactory();
		Session s = factory.getCurrentSession();
		s.beginTransaction();

		Query<NotaDTO> query = s.createQuery(hql, NotaDTO.class).setParameter("idAlumno", "%" + idAlumno + "%")
				.setParameter("nombreAlumno", "%" + nombreAlumno + "%")
				.setParameter("asignatura", "%" + asignatura + "%").setParameter("nota", "%" + nota + "%")
				.setParameter("activo", activo);

		List<NotaDTO> lista = query.getResultList();
		s.close();

		return new ArrayList<>(lista);
	}


	@Override
	public int insertarNota(String idAlumno, String idAsignatura, String nota, String fecha) {
		SessionFactory factory = DBUtils.creadorSessionFactory();
		Session s = factory.getCurrentSession();
		s.beginTransaction();

		AlumnoEntity alumno = s.find(AlumnoEntity.class, Integer.parseInt(idAlumno));
		AsignaturaEntity asignatura = s.find(AsignaturaEntity.class, Integer.parseInt(idAsignatura));
		NotaEntity n = new NotaEntity();
		n.setAlumno(alumno);
		n.setAsignatura(asignatura);
		n.setNota(nota);
		n.setFecha(fecha);

		Integer idPk = (Integer) s.save(n);
		s.getTransaction().commit();
		s.close();
		return idPk;
	}

	@Override
	public int actualizarNota(String id, String idAlumno, String idAsignatura, String nota, String fecha) {
		SessionFactory factory = DBUtils.creadorSessionFactory();
		Session s = factory.getCurrentSession();
		s.beginTransaction();

		AlumnoEntity alumno = s.find(AlumnoEntity.class, Integer.parseInt(idAlumno));
		AsignaturaEntity asignatura = s.find(AsignaturaEntity.class, Integer.parseInt(idAsignatura));

		NotaEntity n = s.get(NotaEntity.class, Integer.parseInt(id));
		n.setAlumno(alumno);
		n.setAsignatura(asignatura);
		n.setNota(nota);
		n.setFecha(fecha);

		s.update(n);
		s.getTransaction().commit();
		s.close();

		return n.getId();
	}

	@Override
	public int borrarNota(String id) {
		SessionFactory factory = DBUtils.creadorSessionFactory();
		Session s = factory.getCurrentSession();
		s.beginTransaction();

		NotaEntity n = s.get(NotaEntity.class, Integer.parseInt(id));
		s.delete(n);

		s.getTransaction().commit();
		s.close();

		return n.getId();
	}

}
