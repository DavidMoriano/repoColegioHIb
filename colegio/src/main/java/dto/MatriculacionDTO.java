package dto;

public class MatriculacionDTO {
    private int id;
    private int idAsignatura;
    private String nombreAsignatura;
    private int idAlumno;
    private String nombreAlumno;
    private String fecha;
    private int activo;
    private double tasa;

    // Constructor completo con tasa
    public MatriculacionDTO(int id, int idAsignatura, String nombreAsignatura, int idAlumno, String nombreAlumno,
            String fecha, int activo, double tasa) {
        super();
        this.id = id;
        this.idAsignatura = idAsignatura;
        this.nombreAsignatura = nombreAsignatura;
        this.idAlumno = idAlumno;
        this.nombreAlumno = nombreAlumno;
        this.fecha = fecha;
        this.activo = activo;
        this.tasa = tasa;
    }

    // Constructor completo sin tasa (para compatibilidad)
    public MatriculacionDTO(int id, int idAsignatura, String nombreAsignatura, int idAlumno, String nombreAlumno,
            String fecha, int activo) {
        super();
        this.id = id;
        this.idAsignatura = idAsignatura;
        this.nombreAsignatura = nombreAsignatura;
        this.idAlumno = idAlumno;
        this.nombreAlumno = nombreAlumno;
        this.fecha = fecha;
        this.activo = activo;
    }

    // Constructor sin nombres (para inserción)
    public MatriculacionDTO(int id, int idAsignatura, int idAlumno, String fecha, int activo) {
        super();
        this.id = id;
        this.idAsignatura = idAsignatura;
        this.idAlumno = idAlumno;
        this.fecha = fecha;
        this.activo = activo;
    }

    // Getters y setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdAsignatura() {
        return idAsignatura;
    }

    public void setIdAsignatura(int idAsignatura) {
        this.idAsignatura = idAsignatura;
    }

    public String getNombreAsignatura() {
        return nombreAsignatura;
    }

    public void setNombreAsignatura(String nombreAsignatura) {
        this.nombreAsignatura = nombreAsignatura;
    }

    public int getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
    }

    public String getNombreAlumno() {
        return nombreAlumno;
    }

    public void setNombreAlumno(String nombreAlumno) {
        this.nombreAlumno = nombreAlumno;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }

    public double getTasa() {
        return tasa;
    }

    public void setTasa(double tasa) {
        this.tasa = tasa;
    }
}
