package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.EmbeddedId;

@Entity
@Table(name="IpsServicioDeSalud")
public class IpsServicioDeSalud {

    @EmbeddedId
    private IpsServicioDeSaludPK pk;

    public IpsServicioDeSalud() {;}

    public IpsServicioDeSalud(Ips ips, ServicioDeSalud servicioDeSalud) {
        this.pk = new IpsServicioDeSaludPK(ips, servicioDeSalud);
    }

    public IpsServicioDeSaludPK getPk() {
        return pk;
    }

    public void setPk(IpsServicioDeSaludPK pk) {
        this.pk = pk;
    }
}
