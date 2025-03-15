package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

@Entity
@Table(name="Cita")
public class Cita {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int idCita;
    private String fechaHora;
    private String numDoc;
    private int idDisponibilidad;
    private String nitIps;

    @ManyToOne
    @JoinColumn(name = "numDoc", referencedColumnName = "numDoc", insertable = false, updatable = false)
    private Afiliado afiliado;

    @ManyToOne
    @JoinColumn(name = "idDisponibilidad", referencedColumnName = "idDisponibilidad", insertable = false, updatable = false)
    private Disponibilidad disponibilidad;

    @ManyToOne
    @JoinColumn(name = "nitIps", referencedColumnName = "nitIps", insertable = false, updatable = false)
    private Ips ips;

    public Cita(){;}

    // Constructor
    public Cita(int idCita, String fechaHora, String numDoc, int idDisponibilidad, String nitIps) {
        this.idCita = idCita;
        this.fechaHora = fechaHora;
        this.numDoc = numDoc;
        this.idDisponibilidad = idDisponibilidad;
        this.nitIps = nitIps;
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

    public String getNumDoc() {
        return numDoc;
    }

    public void setNumDoc(String numDoc) {
        this.numDoc = numDoc;
    }

    public int getIdDisponibilidad() {
        return idDisponibilidad;
    }

    public void setIdDisponibilidad(int idDisponibilidad) {
        this.idDisponibilidad = idDisponibilidad;
    }

    public String getNitIps() {
        return nitIps;
    }

    public void setNitIps(String nitIps) {
        this.nitIps = nitIps;
    }

    public Afiliado getAfiliado() {
        return afiliado;
    }

    public void setAfiliado(Afiliado afiliado) {
        this.afiliado = afiliado;
    }

    public Disponibilidad getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(Disponibilidad disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public Ips getIps() {
        return ips;
    }

    public void setIps(Ips ips) {
        this.ips = ips;
    }
}