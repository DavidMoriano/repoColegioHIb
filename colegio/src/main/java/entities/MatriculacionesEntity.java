package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "matriculaciones")
public class MatriculacionesEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "id_asignatura")
	private AsignaturasEntity asignatura;

	@ManyToOne
	@JoinColumn(name = "id_alumno")
	private AlumnoEntity alumno;

	@Column(name = "fecha")
	private String fecha;

	@Column(name = "activo")
	private Integer activo;

	@OneToOne(mappedBy = "matricula")
	private CajaEntity caja;
	
	public MatriculacionesEntity() {
		
	}

	public MatriculacionesEntity(Integer id, AsignaturasEntity asignatura, AlumnoEntity alumno, String fecha,
			Integer activo, CajaEntity caja) {
		super();
		this.id = id;
		this.asignatura = asignatura;
		this.alumno = alumno;
		this.fecha = fecha;
		this.activo = activo;
		this.caja = caja;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public AsignaturasEntity getAsignatura() {
		return asignatura;
	}

	public void setAsignatura(AsignaturasEntity asignatura) {
		this.asignatura = asignatura;
	}

	public AlumnoEntity getAlumno() {
		return alumno;
	}

	public void setAlumno(AlumnoEntity alumno) {
		this.alumno = alumno;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public Integer getActivo() {
		return activo;
	}

	public void setActivo(Integer activo) {
		this.activo = activo;
	}

	public CajaEntity getCaja() {
		return caja;
	}

	public void setCaja(CajaEntity caja) {
		this.caja = caja;
	}
	
	
	
}
