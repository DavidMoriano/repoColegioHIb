package dto;

public class NotaDTO {
    private int id;
    private String nota;
    private int idAsignatura;
    private String nombreAsignatura;
    private int idAlumno;
    private String nombreAlumno;
    private String fecha;

    // Constructor completo
    public NotaDTO(int id, String nota, int idAsignatura, String nombreAsignatura, int idAlumno, String nombreAlumno,
            String fecha) {
        super();
        this.id = id;
        this.nota = nota;
        this.idAsignatura = idAsignatura;
        this.nombreAsignatura = nombreAsignatura;
        this.idAlumno = idAlumno;
        this.nombreAlumno = nombreAlumno;
        this.fecha = fecha;
    }

    // Constructor sin nombres (para inserción)
    public NotaDTO(int id, String nota, int idAsignatura, int idAlumno, String fecha) {
        super();
        this.id = id;
        this.nota = nota;
        this.idAsignatura = idAsignatura;
        this.idAlumno = idAlumno;
        this.fecha = fecha;
    }

    // Constructor mínimo
    public NotaDTO(int id, String nota) {
        super();
        this.id = id;
        this.nota = nota;
    }

    // Getters y setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
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
}
