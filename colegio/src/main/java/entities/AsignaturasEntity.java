package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "asignaturas")
public class AsignaturasEntity {

	@Id
	@Column(name = "id", length = 50)
	private String id;

	@Column(name = "nombre", nullable = false, length = 100)
	private String nombre;

	@Column(name = "curso", length = 50)
	private String curso;

	@Column(name = "tasa", nullable = false, precision = 10, scale = 2)
	private Double tasa;

	@Column(name = "activo", nullable = false)
	private Integer activo = 1;

	public AsignaturasEntity() {
	}

	public AsignaturasEntity(String id, String nombre, String curso, Double tasa, Integer activo) {
		this.id = id;
		this.nombre = nombre;
		this.curso = curso;
		this.tasa = tasa;
		this.activo = activo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public Double getTasa() {
		return tasa;
	}

	public void setTasa(Double tasa) {
		this.tasa = tasa;
	}

	public Integer getActivo() {
		return activo;
	}

	public void setActivo(Integer activo) {
		this.activo = activo;
	}

	@Override
	public String toString() {
		return "Asignatura{" + "id='" + id + '\'' + ", nombre='" + nombre + '\'' + ", curso='" + curso + '\''
				+ ", tasa=" + tasa + ", activo=" + activo + '}';
	}
}