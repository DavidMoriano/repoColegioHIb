package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "notas")
public class NotasEntity {

	@Id
	@Column(name = "id", length = 50)
	private String id;

	@Column(name = "id_alumnos", nullable = false, length = 100)
	private String id_alumnos;

	@Column(name = "id_asignaturas", length = 50)
	private String id_asignaturas;

	@Column(name = "nota", nullable = false, precision = 10, scale = 2)
	private Double nota;

	@Column(name = "fecha", nullable = false)
	private String fecha;

	public NotasEntity() {
	}

	public NotasEntity(String id, String id_alumnos, String id_asignaturas, Double nota, String fecha) {
		this.id = id;
		this.id_alumnos = id_alumnos;
		this.id_asignaturas = id_asignaturas;
		this.nota = nota;
		this.fecha = fecha;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId_alumnos() {
		return id_alumnos;
	}

	public void setId_alumnos(String id_alumnos) {
		this.id_alumnos = id_alumnos;
	}

	public String getId_asignaturas() {
		return id_asignaturas;
	}

	public void setId_asignaturas(String id_asignaturas) {
		this.id_asignaturas = id_asignaturas;
	}

	public Double getNota() {
		return nota;
	}

	public void setNota(Double nota) {
		this.nota = nota;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

}
