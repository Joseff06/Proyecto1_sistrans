package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.ManyToOne;

@Entity
@Table(name="OrdenDeServicio")
public class OrdenDeServicio {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int idOrden;
    private String fecha;
    private String estado;

    @ManyToOne
    private Afiliado afiliado;

    @ManyToOne
    private Medico medico;

    @ManyToOne
    private Cita cita;

    @ManyToOne
    private ServicioDeSalud servicioDeSalud;

    public OrdenDeServicio(){;}

    // Constructor
    public OrdenDeServicio(int idOrden, String fecha, String estado, Afiliado afiliado, Medico medico, Cita cita, ServicioDeSalud servicioDeSalud) {
        this.idOrden = idOrden;
        this.fecha = fecha;
        this.estado = estado;
        this.afiliado = afiliado;
        this.medico = medico;
        this.cita = cita;
        this.servicioDeSalud = servicioDeSalud;
    }

    // Getters and Setters
    public int getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(int idOrden) {
        this.idOrden = idOrden;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}