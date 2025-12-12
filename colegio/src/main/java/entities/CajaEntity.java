package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "caja")
public class CajaEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;

	@OneToOne
	@JoinColumn(name = "idmatricula")
	private MatriculacionesEntity matricula;

	@Column(name = "importe")
	private Double importe;
	
	public CajaEntity() {
		
	}

	public CajaEntity(Integer id, MatriculacionesEntity matricula, Double importe) {
		super();
		this.id = id;
		this.matricula = matricula;
		this.importe = importe;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public MatriculacionesEntity getMatricula() {
		return matricula;
	}

	public void setMatricula(MatriculacionesEntity matricula) {
		this.matricula = matricula;
	}

	public Double getImporte() {
		return importe;
	}

	public void setImporte(Double importe) {
		this.importe = importe;
	}
	
	
	
	
}
