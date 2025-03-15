package uniandes.edu.co.proyecto.modelo;

public class Disponibilidad {
    private String fechaHora;
    private String estadoDisponibilidad;

    // Constructor
    public Disponibilidad(String fechaHora, String estadoDisponibilidad) {
        this.fechaHora = fechaHora;
        this.estadoDisponibilidad = estadoDisponibilidad;
    }

    // Getters and Setters
    public String getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(String fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getEstadoDisponibilidad() {
        return estadoDisponibilidad;
    }

    public void setEstadoDisponibilidad(String estadoDisponibilidad) {
        this.estadoDisponibilidad = estadoDisponibilidad;
    }
}