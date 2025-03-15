package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

@Entity
@Table(name="PrestacionServicio")
public class PrestacionServicio {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int idPrestacionServicio;
    private String fechaHora;
    private String numDoc;
    private int registroMedico;
    private String nitIps;
    private int codigoServicio;

    @ManyToOne
    @JoinColumn(name = "numDoc", referencedColumnName = "numDoc", insertable = false, updatable = false)
    private Afiliado afiliado;

    @ManyToOne
    @JoinColumn(name = "registroMedico", referencedColumnName = "registroMedico", insertable = false, updatable = false)
    private Medico medico;

    @ManyToOne
    @JoinColumn(name = "nitIps", referencedColumnName = "nitIps", insertable = false, updatable = false)
    private Ips ips;

    @ManyToOne
    @JoinColumn(name = "codigoServicio", referencedColumnName = "codigo", insertable = false, updatable = false)
    private ServicioDeSalud servicioDeSalud;

    // Constructor
    public PrestacionServicio(int idPrestacionServicio, String fechaHora, String numDoc, int registroMedico, String nitIps, int codigoServicio) {
        this.idPrestacionServicio = idPrestacionServicio;
        this.fechaHora = fechaHora;
        this.numDoc = numDoc;
        this.registroMedico = registroMedico;
        this.nitIps = nitIps;
        this.codigoServicio = codigoServicio;
    }

    // Getters and Setters
    public int getIdPrestacionServicio() {
        return idPrestacionServicio;
    }

    public void setIdPrestacionServicio(int idPrestacionServicio) {
        this.idPrestacionServicio = idPrestacionServicio;
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

    public int getRegistroMedico() {
        return registroMedico;
    }

    public void setRegistroMedico(int registroMedico) {
        this.registroMedico = registroMedico;
    }

    public String getNitIps() {
        return nitIps;
    }

    public void setNitIps(String nitIps) {
        this.nitIps = nitIps;
    }

    public int getCodigoServicio() {
        return codigoServicio;
    }

    public void setCodigoServicio(int codigoServicio) {
        this.codigoServicio = codigoServicio;
    }

    public Afiliado getAfiliado() {
        return afiliado;
    }

    public void setAfiliado(Afiliado afiliado) {
        this.afiliado = afiliado;
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
