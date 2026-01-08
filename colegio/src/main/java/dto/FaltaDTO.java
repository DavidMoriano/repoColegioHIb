package dto;

public class FaltaDTO {
    private int idFalta;
    private int idAlumno;
    private String nombreAlumno;
    private int idAsignatura;
    private String nombreAsignatura;
    private String fecha;
    private int justificada;

    // Constructor completo
    public FaltaDTO(int idFalta, int idAlumno, String nombreAlumno, int idAsignatura, String nombreAsignatura,
            String fecha, int justificada) {
        super();
        this.idFalta = idFalta;
        this.idAlumno = idAlumno;
        this.nombreAlumno = nombreAlumno;
        this.idAsignatura = idAsignatura;
        this.nombreAsignatura = nombreAsignatura;
        this.fecha = fecha;
        this.justificada = justificada;
    }

    // Constructor sin nombres (para inserción)
    public FaltaDTO(int idFalta, int idAlumno, int idAsignatura, String fecha, int justificada) {
        super();
        this.idFalta = idFalta;
        this.idAlumno = idAlumno;
        this.idAsignatura = idAsignatura;
        this.fecha = fecha;
        this.justificada = justificada;
    }

    // Constructor mínimo
    public FaltaDTO(int idFalta, String fecha) {
        super();
        this.idFalta = idFalta;
        this.fecha = fecha;
    }

    // Getters y setters

    public int getIdFalta() {
        return idFalta;
    }

    public void setIdFalta(int idFalta) {
        this.idFalta = idFalta;
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

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getJustificada() {
        return justificada;
    }

    public void setJustificada(int justificada) {
        this.justificada = justificada;
    }
}
