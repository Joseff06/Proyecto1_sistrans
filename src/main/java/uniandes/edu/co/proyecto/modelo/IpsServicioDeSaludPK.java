package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import java.io.Serializable;

@Embeddable
public class IpsServicioDeSaludPK implements Serializable {

    @ManyToOne
    @JoinColumn(name = "nitIPS", referencedColumnName = "nitIPS")
    private Ips ips;

    @ManyToOne
    @JoinColumn(name = "codigo", referencedColumnName = "codigo")
    private ServicioDeSalud servicioDeSalud;

    public IpsServicioDeSaludPK() {
        super();
    }

    public IpsServicioDeSaludPK(Ips ips, ServicioDeSalud servicioDeSalud) {
        super();
        this.ips = ips;
        this.servicioDeSalud = servicioDeSalud;
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
