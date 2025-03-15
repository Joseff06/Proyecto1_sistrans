package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.EmbeddedId;

@Entity
@Table(name="MedicoIps")
public class MedicoIps {

    @EmbeddedId
    private MedicoIpsPK pk;

    public MedicoIps() {;}

    public MedicoIps(Medico medico, Ips ips) {
        this.pk = new MedicoIpsPK(medico, ips);
    }

    public MedicoIpsPK getPk() {
        return pk;
    }

    public void setPk(MedicoIpsPK pk) {
        this.pk = pk;
    }
}
