package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

@Entity
@Table(name="OrdenDeServicio")
public class OrdenDeServicio {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int idOrdenServicio;
    private String fechaHora;
    private int idEstadoOrden;
    private String numDoc;
    private int registroMedico;
    private int idCita;
    private int codigo;

    @ManyToOne
    @JoinColumn(name = "numDoc", referencedColumnName = "numDoc", insertable = false, updatable = false)
    private Afiliado afiliado;

    @ManyToOne
    @JoinColumn(name = "registroMedico", referencedColumnName = "registroMedico", insertable = false, updatable = false)
    private Medico medico;

    @ManyToOne
    @JoinColumn(name = "idCita", referencedColumnName = "idCita", insertable = false, updatable = false)
    private Cita cita;

    @ManyToOne
    @JoinColumn(name = "codigo", referencedColumnName = "codigo", insertable = false, updatable = false)
    private ServicioDeSalud servicioDeSalud;

    // Constructor
    public OrdenDeServicio(int idOrdenServicio, String fechaHora, int idEstadoOrden, String numDoc, int registroMedico, int idCita, int codigo) {
        this.idOrdenServicio = idOrdenServicio;
        this.fechaHora = fechaHora;
        this.idEstadoOrden = idEstadoOrden;
        this.numDoc = numDoc;
        this.registroMedico = registroMedico;
        this.idCita = idCita;
        this.codigo = codigo;
    }

    // Getters and Setters
    public int getIdOrdenServicio() {
        return idOrdenServicio;
    }

    public void setIdOrdenServicio(int idOrdenServicio) {
        this.idOrdenServicio = idOrdenServicio;
    }

    public String getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(String fechaHora) {
        this.fechaHora = fechaHora;
    }

    public int getIdEstadoOrden() {
        return idEstadoOrden;
    }

    public void setIdEstadoOrden(int idEstadoOrden) {
        this.idEstadoOrden = idEstadoOrden;
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

    public int getIdCita() {
        return idCita;
    }

    public void setIdCita(int idCita) {
        this.idCita = idCita;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
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

    public Cita getCita() {
        return cita;
    }

    public void setCita(Cita cita) {
        this.cita = cita;
    }

    public ServicioDeSalud getServicioDeSalud() {
        return servicioDeSalud;
    }

    public void setServicioDeSalud(ServicioDeSalud servicioDeSalud) {
        this.servicioDeSalud = servicioDeSalud;
    }
}