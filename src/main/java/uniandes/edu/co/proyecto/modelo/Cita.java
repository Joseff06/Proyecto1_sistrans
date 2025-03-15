package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
@Entity
@Table(name="citas")
public class Cita {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int idCita;
    private String fechaHora;

    // Constructor
    public Cita(int idCita, String fechaHora) {
        this.idCita = idCita;
        this.fechaHora = fechaHora;
    }

    // Getters and Setters
    public int getIdCita() {
        return idCita;
    }

    public void setIdCita(int idCita) {
        this.idCita = idCita;
    }

    public String getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(String fechaHora) {
        this.fechaHora = fechaHora;
    }
}