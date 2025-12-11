package entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "municipios")
public class MunicipiosEntity {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id_municipio")
    private int idMunicipio;
    
    @Column(name = "id_provincia")
    private int idProvincia;
    
    @Column(name = "nombre")
    private String nombre;
    
    @Column(name = "cod_municipio")
    private String codMunicipio;
    
    @Column(name = "DC")
    private String dc;
    
    @OneToMany(mappedBy = "municipio")
    private List<AlumnoEntity> alumnos= new ArrayList<>();

	public MunicipiosEntity(int idMunicipio, int idProvincia, String nombre, String codMunicipio, String dc) {
		super();
		this.idMunicipio = idMunicipio;
		this.idProvincia = idProvincia;
		this.nombre = nombre;
		this.codMunicipio = codMunicipio;
		this.dc = dc;
	}
	
	public MunicipiosEntity() {
		
	}

	public int getIdMunicipio() {
		return idMunicipio;
	}

	public void setIdMunicipio(int idMunicipio) {
		this.idMunicipio = idMunicipio;
	}

	public int getIdProvincia() {
		return idProvincia;
	}

	public void setIdProvincia(int idProvincia) {
		this.idProvincia = idProvincia;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCodMunicipio() {
		return codMunicipio;
	}

	public void setCodMunicipio(String codMunicipio) {
		this.codMunicipio = codMunicipio;
	}

	public String getDc() {
		return dc;
	}

	public void setDc(String dc) {
		this.dc = dc;
	}
    
    
}
