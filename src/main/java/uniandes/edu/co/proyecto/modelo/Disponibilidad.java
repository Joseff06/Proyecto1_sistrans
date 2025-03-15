package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.ManyToOne;

@Entity
@Table(name="Disponibilidad")
public class Disponibilidad {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int idDisponibilidad;
    private String fechaHora;
    private boolean estadoDisponibilidad;

    @ManyToOne
    private Medico medico;

    @ManyToOne
    private Ips ips;

    @ManyToOne
    private ServicioDeSalud servicioDeSalud;

    // Constructor
    public Disponibilidad(int idDisponibilidad, String fechaHora, boolean estadoDisponibilidad, Medico medico, Ips ips, ServicioDeSalud servicioDeSalud) {
        this.idDisponibilidad = idDisponibilidad;
        this.fechaHora = fechaHora;
        this.estadoDisponibilidad = estadoDisponibilidad;
        this.medico = medico;
        this.ips = ips;
        this.servicioDeSalud = servicioDeSalud;
    }

    // Getters and Setters
    public int getIdDisponibilidad() {
        return idDisponibilidad;
    }

    public void setIdDisponibilidad(int idDisponibilidad) {
        this.idDisponibilidad = idDisponibilidad;
    }

    public String getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(String fechaHora) {
        this.fechaHora = fechaHora;
    }

    public boolean isEstadoDisponibilidad() {
        return estadoDisponibilidad;
    }

    public void setEstadoDisponibilidad(boolean estadoDisponibilidad) {
        this.estadoDisponibilidad = estadoDisponibilidad;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Ips getIps() {
        return ips;
    }

    public void setIps(Ips ips) {
        this.ips = ips;
    }

    public ServicioDeSalud getServicioDeSalud() {
        return servicioDeSalud;
    }

    public void setServicioDeSalud(ServicioDeSalud servicioDeSalud) {
        this.servicioDeSalud = servicioDeSalud;
    }
}