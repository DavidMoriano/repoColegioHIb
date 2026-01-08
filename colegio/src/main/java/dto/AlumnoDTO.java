package dto;

public class AlumnoDTO {
	private int id;
	private String nombre;
	private String apellido;
	private String municipio;
	private int idMunicipio;
	private int familiaNumerosa;
	private int activo;
	
	public AlumnoDTO(int id, String nombre, String apellido, String municipio, int idMunicipio, int familiaNumerosa, int activo) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.municipio = municipio;
		this.idMunicipio = idMunicipio;
		this.familiaNumerosa = familiaNumerosa;
		this.activo = activo;
	}
	
	public AlumnoDTO(int id, String nombre, String apellido, String municipio) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.municipio = municipio;
	}
	
	public AlumnoDTO(int id, String nombre, String apellido) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
	}
	
	//Getters, setters... 

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

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public int getIdMunicipio() {
		return idMunicipio;
	}

	public void setIdMunicipio(int idMunicipio) {
		this.idMunicipio = idMunicipio;
	}

	public int getFamiliaNumerosa() {
		return familiaNumerosa;
	}

	public void setFamiliaNumerosa(int familiaNumerosa) {
		this.familiaNumerosa = familiaNumerosa;
	}

	public int getActivo() {
		return activo;
	}

	public void setActivo(int activo) {
		this.activo = activo;
	}
	
	
	

	
	
	

}
