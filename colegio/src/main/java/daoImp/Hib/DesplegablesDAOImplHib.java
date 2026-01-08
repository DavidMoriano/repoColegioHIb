package daoImp.Hib;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.hibernate.query.Query;

import dao.IDesplegableDAO;
import dto.DesplegableDTO;
import utils.DBUtils;

public class DesplegablesDAOImplHib implements IDesplegableDAO {
	@Override
	public ArrayList<DesplegableDTO> desplegableMunicipios() {
		String hql = "SELECT new dto.DesplegableDTO(m.idMunicipio, m.nombre)FROM MunicipiosEntity m";
		SessionFactory factory = DBUtils.creadorSessionFactory();
		Session s = factory.getCurrentSession();
		s.beginTransaction();
		Query<DesplegableDTO> query = s.createQuery(hql, DesplegableDTO.class);
		List<DesplegableDTO> lista = query.getResultList();
		s.close();
		return new ArrayList<>(lista);
	}
//Resto de desplegables que no hemos implementado con Hibernate (aun)

	@Override
	public ArrayList<DesplegableDTO> desplegableAlumnos() {
		String hql = "SELECT new dto.DesplegableDTO(a.id, a.nombre) FROM AlumnoEntity a";
		SessionFactory factory = DBUtils.creadorSessionFactory();
		Session s = factory.getCurrentSession();
		s.beginTransaction();
		
		Query<DesplegableDTO> query = s.createQuery(hql, DesplegableDTO.class);
		List<DesplegableDTO> lista = query.getResultList();
		s.close();
		return new ArrayList<>(lista);
	}

	@Override
	public ArrayList<DesplegableDTO> desplegableAsignaturas() {
		String hql = "SELECT new dto.DesplegableDTO(a.id, a.nombre) FROM AsignaturaEntity a";
		SessionFactory factory = DBUtils.creadorSessionFactory();
		Session s = factory.getCurrentSession();
		s.beginTransaction();
		
		Query<DesplegableDTO> query = s.createQuery(hql, DesplegableDTO.class);
		List<DesplegableDTO> lista = query.getResultList();
		s.close();
		return new ArrayList<>(lista);
	}

	@Override
	public ArrayList<DesplegableDTO> desplegableMatriculaciones() {
		// TODO Auto-generated method stub
		return null;
	}
}
