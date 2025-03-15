package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
@Table(name="citas")
public class Cita {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int idCita;
    private String fechaHora;

    @ManyToOne
    private Afiliado afiliado;

    @ManyToOne
    private Disponibilidad disponibilidad;

    @ManyToOne
    private Ips ips;

    public Cita(){;}

    // Constructor
    public Cita(int idCita, String fechaHora, Afiliado afiliado, Disponibilidad disponibilidad, Ips ips) {
        this.idCita = idCita;
        this.fechaHora = fechaHora;
        this.afiliado = afiliado;
        this.disponibilidad = disponibilidad;
        this.ips = ips;
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