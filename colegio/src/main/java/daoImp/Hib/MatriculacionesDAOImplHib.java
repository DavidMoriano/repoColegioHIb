package daoImp.Hib;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import dao.IMatriculacionesDAO;
import dto.MatriculacionDTO;
import entities.AlumnoEntity;
import entities.AsignaturaEntity;
import entities.CajaEntity;
import entities.MatriculacionEntity;
import utils.DBUtils;

public class MatriculacionesDAOImplHib implements IMatriculacionesDAO {

	@Override
	public double obtenerTasaAsignatura(String idAsignatura) {

	    SessionFactory factory = DBUtils.creadorSessionFactory();
	    Session s = factory.getCurrentSession();
	    double resultado = 0.0;

	    try {
	        s.beginTransaction();

	        String hql = "SELECT a.tasa FROM AsignaturaEntity a WHERE a.id = :id";

	        Query<Double> query = s.createQuery(hql, Double.class)
	                .setParameter("id", Integer.parseInt(idAsignatura));

	        Double tasa = query.uniqueResult();
	        resultado = (tasa != null) ? tasa : 0.0;

	        s.getTransaction().commit();
	    } finally {
	        s.close();
	    }

	    return resultado;
	}


	@Override
	public int insertarMatriculacion(String idAsignatura, String idAlumno,
	        String fecha, String tasa) throws SQLException {

	    SessionFactory factory = DBUtils.creadorSessionFactory();
	    Session s = factory.getCurrentSession();
	    int idMatricula = 0;

	    try {
	        s.beginTransaction();

	        AlumnoEntity alumno = s.find(AlumnoEntity.class, Integer.parseInt(idAlumno));
	        AsignaturaEntity asignatura = s.find(AsignaturaEntity.class, Integer.parseInt(idAsignatura));

	        MatriculacionEntity m = new MatriculacionEntity(
	                null, asignatura, alumno, fecha, 1
	        );

	        idMatricula = (Integer) s.save(m);

	        CajaEntity c = new CajaEntity();
	        c.setMatricula(m);
	        c.setImporte(Double.parseDouble(tasa));

	        s.save(c);

	        s.getTransaction().commit();
	    } catch (Exception e) {
	        if (s.getTransaction() != null) {
	            s.getTransaction().rollback();
	        }
	        throw e;
	    } finally {
	        s.close();
	    }

	    return idMatricula;
	}


	@Override
	public ArrayList<MatriculacionDTO> obtenerMatriculacionesPorFiltros(
	        String nombreAsignatura, String nombreAlumno, String fecha, int activo) {

	    String hql =
	        "SELECT new dto.MatriculacionDTO(" +
	        " m.id, a.id, a.nombre, al.id, al.nombre, m.fecha, m.activo, c.importe ) " +
	        "FROM MatriculacionEntity m " +
	        "JOIN m.asignatura a " +
	        "JOIN m.alumno al " +
	        "JOIN m.caja c " +
	        "WHERE a.nombre LIKE :asignatura " +
	        "AND al.nombre LIKE :alumno " +
	        "AND m.fecha >= :fecha " +
	        "AND m.activo = :activo";

	    SessionFactory factory = DBUtils.creadorSessionFactory();
	    Session s = factory.getCurrentSession();

	    try {
	        s.beginTransaction();

	        Query<MatriculacionDTO> query = s.createQuery(hql, MatriculacionDTO.class)
	            .setParameter("asignatura", "%" + nombreAsignatura + "%")
	            .setParameter("alumno", "%" + nombreAlumno + "%")
	            .setParameter("fecha", fecha)
	            .setParameter("activo", activo);

	        List<MatriculacionDTO> lista = query.getResultList();
	        s.getTransaction().commit();

	        return new ArrayList<>(lista);
	    } finally {
	        s.close();
	    }
	}


	@Override
	public ArrayList<MatriculacionDTO> obtenerMatriculacionesPorFiltrosSinFecha(
	        String nombreAsignatura, String nombreAlumno, int activo) {

	    SessionFactory factory = DBUtils.creadorSessionFactory();
	    Session s = factory.getCurrentSession();

	    try {
	        s.beginTransaction();

	        String hql =
	            "SELECT new dto.MatriculacionDTO(" +
	            " m.id, a.id, a.nombre, al.id, al.nombre, m.fecha, m.activo, c.importe ) " +
	            "FROM MatriculacionEntity m " +
	            "JOIN m.asignatura a " +
	            "JOIN m.alumno al " +
	            "JOIN m.caja c " +
	            "WHERE a.nombre LIKE :asignatura " +
	            "AND al.nombre LIKE :alumno " +
	            "AND m.activo = :activo";

	        Query<MatriculacionDTO> query = s.createQuery(hql, MatriculacionDTO.class)
	            .setParameter("asignatura", "%" + nombreAsignatura + "%")
	            .setParameter("alumno", "%" + nombreAlumno + "%")
	            .setParameter("activo", activo);

	        List<MatriculacionDTO> lista = query.getResultList();
	        s.getTransaction().commit();

	        return new ArrayList<>(lista);
	    } finally {
	        s.close();
	    }
	}



	@Override
	public int actualizarMatriculacion(String id, String idAsignatura,
	        String idAlumno, String fecha, String tasa) throws SQLException {

	    SessionFactory factory = DBUtils.creadorSessionFactory();
	    Session s = factory.getCurrentSession();

	    try {
	        s.beginTransaction();

	        MatriculacionEntity m = s.get(MatriculacionEntity.class, Integer.parseInt(id));
	        AsignaturaEntity asignatura = s.find(AsignaturaEntity.class, Integer.parseInt(idAsignatura));
	        AlumnoEntity alumno = s.find(AlumnoEntity.class, Integer.parseInt(idAlumno));

	        m.setAsignatura(asignatura);
	        m.setAlumno(alumno);
	        m.setFecha(fecha);

	        CajaEntity c = m.getCaja();
	        c.setImporte(Double.parseDouble(tasa));

	        s.getTransaction().commit();
	        return m.getId();
	    } finally {
	        s.close();
	    }
	}


	@Override
	public int borrarMatriculacion(String id) throws SQLException {

	    SessionFactory factory = DBUtils.creadorSessionFactory();
	    Session s = factory.getCurrentSession();

	    try {
	        s.beginTransaction();

	        MatriculacionEntity m = s.get(MatriculacionEntity.class, Integer.parseInt(id));
	        m.setActivo(0);

	        CajaEntity c = m.getCaja();
	        s.delete(c);

	        s.getTransaction().commit();
	        return m.getId();
	    } finally {
	        s.close();
	    }
	}

}
