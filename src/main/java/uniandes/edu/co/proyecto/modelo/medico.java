package uniandes.edu.co.proyecto.modelo;

public class medico {
    private int registroMedico;
    private String nombre;
    private String tipoDocumento;
    private String numeroDocumento;
    private String especialidad;

    // Constructor
    public medico(int registroMedico, String nombre, String tipoDocumento, String numeroDocumento, String especialidad) {
        this.registroMedico = registroMedico;
        this.nombre = nombre;
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
        this.especialidad = especialidad;
    }

    // Getters and Setters
    public int getRegistroMedico() {
        return registroMedico;
    }

    public void setRegistroMedico(int registroMedico) {
        this.registroMedico = registroMedico;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }
}
