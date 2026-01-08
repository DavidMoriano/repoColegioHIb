package entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "alumnos")

public class AlumnoEntity {
	@Id
	@Column(name = "id")
	private int id;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "apellidos")
	private String apellidos;

	@Column(name = "familia_numerosa")
	private int famNumerosa;

	@Column(name = "activo")
	private int activo;

	@ManyToOne
	@JoinColumn(name = "id_municipio")
	private MunicipiosEntity municipio;

	@OneToMany(mappedBy = "id")
	Set<MatriculacionEntity> matriculaciones;

	@OneToMany(mappedBy = "alumno", fetch = FetchType.EAGER)
	Set<NotaEntity> notas;

	public AlumnoEntity(int id, String nombre, String apellidos, int famNumerosa, int activo,
			MunicipiosEntity municipio) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.famNumerosa = famNumerosa;
		this.activo = activo;
		this.municipio = municipio;
	}

	public AlumnoEntity() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public int getFamNumerosa() {
		return famNumerosa;
	}

	public void setFamNumerosa(int famNumerosa) {
		this.famNumerosa = famNumerosa;
	}

	public int getActivo() {
		return activo;
	}

	public void setActivo(int activo) {
		this.activo = activo;
	}

	public MunicipiosEntity getMunicipio() {
		return municipio;
	}

	public void setMunicipio(MunicipiosEntity municipio) {
		this.municipio = municipio;
	}

	public Set<MatriculacionEntity> getMatriculaciones() {
		return matriculaciones;
	}

	public void setMatriculaciones(Set<MatriculacionEntity> matriculaciones) {
		this.matriculaciones = matriculaciones;
	}

	public Set<NotaEntity> getNotas() {
		return notas;
	}

	public void setNotas(Set<NotaEntity> notas) {
		this.notas = notas;
	}

}
